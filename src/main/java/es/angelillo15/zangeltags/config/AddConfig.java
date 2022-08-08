package es.angelillo15.zangeltags.config;

import org.bukkit.configuration.file.FileConfiguration;

public class AddConfig {
    private FileConfiguration config = ConfigLoader.getMainConfig().getConfig();
    private FileConfiguration msg = ConfigLoader.getMessageConfig().getConfig();

    public AddConfig() {
        addMainConfig();
        addMessageConfig();
    }

    public void addMainConfig() {
        if (!(config.contains("Database.type"))) {
            config.set("Database.type", "MySQL");
            ConfigLoader.getMainConfig().saveConfig();
        }

        if(!(config.contains("Config.injectChat"))){
            config.set("Config.injectChat", false);
            ConfigLoader.getMainConfig().saveConfig();
        }

        if(!(config.contains("Config.autoUpdates"))){
            config.set("Config.autoUpdates", true);
            ConfigLoader.getMainConfig().saveConfig();
        }
        if(!(config.contains("Config.defaultTag"))){
            config.set("Config.defaultTag", false);
            config.set("Config.defaultTagName", "<tag_name>");
            ConfigLoader.getMainConfig().saveConfig();
        }
    }

    public void addMessageConfig(){
        if(!(msg.contains("Messages.offline"))){
            msg.set("Messages.offline", "&b「zAngelTags」&4The player is offline");
            ConfigLoader.getMessageConfig().saveConfig();
        }
    }
}
