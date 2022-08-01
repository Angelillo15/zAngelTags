package es.angelillo15.zangeltags;

import es.angelillo15.zangeltags.bstats.Metrics;
import es.angelillo15.zangeltags.cache.TagsCache;
import es.angelillo15.zangeltags.cmd.commandsmanagers.MainCommandManager;
import es.angelillo15.zangeltags.cmd.createtag.CreateTag;
import es.angelillo15.zangeltags.cmd.createtag.CreateTagTabComplete;
import es.angelillo15.zangeltags.cmd.mainsubcommands.TabComplete;
import es.angelillo15.zangeltags.cmd.removetag.RemoveTag;
import es.angelillo15.zangeltags.cmd.removetag.RemoveTagTabComplete;
import es.angelillo15.zangeltags.config.AddConfig;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.PluginConnection;
import es.angelillo15.zangeltags.database.SqlQueries;
import es.angelillo15.zangeltags.listener.ChatInjector;
import es.angelillo15.zangeltags.listener.TagsInventoryClickEvent;
import es.angelillo15.zangeltags.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import es.angelillo15.zangeltags.spiget.SpigetUpdate;
import es.angelillo15.zangeltags.spiget.UpdateCallback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;


public final class ZAngelTags extends JavaPlugin {
    //Connection
    private PluginConnection connection;
    //plugin prefix
    private String prefix = "&b「zAngelTags」";
    PluginDescriptionFile pdf = this.getDescription();
    public String version = pdf.getVersion();
    public String latestVersion;
    //Config Loader
    private ConfigLoader cl;

    //Array List of players


    //bstats loader
    int pluginId = 15601;
    Metrics metrics = new Metrics(this, pluginId);
    public TagsCache tagsCache = new TagsCache(this);

    //Plugin enable
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "\n " +
                "███████╗ █████╗ ███╗   ██╗ ██████╗ ███████╗██╗  ████████╗ █████╗  ██████╗ ███████╗\n" +
                "╚══███╔╝██╔══██╗████╗  ██║██╔════╝ ██╔════╝██║  ╚══██╔══╝██╔══██╗██╔════╝ ██╔════╝\n" +
                "  ███╔╝ ███████║██╔██╗ ██║██║  ███╗█████╗  ██║     ██║   ███████║██║  ███╗███████╗\n" +
                " ███╔╝  ██╔══██║██║╚██╗██║██║   ██║██╔══╝  ██║     ██║   ██╔══██║██║   ██║╚════██║\n" +
                "███████╗██║  ██║██║ ╚████║╚██████╔╝███████╗███████╗██║   ██║  ██║╚██████╔╝███████║\n" +
                "╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚══════╝╚══════╝╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚══════╝\n" +
                "                                                                      &bVersion: " + version)
        );
        ConfigLoader cl = new ConfigLoader(this);
        AddConfig addConfig = new AddConfig();
        dbConnection();
        registerCommand();
        registerEvents();
        registerPlaceholder();
        tagsCache.loadData();
        downloadUpdate();

    }

    //Check if the plugin is in the latest update
    public void updateChecker() {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    "https://api.spigotmc.org/legacy/update.php?resource=102952").openConnection();
            int timed_out = 1250;
            con.setConnectTimeout(timed_out);
            con.setReadTimeout(timed_out);
            latestVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if (latestVersion.length() <= 7) {
                if (!version.equals(latestVersion)) {
                    Bukkit.getConsoleSender().sendMessage("");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&aThere is a new update of the plugin"));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "「zAngelTags」" + ChatColor.RED + "You can download it at: " + ChatColor.WHITE + "https://www.spigotmc.org/resources/102952/");
                }
            }
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + "&4&lERROR CHECKING UPDATES"));
        }
    }

    //Register PAPI placeholder
    public void registerPlaceholder() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceHolderApiExtensions(this).register();
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "&6&l[⚠ WARNING ⚠] &bPlaceholderAPI isn't installed, PlaceholderAPI is necesary to the Placeholders to work"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "&6&l[⚠ WARNING ⚠] &bYou can download PlaceholderAPI at: https://www.spigotmc.org/resources/6245/"));

        }
    }

    //Open database connection
    public void dbConnection() {
        FileConfiguration db = ConfigLoader.getMainConfig().getConfig();
        String host = db.getString("Database.host");
        int port = Integer.parseInt(db.getString("Database.port"));
        String database = db.getString("Database.database");
        String user = db.getString("Database.user");
        String password = db.getString("Database.password");
        String type = db.getString("Database.type");
        this.connection = new PluginConnection(host, port, database, user, password, type, this);
        if(type.equalsIgnoreCase("SQLite")){
            SqlQueries.createTagsDataSQLiteTable(getConnection());
            SqlQueries.createUserDataSQLiteTable(getConnection());
        }else {
            if (!SqlQueries.userDatatableCreated(getConnection())) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "&6Creating userData table....."));
                SqlQueries.createUserDataTables(getConnection());
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "&6userData table succesfully created!"));
            }
            if (!SqlQueries.tagsTableCreated(getConnection())) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "&6Creating tags table....."));
                SqlQueries.createTagsTables(getConnection());
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "&6Tags table succesfully created!"));
            }
        }
    }

    //Register commands with tab complete
    public void registerCommand() {
        this.getCommand("zAngelTags").setExecutor(new MainCommandManager(this));
        this.getCommand("zat").setExecutor(new MainCommandManager(this));
        this.getCommand("zAngelTags").setTabCompleter(new TabComplete(this));
        this.getCommand("zat").setTabCompleter(new TabComplete(this));
        this.getCommand("tags").setExecutor(new MainCommandManager(this));
        this.getCommand("tags").setTabCompleter(new TabComplete(this));
        //Command to create tags
        this.getCommand("createTag").setExecutor(new CreateTag(this));
        this.getCommand("createTag").setTabCompleter(new CreateTagTabComplete());
        //Command to delete tags
        this.getCommand("removeTag").setExecutor(new RemoveTag(this));
        this.getCommand("removeTag").setTabCompleter(new RemoveTagTabComplete(this));
    }

    //Register events
    public void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new TagsInventoryClickEvent(this), this);
        pm.registerEvents(new ChatInjector(this), this);
    }

    //Close the database connection
    public void closeConnection() {
        SqlQueries.closeConnection(getConnection());
        Bukkit.getConsoleSender().sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', this.prefix + "&6Connection closed"));
    }

    public Connection getConnection() {
        return this.connection.getConection();
    }

    //This function returns the prefix of the plugin
    public String getPrefix() {
        return this.prefix;
    }

    //This function is to reload all the plugin
    public void reload() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "\n " +
                "███████╗ █████╗ ███╗   ██╗ ██████╗ ███████╗██╗  ████████╗ █████╗  ██████╗ ███████╗\n" +
                "╚══███╔╝██╔══██╗████╗  ██║██╔════╝ ██╔════╝██║  ╚══██╔══╝██╔══██╗██╔════╝ ██╔════╝\n" +
                "  ███╔╝ ███████║██╔██╗ ██║██║  ███╗█████╗  ██║     ██║   ███████║██║  ███╗███████╗\n" +
                " ███╔╝  ██╔══██║██║╚██╗██║██║   ██║██╔══╝  ██║     ██║   ██╔══██║██║   ██║╚════██║\n" +
                "███████╗██║  ██║██║ ╚████║╚██████╔╝███████╗███████╗██║   ██║  ██║╚██████╔╝███████║\n" +
                "╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚══════╝╚══════╝╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚══════╝\n" +
                "                                                                      &bVersion: " + version)
        );
        closeConnection();
        ConfigLoader.getGuiConfig().reloadConfig();
        ConfigLoader.getMainConfig().reloadConfig();
        ConfigLoader.getMessageConfig().reloadConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "&6Successfully reloaded the config"));
        dbConnection();
        tagsCache.loadData();
        downloadUpdate();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "&6Successfully reloaded the plugin"));
    }

    //Reload cache config
    public void reloadCache() {
        tagsCache.loadData();
    }

    //Returns Config loader
    public ConfigLoader getCl() {
        return this.cl;
    }

    //Download update

    public void downloadUpdate(){
        if(!(ConfigLoader.getMainConfig().getConfig().getBoolean("Config.autoUpdates"))) {
            updateChecker();
            return;
        }
        final SpigetUpdate updater = new SpigetUpdate(this, 102952);
        updater.checkForUpdate(new UpdateCallback() {
            @Override
            public void updateAvailable(String newVersion, String downloadUrl, boolean hasDirectDownload) {
                if (hasDirectDownload) {
                    int spigotVer = Integer.parseInt(newVersion.replace(".", ""));
                    int plVer = Integer.parseInt(version.replace(".", ""));
                    if(plVer > spigotVer){
                        Bukkit.getConsoleSender().sendMessage(ColorUtils.translateColorCodes(getPrefix() + "Running latest version"));
                        return;
                    }
                    if (updater.downloadUpdate()) {
                        //Successful update
                        Bukkit.getConsoleSender().sendMessage(ColorUtils.translateColorCodes(getPrefix() + "Plugin update downloaded in the next server restart will be applied"));
                    } else {
                        // Update failed
                        getLogger().warning("Update download failed, reason is " + updater.getFailReason());
                    }
                }
            }

            @Override
            public void upToDate() {
                Bukkit.getConsoleSender().sendMessage(ColorUtils.translateColorCodes(getPrefix() + "Running latest version"));
            }
        });
    }

    //Plugin disable
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ColorUtils.translateColorCodes(prefix + "Disabling plugin"));
        try {
            connection.getConection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
