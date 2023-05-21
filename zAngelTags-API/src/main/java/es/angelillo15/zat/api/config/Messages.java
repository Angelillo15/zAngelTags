package es.angelillo15.zat.api.config;

import es.angelillo15.zat.api.TextUtils;
import org.simpleyaml.configuration.file.YamlFile;

public class Messages {
    public static String prefix(){
        return getMSG().getString("Prefix");
    }
    public static class Command {
        public static String noPermission() {
            return format("Commands.noPermission");
        }

        public static class Zat {
            public static class Create {
                public static String usage() {
                    return format("Commands.zat.create.usage");
                }

                public static String success() {
                    return format("Commands.zat.create.success");
                }

                public static String success(String tag, String permission, String display) {
                    return format("Commands.zat.create.success")
                            .replace("{tag}", tag)
                            .replace("{permission}", permission)
                            .replace("{display}", display);
                }

                public static String alreadyExists() {
                    return format("Commands.zat.create.alreadyExists");
                }

                public static String alreadyExists(String tag) {
                    return format("Commands.zat.create.alreadyExists")
                            .replace("{tag}", tag);
                }
            }
        }
    }

    public static String format(String path) {
        return TextUtils.colorize(getMSG().getString(path)
                .replace("{prefix}", prefix())
        );
    }

    public static YamlFile getMSG() {
        return ConfigLoader.getMessages().getConfig();
    }
}
