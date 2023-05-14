package es.angelillo15.zat.api.database;

import com.craftmend.storm.Storm;
import com.craftmend.storm.StormOptions;
import com.craftmend.storm.connection.sqlite.SqliteFileDriver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import es.angelillo15.zat.api.TagsInstance;
import es.angelillo15.zat.api.TextUtils;
import es.angelillo15.zat.api.models.UserModel;
import lombok.Getter;
import lombok.SneakyThrows;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PluginConnection {
    @Getter
    private static Connection connection;
    @Getter
    private static DataProvider dataProvider;
    private static HikariConfig config;
    @Getter
    private static Storm storm;
    private static HikariDataSource dataSource;
    @Getter
    private static PluginConnection instance;
    @Getter
    private Connection conn;
    @SneakyThrows
    public PluginConnection(String host, int port, String database, String user, String password){
        dataProvider = DataProvider.MYSQL;
        config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useUnicode=yes");
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(20);
        config.setConnectionTimeout(30000);
        config.setLeakDetectionThreshold(0);
        dataSource = new HikariDataSource(config);
        StormOptions options = new StormOptions();

        options.setLogger(new StormLogger());
        storm = new Storm(options, new PluginDriver(dataSource));

        try {
            connection = dataSource.getConnection();
            conn = connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        instance = this;
    }

    public PluginConnection(String pluginPath){
        dataProvider = DataProvider.SQLITE;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            TagsInstance.getLogger().error((TextUtils.simpleColorize("&c┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓")));
            TagsInstance.getLogger().error((TextUtils.simpleColorize("&c┃ The SQLite driver couldn't be found!                                     ┃")));
            TagsInstance.getLogger().error((TextUtils.simpleColorize("&c┃                                                                          ┃")));
            TagsInstance.getLogger().error((TextUtils.simpleColorize("&c┃ Please, join our Discord server to get support:                          ┃")));
            TagsInstance.getLogger().error((TextUtils.simpleColorize("&c┃ https://discord.nookure.com                                              ┃")));
            TagsInstance.getLogger().error((TextUtils.simpleColorize("&c┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛")));
        }
        try {
            String dataPath = pluginPath + "/database.db";
            String url = "jdbc:sqlite:" + dataPath;
            connection = DriverManager.getConnection(url);
            conn = connection;
            StormOptions options = new StormOptions();

            options.setLogger(new StormLogger());

            storm = new Storm(options, new SqliteFileDriver(new File(dataPath)));

        } catch (SQLException e) {
            TagsInstance.getLogger().error("An error ocurred while trying to connect to the SQLite database: " + e.getMessage());
        }

        instance = this;
    }

    public static boolean tableExists(String table) {
        try {
            return connection.getMetaData().getTables(null, null, table, null).next();
        } catch (SQLException e) {
            TagsInstance.getLogger().error("An error ocurred while trying to check if the table " + table + " exists: " + e.getMessage());
            return false;
        }
    }

    @SneakyThrows
    public static void migrate() {
        Storm storm = PluginConnection.getStorm();
        storm.registerModel(new UserModel());
        storm.runMigrations();
    }
}
