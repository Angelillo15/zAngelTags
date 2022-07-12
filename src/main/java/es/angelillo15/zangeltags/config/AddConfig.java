package es.angelillo15.zangeltags.config;

import org.bukkit.configuration.file.FileConfiguration;

public class AddConfig {
    private FileConfiguration config = ConfigLoader.getMainConfig().getConfig();

    public AddConfig() {
        addMainConfig();
    }

    public void addMainConfig() {
        if (!(config.contains("Database.type"))) {
            config.set("Database.type", "MySQL");
            ConfigLoader.getMainConfig().saveConfig();
        }
    }
}
