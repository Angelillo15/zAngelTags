package es.angelillo15.zat.api.config;

import es.angelillo15.configmanager.ConfigManager;
import es.angelillo15.zat.api.ILogger;
import es.angelillo15.zat.api.TagsInstance;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigLoader {
    private JavaPlugin plugin = (JavaPlugin) TagsInstance.getInstance();
    @Getter
    private static ConfigManager config;
    @Getter
    private static ConfigManager messages;
    @Getter
    private static ConfigManager internalStaffItems;
    @Getter
    private static ConfigManager glow;
    @Getter
    private static ConfigManager es;
    @Getter
    private static ConfigManager en;
    @Getter
    private static ConfigManager customItems;
    @Getter
    private static ConfigManager punishmentsGUI;
    private String language;

    public void load() {
        ILogger logger = TagsInstance.getInstance().getPLogger();
        loadConfig();
        logger.debug("Loaded config");
        logger.debug("Loading languages...");
        loadLanguages();
        logger.debug("Loading messages...");
        loadMessage();
    }

    public void loadConfig() {
        ConfigMerge.merge(new File(plugin.getDataFolder().toPath().toString() + File.separator + "config.yml"),
                plugin.getResource("Bukkit/config.yml")
        );

        config = new ConfigManager(plugin.getDataFolder().toPath(), "Bukkit/config.yml", "config.yml");
        config.registerConfig();

        TagsInstance.getInstance().setDebug(config.getConfig().getBoolean("Config.debug"));
    }

    public void loadLanguages() {
        File file = new File(plugin.getDataFolder().toPath().toString() + File.separator + "lang");
        if (!file.exists()) {
            file.mkdirs();
        }

        es = new ConfigManager(plugin.getDataFolder().toPath(), "Bukkit/lang/es.yml", "/lang/es.yml");
        en = new ConfigManager(plugin.getDataFolder().toPath(), "Bukkit/lang/en.yml", "/lang/en.yml");
        es.registerConfig();
        en.registerConfig();
    }

    public void loadMessage() {
        language = config.getConfig().getString("Config.language");
        String lang = "lang/" + language;

        messages = new ConfigManager(plugin.getDataFolder().toPath(), "/Bukkit/" +lang, lang);
        messages.registerConfig();
        try {
            messages.getConfig().load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
