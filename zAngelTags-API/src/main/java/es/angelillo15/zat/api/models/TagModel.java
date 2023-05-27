package es.angelillo15.zat.api.models;

import com.craftmend.storm.Storm;
import com.craftmend.storm.api.StormModel;
import com.craftmend.storm.api.enums.Where;
import com.craftmend.storm.api.markers.Column;
import com.craftmend.storm.api.markers.Table;
import es.angelillo15.zat.api.TagsInstance;
import es.angelillo15.zat.api.database.PluginConnection;
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
    private String material = "NETHER_STAR";

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

    /**
     * Check if tag exists in database
     * @param name name of the tag
     * @return true if exists, false if not
     */
    public static boolean exists(String name) {
        Storm storage = PluginConnection.getStorm();
        boolean exists = false;

        try {
            exists = storage.buildQuery(TagModel.class)
                    .where("name", Where.EQUAL, name)
                    .execute()
                    .join()
                    .iterator()
                    .hasNext();
        } catch (Exception e) {
            TagsInstance.getLogger().debug("Error while checking if user exists: " + e.getMessage());
        }

        return exists;
    }

    /**
     * Get tag by name
     * @param name name of the tag
     * @return the tag if exists, null if not
     */
    public static TagModel getByName(String name) {
        Storm storage = PluginConnection.getStorm();
        TagModel tag = null;

        try {
            tag = storage.buildQuery(TagModel.class)
                    .where("name", Where.EQUAL, name)
                    .execute()
                    .join()
                    .iterator()
                    .next();
        } catch (Exception e) {
            TagsInstance.getLogger().debug("Error while getting tag by name: " + e.getMessage());
        }

        return tag;
    }

    public static TagModel getById(int id) {
        Storm storage = PluginConnection.getStorm();
        TagModel tag = null;

        try {
            tag = storage.buildQuery(TagModel.class)
                    .where("id", Where.EQUAL, id)
                    .execute()
                    .join()
                    .iterator()
                    .next();
        } catch (Exception e) {
            TagsInstance.getLogger().debug("Error while getting tag by id: " + e.getMessage());
        }

        return tag;
    }
}
