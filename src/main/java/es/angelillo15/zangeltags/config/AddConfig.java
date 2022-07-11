package es.angelillo15.zangeltags.config;

import org.bukkit.configuration.file.FileConfiguration;

public class AddConfig {
    private FileConfiguration config = ConfigLoader.getMainConfig().getConfig();

    public AddConfig() {
        addMainConfig();
    }

    public void addMainConfig() {
        if (!(config.contains("Database.type"))) {
            config.set("Config.gui", "default");
            config.set("Database.type", "MySQL");
            config.options().header(
                    "███████╗ █████╗ ███╗   ██╗ ██████╗ ███████╗██╗  ████████╗ █████╗  ██████╗ ███████╗\n" +
                    "╚══███╔╝██╔══██╗████╗  ██║██╔════╝ ██╔════╝██║  ╚══██╔══╝██╔══██╗██╔════╝ ██╔════╝\n" +
                    "  ███╔╝ ███████║██╔██╗ ██║██║  ███╗█████╗  ██║     ██║   ███████║██║  ███╗███████╗\n" +
                    " ███╔╝  ██╔══██║██║╚██╗██║██║   ██║██╔══╝  ██║     ██║   ██╔══██║██║   ██║╚════██║\n" +
                    "███████╗██║  ██║██║ ╚████║╚██████╔╝███████╗███████╗██║   ██║  ██║╚██████╔╝███████║\n" +
                    "╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚══════╝╚══════╝╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚══════╝\n"
                    +
                    "Database types -> MySQL (MySQL or MariaDB) / SQLite");
            config.options().copyHeader(true);
            ConfigLoader.getMainConfig().saveConfig();
        }
    }
}
