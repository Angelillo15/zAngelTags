package es.angelillo15.zat.listeners;

import es.angelillo15.zat.api.database.PluginConnection;
import es.angelillo15.zat.api.models.UserModel;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UserDataListener implements Listener {
    @SneakyThrows
    @EventHandler
    public void onUserJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        boolean userExists = UserModel.exists(player.getUniqueId());

        if (!userExists) {
            UserModel userModel = new UserModel();
            userModel.setUUID(player.getUniqueId().toString());
            userModel.setUsername(player.getName());
            PluginConnection.getStorm().save(userModel);
        }
    }
}
