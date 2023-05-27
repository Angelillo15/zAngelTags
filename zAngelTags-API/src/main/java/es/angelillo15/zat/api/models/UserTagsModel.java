package es.angelillo15.zat.api.models;

import com.craftmend.storm.Storm;
import com.craftmend.storm.api.StormModel;
import com.craftmend.storm.api.enums.KeyType;
import com.craftmend.storm.api.enums.Where;
import com.craftmend.storm.api.markers.Column;
import com.craftmend.storm.api.markers.Table;
import es.angelillo15.zat.api.TagsInstance;
import es.angelillo15.zat.api.database.PluginConnection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

@Data
@Table(name = "zat_user_tags")
@EqualsAndHashCode(callSuper = true)
public class UserTagsModel extends StormModel {
    @Column(
            keyType = KeyType.FOREIGN,
            references = {UserModel.class}
    )
    private Integer userModelId;

    @Column(
            keyType = KeyType.FOREIGN,
            references = {TagModel.class}
    )
    private Integer tagModelId;

    @SneakyThrows
    public UserModel getUserModel() {
        Storm storm = PluginConnection.getStorm();
        UserModel userModel = null;

        try {

            userModel = storm.buildQuery(UserModel.class)
                    .where("id", Where.EQUAL, userModelId)
                    .execute()
                    .join()
                    .iterator()
                    .next();
        } catch (Exception e) {
            TagsInstance.getLogger().debug("Error while getting user model: " + e.getMessage());
        }

        return userModel;
    }

    @SneakyThrows
    public TagModel getTagModel() {
        Storm storm = PluginConnection.getStorm();

        TagModel tagModel = null;

        try {
            tagModel = storm.buildQuery(TagModel.class)
                    .where("id", Where.EQUAL, tagModelId)
                    .execute()
                    .join()
                    .iterator()
                    .next();
        } catch (Exception e) {
            TagsInstance.getLogger().debug("Error while getting tag model: " + e.getMessage());
        }

        return tagModel;
    }
}
