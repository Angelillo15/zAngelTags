package es.angelillo15.zat.api.models;

import com.craftmend.storm.api.StormModel;
import com.craftmend.storm.api.enums.KeyType;
import com.craftmend.storm.api.markers.Column;
import com.craftmend.storm.api.markers.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "zat_user_tags")
@EqualsAndHashCode(callSuper = true)
public class UserTagsModel extends StormModel {
    @Column(
            keyType = KeyType.FOREIGN,
            references = {UserModel.class}
    )
    private Integer userModel;

    @Column(
            keyType = KeyType.FOREIGN,
            references = {TagModel.class}
    )
    private Integer tagModel;
}
