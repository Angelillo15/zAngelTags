package es.angelillo15.zat;

import es.angelillo15.zat.api.ITagPlayer;
import es.angelillo15.zat.api.models.TagModel;
import es.angelillo15.zat.api.models.UserModel;
import es.angelillo15.zat.api.models.UserTagsModel;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class TagPlayer implements ITagPlayer {
    private final Player player;
    private TagModel tag;
    private UserModel userModel;
    private UserTagsModel userTagsModel;
    public TagPlayer(Player player) {
        this.player = player;
        reloadData();
    }

    @Override
    public void reloadData() {
        this.userModel = UserModel.getUser(player.getUniqueId());
        this.userTagsModel = userModel.getTags();
        if (userTagsModel.getTagModelId() != null) {
            this.tag = userTagsModel.getTagModel();
        }
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    @Nullable
    public TagModel getTag() {
        return tag;
    }

    @Override
    public void setTag(TagModel tag) {
        this.tag = tag;
    }

    @Override
    public void removeTag() {
        this.tag = null;
    }

    @Override
    public boolean hasTag() {
        return getTag() != null;
    }

    @Override
    public boolean hasTag(TagModel tag) {
        return getTag() != null && getTag().equals(tag);
    }

    @Override
    public UserModel getUserModel() {
        return userModel;
    }

    @Override
    public UserTagsModel getUserTagsModel() {
        return userTagsModel;
    }
}
