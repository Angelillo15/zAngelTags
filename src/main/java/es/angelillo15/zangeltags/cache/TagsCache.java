package es.angelillo15.zangeltags.cache;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.database.SQLQuerys;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.sql.Connection;
import java.util.ArrayList;

public class TagsCache {
    private FileConfiguration cache = new YamlConfiguration();
    private ZAngelTags plugin;
    public TagsCache (ZAngelTags plugin){
        this.plugin = plugin;
    }

    public void loadData(){
        this.cache.set("Tags", null);
        Connection connection = plugin.getConnection();

        ArrayList<String> tags = SQLQuerys.getTagsName(connection);
        for (String s : tags){
            String inGameTag = SQLQuerys.getTagInGameTag(connection, s);
            String permission = SQLQuerys.getTagPermission(connection, s);
            this.cache.set("Tags."+s+".name", s);
            this.cache.set("Tags."+s+".inGameTag", inGameTag);
            this.cache.set("Tags."+s+".permission", permission);
        }
    }

    public String getInGameTag(String name){
        if(this.cache.contains("Tags."+name+".inGameTag")){
            return this.cache.getString("Tags."+name+".inGameTag");
        }else {
            return "";
        }
    }

    public FileConfiguration getCache(){
        return this.cache;
    }
}
