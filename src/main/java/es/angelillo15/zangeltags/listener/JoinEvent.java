package es.angelillo15.zangeltags.listener;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SqlQueries;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    private ZAngelTags plugin;
    public JoinEvent(ZAngelTags plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(!(SqlQueries.playerInDB(plugin.getConnection(), e.getPlayer().getUniqueId()))){
            Bukkit.getConsoleSender().sendMessage("xd");
            SqlQueries.insertData(plugin.getConnection(), e.getPlayer().getUniqueId(), ConfigLoader.getMainConfig().getConfig().getString("Config.defaultTagName"));
        }
    }
}
