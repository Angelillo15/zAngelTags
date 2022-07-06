package es.angelillo15.zangeltags;

import es.angelillo15.zangeltags.database.SQLQuerys;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;


public class PlaceHolderApiExtensions extends PlaceholderExpansion {

    private ZAngelTags plugin;

    public PlaceHolderApiExtensions(ZAngelTags plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }


    @Override
    public String getAuthor(){
        return "Angelillo15";
    }


    @Override
    public String getIdentifier(){
        return "zangeltags";
    }


    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }


    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }

        if(identifier.equalsIgnoreCase("tag")) {
            if(SQLQuerys.getTag(plugin.getConnection(), player.getUniqueId()) == "null"){
                return "";
            }else {
                return SQLQuerys.getTagInGameTag(plugin.getConnection(), SQLQuerys.getTag(plugin.getConnection(), player.getUniqueId()));
            }

        }

        return null;
    }
}