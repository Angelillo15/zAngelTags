package es.angelillo15.zangeltags.config;

import es.angelillo15.zangeltags.ZAngelTags;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

public class ConfigLoader {
    private ZAngelTags plugin;
    private static ConfigManager MainConfig;
    private static ConfigManager MessageConfig;

    private static ConfigManager GuiConfig;

    public ConfigLoader(ZAngelTags plugin) {
        this.plugin = plugin;
        loadMainConfig();
        loadMessages();
        loadGuiConfig();
    }

    public void loadMainConfig() {
        this.MainConfig = new ConfigManager(plugin, "", "Config.yml");
        this.MainConfig.saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getPrefix() + "&6Config successfully loaded"));
    }

    public void loadMessages() {
        this.MessageConfig = new ConfigManager(plugin, "", "messages.yml");
        this.MessageConfig.saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getPrefix() + "&6Messages successfully loaded"));

    }

    public void loadGuiConfig() {
        this.GuiConfig = new ConfigManager(plugin, "", "Gui.yml");
        this.GuiConfig.saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getPrefix() + "&6Gui successfully loaded"));

    }

    public static ConfigManager getMainConfig() {
        return MainConfig;
    }

    public static ConfigManager getMessageConfig() {
        return MessageConfig;
    }

    public static ConfigManager getGuiConfig() {
        return GuiConfig;
    }
}
