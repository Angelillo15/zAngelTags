package es.angelillo15.zangeltags.database;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PluginConnection {

    private Connection conection;
    private String host;
    private int port;
    private String database;
    private String user;
    private String password;
    private String driver;

    public PluginConnection(String host, int port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
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

    public Connection getConection() {
        return conection;
    }


}