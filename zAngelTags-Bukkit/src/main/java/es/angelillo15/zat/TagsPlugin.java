package es.angelillo15.zat;

import es.angelillo15.zat.api.Constants;
import es.angelillo15.zat.api.ILogger;
import es.angelillo15.zat.api.TagsInstance;
import es.angelillo15.zat.api.TextUtils;
import es.angelillo15.zat.utils.BukkitLogger;
import es.angelillo15.zat.utils.LibsLoader;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class TagsPlugin extends JavaPlugin implements TagsInstance {
    @Getter
    private static TagsPlugin instance;
    private static ILogger pLogger;

    public TagsPlugin() {
        instance = this;
    }

    @Override
    public ILogger getPLogger() {
        return pLogger;
    }

    @Override
    public boolean isDebug() {
        return false;
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

    }

    @Override
    public void registerCommands() {

    }

    @Override
    public void registerListeners() {

    }

    @Override
    public void loadDatabase() {

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
