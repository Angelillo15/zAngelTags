package es.angelillo15.zat.api;

import es.angelillo15.zat.api.models.TagModel;
import es.angelillo15.zat.api.models.UserModel;
import es.angelillo15.zat.api.models.UserTagsModel;
import org.bukkit.entity.Player;

public interface ITagPlayer {
    Player getPlayer();
    void setTag(TagModel tag);

    void removeTag();

    boolean hasTag();

    boolean hasTag(TagModel tag);

    UserModel getUserModel();

    TagModel getTag();

    UserTagsModel getUserTagsModel();

    void reloadData();
}
