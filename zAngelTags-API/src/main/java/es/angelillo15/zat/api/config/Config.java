package es.angelillo15.zat.api.config;

import es.angelillo15.zat.api.database.DataProvider;

public class Config {
    public static class Database {
        public static DataProvider type() {
            return DataProvider.valueOf(ConfigLoader.getConfig().getConfig().getString("Database.type").toUpperCase());
        }

        public static String host() {
            return ConfigLoader.getConfig().getConfig().getString("Database.host");
        }

        public static int port() {
            return ConfigLoader.getConfig().getConfig().getInt("Database.port");
        }

        public static String database() {
            return ConfigLoader.getConfig().getConfig().getString("Database.database");
        }

        public static String user() {
            return ConfigLoader.getConfig().getConfig().getString("Database.user");
        }

        public static String password() {
            return ConfigLoader.getConfig().getConfig().getString("Database.password");
        }
    }
}
