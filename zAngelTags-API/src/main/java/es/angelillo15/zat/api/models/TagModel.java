package es.angelillo15.zat.api.models;

import com.craftmend.storm.api.StormModel;
import com.craftmend.storm.api.markers.Column;
import com.craftmend.storm.api.markers.Table;
import lombok.Data;

@Data
@Table(name = "zat_tags")
public class TagModel extends StormModel {
    @Column(
            length = 32,
            unique = true
    )
    private String name;

    @Column(
            length = 1024
    )
    private String displayName;

    @Column(
            length = 64
    )
    private String permission;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TagModel) {
            TagModel tag = (TagModel) obj;
            return tag.getName().equals(getName());
        }
        return false;
    }
}
