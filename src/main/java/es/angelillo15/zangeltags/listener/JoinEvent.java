package es.angelillo15.zangeltags.listener;

import es.angelillo15.zangeltags.TagPlayer;
import es.angelillo15.zangeltags.ZAngelTags;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    private ZAngelTags plugin;

    public JoinEvent(ZAngelTags plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void tagPlayerJoinEvent(PlayerJoinEvent e) {
        TagPlayer tp = new TagPlayer(e.getPlayer(), plugin);
        plugin.addTagPlayer(tp);
    }
}
