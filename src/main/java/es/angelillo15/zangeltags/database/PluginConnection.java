package es.angelillo15.zangeltags.database;

import es.angelillo15.zangeltags.ZAngelTags;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PluginConnection {

    private Connection conection;
    private Connection conn;
    private String host;
    private int port;
    private String database;
    private String user;
    private String password;
    private ZAngelTags plugin;
    private String type;

    public PluginConnection(String host, int port, String database, String user, String password, String type, ZAngelTags plugin) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        this.plugin = plugin;
        this.type = type;
        if (type.equalsIgnoreCase("SQLite")) {

            try {
                String path = plugin.getDataFolder().getAbsolutePath() + "/database.db";
                // db parameters
                String url = "jdbc:sqlite:" + path;
                // create a connection to the database
                conn = DriverManager.getConnection(url);

                System.out.println("Connection to SQLite has been established.");

            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(String.valueOf(e));

            }
        } else {
            try {
                synchronized (this) {
                    if (conection != null && !conection.isClosed()) {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l[zAngelTags] &6Error connecting to database"));
                        return;
                    }
                    Class.forName("com.mysql.jdbc.Driver");
                    this.conection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.password);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b「zAngelTags」&6Successfully connected to Database"));

                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(String.valueOf(e));

            } catch (ClassNotFoundException e) {
                Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            }
        }
    }


    public Connection getConection() {
        return conection;
    }

}