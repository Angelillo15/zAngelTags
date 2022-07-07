package es.angelillo15.zangeltags.cache;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SQLQuerys;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class TagsCache {
    public static void saveTags(ZAngelTags plugin) {
        File f = new File(plugin.getDataFolder().getAbsolutePath() + "/cache", "tags.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);

        c.set("Tags", null);
        try {
            c.save(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Connection connection = plugin.getConnection();

        ArrayList<String> tags = SQLQuerys.getTagsName(connection);
        for (String s : tags){
            String inGameTag = SQLQuerys.getTagInGameTag(connection, s);
            String permission = SQLQuerys.getTagPermission(connection, s);
            c.set("Tags."+s+".name", s);
            c.set("Tags."+s+".inGameTag", inGameTag);
            c.set("Tags."+s+".permission", permission);
        }
        try {
            c.save(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CacheConfigLoader.getTagsCache().reloadConfig();
    }
}
