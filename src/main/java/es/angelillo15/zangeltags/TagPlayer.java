package es.angelillo15.zangeltags;

import es.angelillo15.zangeltags.database.SQLQuerys;
import org.bukkit.entity.Player;

public class TagPlayer {
    private Player p;
    private ZAngelTags plugin;
    private String tag;
    private String TagFormat;
    private boolean hasTag;

    public TagPlayer(Player p, ZAngelTags plugin) {
        this.p = p;
        this.plugin = plugin;
    }

    public Player getPlayer() {
        return p;
    }

    public String getTagFormat() {
        return SQLQuerys.getTagPermission(plugin.getConnection(), getTag());
    }

    public String getPermission() {
        return SQLQuerys.getTagPermission(plugin.getConnection(), getTag());
    }


    public boolean hasTag() {
        if(SQLQuerys.playerInDB(plugin.getConnection(), p.getUniqueId())){
            return true;
        }else {
            return false;
        }
    }

    public String getTag() {
        if(SQLQuerys.playerInDB(plugin.getConnection(), p.getUniqueId())){
            return SQLQuerys.getTag(plugin.getConnection(), p.getUniqueId());
        }else {
            return "";
        }
    }

    public void setTag(String tag) {
        if(SQLQuerys.playerInDB(plugin.getConnection(), p.getUniqueId())){
            SQLQuerys.updateData(plugin.getConnection(), p.getUniqueId(), tag);
        }else {
            SQLQuerys.updateData(plugin.getConnection(), p.getUniqueId(), tag);
        }
    }
}
