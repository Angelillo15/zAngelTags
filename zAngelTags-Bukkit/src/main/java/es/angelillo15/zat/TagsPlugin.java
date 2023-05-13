package es.angelillo15.zat;

import es.angelillo15.zat.api.ILogger;
import es.angelillo15.zat.api.TagsInstance;
import org.bukkit.plugin.java.JavaPlugin;

public class TagsPlugin extends JavaPlugin implements TagsInstance {
    @Override
    public ILogger getPLogger() {
        return null;
    }

    @Override
    public boolean isDebug() {
        return false;
    }

    @Override
    public void drawLogo() {

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
}
