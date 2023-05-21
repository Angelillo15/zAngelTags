package es.angelillo15.zat;

import es.angelillo15.zat.api.*;
import es.angelillo15.zat.api.config.Config;
import es.angelillo15.zat.api.config.ConfigLoader;
import es.angelillo15.zat.api.database.DataProvider;
import es.angelillo15.zat.api.database.PluginConnection;
import es.angelillo15.zat.cmd.TestCMD;
import es.angelillo15.zat.cmd.manager.AbstractCommandLoader;
import es.angelillo15.zat.listeners.UserDataListener;
import es.angelillo15.zat.utils.BukkitLogger;
import es.angelillo15.zat.utils.LibsLoader;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TagsPlugin extends JavaPlugin implements TagsInstance {
    @Getter
    private static TagsPlugin instance;
    private static ILogger pLogger;
    private boolean debug;

    public TagsPlugin() {
        instance = this;
    }

    @Override
    public ILogger getPLogger() {
        return pLogger;
    }

    @Override
    public boolean isDebug() {
        return debug;
    }

    @Override
    public void drawLogo() {
        pLogger.info(TextUtils.simpleColorize("&4 ·▄▄▄▄• ▄▄▄·  ▐ ▄  ▄▄ • ▄▄▄ .▄▄▌  ▄▄▄▄▄ ▄▄▄·  ▄▄ • .▄▄ ·  "));
        pLogger.info(TextUtils.simpleColorize("&4 ▪▀·.█▌▐█ ▀█ •█▌▐█▐█ ▀ ▪▀▄.▀·██•  •██  ▐█ ▀█ ▐█ ▀ ▪▐█ ▀.  "));
        pLogger.info(TextUtils.simpleColorize("&4 ▄█▀▀▀•▄█▀▀█ ▐█▐▐▌▄█ ▀█▄▐▀▀▪▄██▪   ▐█.▪▄█▀▀█ ▄█ ▀█▄▄▀▀▀█▄ "));
        pLogger.info(TextUtils.simpleColorize("&4 █▌▪▄█▀▐█ ▪▐▌██▐█▌▐█▄▪▐█▐█▄▄▌▐█▌▐▌ ▐█▌·▐█ ▪▐▌▐█▄▪▐█▐█▄▪▐█ "));
        pLogger.info(TextUtils.simpleColorize("&4 ·▀▀▀ • ▀  ▀ ▀▀ █▪·▀▀▀▀  ▀▀▀ .▀▀▀  ▀▀▀  ▀  ▀ ·▀▀▀▀  ▀▀▀▀  "));
        pLogger.info(TextUtils.simpleColorize("&a &7v" + Constants.VERSION + " - " + Constants.COMMIT));
        LibsLoader.load();
    }

    @Override
    public void loadConfig() {
        new ConfigLoader().load();
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public void registerCommands() {
        AbstractCommandLoader.registerCommand(new TestCMD());
    }

    @Override
    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new UserDataListener(), this);
    }

    @Override
    public ITagPlayer createTagPlayer(Player player) {
        return new TagPlayer(player);
    }

    @SneakyThrows
    @Override
    public void loadDatabase() {
        if (Config.Database.type() == DataProvider.MYSQL) {
            new PluginConnection(
                    Config.Database.host(),
                    Config.Database.port(),
                    Config.Database.database(),
                    Config.Database.user(),
                    Config.Database.password()
            );
        } else {
            new PluginConnection(getDataFolder().getPath());
        }
        pLogger.debug("Running database migrations...");
        PluginConnection.migrate();
        pLogger.debug("Database migrations finished!");
    }

    @Override
    public void unregisterCommands() {

    }

    @Override
    public void unregisterListeners() {

    }

    @Override
    public void unloadDatabase() {

    }

    @Override
    public void reload() {

    }

    public void loadLogger() {
        pLogger = new BukkitLogger(getLogger());
    }
}
