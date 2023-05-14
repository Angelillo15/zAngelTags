package es.angelillo15.zat.api.models;

import com.craftmend.storm.api.enums.Where;
import com.craftmend.storm.api.markers.Column;
import com.craftmend.storm.api.markers.Table;
import com.craftmend.storm.Storm;
import com.craftmend.storm.api.StormModel;
import es.angelillo15.zat.api.TagsInstance;
import es.angelillo15.zat.api.database.PluginConnection;

@Table(name="zat_user_data")
public class UserModel extends StormModel {
    public static final String UNKNOWN = "unknown";
    @Column(
            name = "UUID",
            length = 36,
            unique = true
    )
    private String UUID = UNKNOWN;
    @Column(
            name = "username",
            length = 16
    )
    private String username = UNKNOWN;

    public static boolean exists(String where, String value) {
        Storm storage = PluginConnection.getStorm();
        boolean exists = false;

        try {
            exists = storage.buildQuery(UserModel.class)
                    .where(where, Where.EQUAL ,value)
                    .execute()
                    .join()
                    .iterator()
                    .hasNext();
        } catch (Exception e) {
            TagsInstance.getLogger().debug("Error while checking if user exists: " + e.getMessage());
        }

        return exists;
    }
}
