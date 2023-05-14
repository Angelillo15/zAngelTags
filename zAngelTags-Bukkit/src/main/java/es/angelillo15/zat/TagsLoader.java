package es.angelillo15.zat;

import es.angelillo15.zat.utils.Metrics;
import io.papermc.lib.PaperLib;

public class TagsLoader extends TagsPlugin {
    @Override
    public void onEnable() {
        loadLogger();
        drawLogo();
        loadConfig();
        registerCommands();
        registerListeners();
        loadDatabase();
        PaperLib.suggestPaper(this);
        new Metrics(this, 15601);
    }

    @Override
    public void onDisable() {
        unregisterCommands();
        unregisterListeners();
        unloadDatabase();
    }
}
