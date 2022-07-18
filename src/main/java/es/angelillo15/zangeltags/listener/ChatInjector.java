package es.angelillo15.zangeltags.listener;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SqlQueries;
import es.angelillo15.zangeltags.utils.ColorUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatInjector implements Listener {
    private ZAngelTags plugin;

    public ChatInjector(ZAngelTags plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e){
        FileConfiguration config = ConfigLoader.getMainConfig().getConfig();
        Player p = e.getPlayer();
        if(config.getBoolean("Config.injectChat")){
            String inGameTag = SqlQueries.getTagInGameTag(plugin.getConnection(), SqlQueries.getTag(plugin.getConnection(), p.getUniqueId()));
            if(inGameTag != null){
                if(!p.getDisplayName().contains(ColorUtils.translateColorCodes(inGameTag))){
                    p.setDisplayName(ColorUtils.translateColorCodes(p.getName() + " " +inGameTag));
                }
            }
        }
    }
}
