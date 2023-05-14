package es.angelillo15.zat.api.managers;

import es.angelillo15.zat.api.ITagPlayer;
import es.angelillo15.zat.api.TagsInstance;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class TagsPlayerManager {
    /**
     * HashMap of all the players
     * Key: UUID of the player
     * Value: ITagPlayer instance
     * @return HashMap of all the players
     */
    @Getter
    private static final HashMap<UUID, ITagPlayer> players = new HashMap<>();

    /**
     * Add a player to the HashMap
     * @param player ITagPlayer object
     */
    public static void addPlayer(ITagPlayer player) {
        players.put(player.getPlayer().getUniqueId(), player);
    }

    /**
     * Remove a player from the HashMap
     * @param player ITagPlayer object
     */
    public static void removePlayer(ITagPlayer player) {
        players.remove(player.getPlayer().getUniqueId());
    }

    /**
     * Get a player from the HashMap
     * @param uuid UUID of the player
     * @return ITagPlayer object
     */
    public static ITagPlayer getPlayer(UUID uuid) {
        if (!hasPlayer(uuid)) {
            addPlayer(
                    TagsInstance.getInstance().createTagPlayer(Bukkit.getPlayer(uuid))
            );
        }
        return players.get(uuid);
    }

    /**
     * Check if a player exists in the HashMap
     * @param uuid UUID of the player
     * @return true if exists, false if not
     */
    public static boolean hasPlayer(UUID uuid) {
        return players.containsKey(uuid);
    }
}
