package es.angelillo15.zat.listeners;

import es.angelillo15.zat.TagPlayer;
import es.angelillo15.zat.api.ITagPlayer;
import es.angelillo15.zat.api.database.PluginConnection;
import es.angelillo15.zat.api.managers.TagsPlayerManager;
import es.angelillo15.zat.api.models.UserModel;
import es.angelillo15.zat.api.models.UserTagsModel;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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

            UserTagsModel userTagsModel = new UserTagsModel();
            userTagsModel.setUserModelId(userModel.getId());
            userTagsModel.setTagModelId(null);
            PluginConnection.getStorm().save(userTagsModel);
        }

        TagPlayer tagPlayer = new TagPlayer(player);

        TagsPlayerManager.addPlayer(tagPlayer);
    }

    @SneakyThrows
    @EventHandler
    public void onUserQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ITagPlayer tagPlayer = TagsPlayerManager.getPlayer(player.getUniqueId());
        TagsPlayerManager.removePlayer(tagPlayer);
    }

}
