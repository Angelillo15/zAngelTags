package es.angelillo15.zat;

import es.angelillo15.zat.api.ITagPlayer;
import es.angelillo15.zat.api.models.TagModel;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class TagPlayer implements ITagPlayer {
    private final Player player;
    private TagModel tag;
    public TagPlayer(Player player) {
        this.player = player;
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
}
