package es.angelillo15.zangeltags.listener;

import es.angelillo15.zangeltags.ZAngelTags;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LeaveEvent implements Listener {
    private ZAngelTags plugin;

    public LeaveEvent(ZAngelTags plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void tagPlayerJoinEvent(PlayerJoinEvent e) {

    }
}
