package es.angelillo15.zat.api;

import es.angelillo15.zat.api.models.TagModel;
import org.bukkit.entity.Player;

public interface ITagPlayer {
    Player getPlayer();

    TagModel getTag();

    void setTag(TagModel tag);

    void removeTag();

    boolean hasTag();

    boolean hasTag(TagModel tag);
}
