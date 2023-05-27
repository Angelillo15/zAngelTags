package es.angelillo15.zat.papi;

import es.angelillo15.zat.api.ITagPlayer;
import es.angelillo15.zat.api.managers.TagsPlayerManager;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class RequestDispatcher {
    public static String onRequest(OfflinePlayer player, @NotNull String params) {
        ITagPlayer tagPlayer = TagsPlayerManager.getPlayer(player.getUniqueId());

        if (params.startsWith("tag")) {
            return tagPlayer.getTag() != null ? tagPlayer.getTag().getName() : "";
        }

        return "";
    }
}
