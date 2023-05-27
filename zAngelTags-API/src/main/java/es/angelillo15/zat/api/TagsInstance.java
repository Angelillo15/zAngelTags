package es.angelillo15.zat.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public interface TagsInstance {
    int version = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]);
    static int version() {
        return version;
    }
    static TagsInstance getInstance() {
        return (TagsInstance) Bukkit.getPluginManager().getPlugin("zAngelTags");
    }

    static boolean placeholderCheck() {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    static ILogger getLogger() {
        return getInstance().getPLogger();
    }

    ILogger getPLogger();
    boolean isDebug();
    void setDebug(boolean debug);
    void drawLogo();
    void loadConfig();
    void registerCommands();
    void registerListeners();
    void loadDatabase();
    void unregisterCommands();
    void unregisterListeners();
    void unloadDatabase();
    void reload();
    ITagPlayer createTagPlayer(Player player);
}
