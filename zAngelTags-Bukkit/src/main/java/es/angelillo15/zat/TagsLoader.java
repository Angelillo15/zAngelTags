package es.angelillo15.zat;

public class TagsLoader extends TagsPlugin {
    @Override
    public void onEnable() {
        loadLogger();
        drawLogo();
        loadConfig();
        registerCommands();
        registerListeners();
        loadDatabase();
    }

    @Override
    public void onDisable() {
        unregisterCommands();
        unregisterListeners();
        unloadDatabase();
    }
}
