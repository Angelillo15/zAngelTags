package es.angelillo15.zat.api.models;

import com.craftmend.storm.api.enums.Where;
import com.craftmend.storm.api.markers.Column;
import com.craftmend.storm.api.markers.Table;
import com.craftmend.storm.Storm;
import com.craftmend.storm.api.StormModel;
import es.angelillo15.zat.api.TagsInstance;
import es.angelillo15.zat.api.database.PluginConnection;

import java.util.UUID;

@Table(name = "zat_user_data")
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

    /**
     * Get the UUID of the user
     * @param where UUID or username
     * @param value UUID or username value
     * @return the UUID of the user
     */
    public static boolean exists(String where, String value) {
        Storm storage = PluginConnection.getStorm();
        boolean exists = false;

        try {
            exists = storage.buildQuery(UserModel.class)
                    .where(where, Where.EQUAL, value)
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
     * Check if user exists in database
     * @param uuid UUID of the user
     * @return true if exists, false if not
     */
    public static boolean exits(UUID uuid) {
        return exists("UUID", uuid.toString());
    }

    /**
     * Check if user exists in database
     * @param username username of the user
     * @return true if exists, false if not
     */
    public static boolean exits(String username) {
        return exists("username", username);
    }

    /**
     * Get user from database
     * @param where UUID or username
     * @param value UUID or username value
     * @return the {@link UserModel} instance
     */
    public static UserModel getUser(String where, String value) {
        Storm storage = PluginConnection.getStorm();
        UserModel user = null;

        try {
            user = storage.buildQuery(UserModel.class)
                    .where(where, Where.EQUAL, value)
                    .execute()
                    .join()
                    .iterator()
                    .next();
        } catch (Exception e) {
            TagsInstance.getLogger().debug("Error while getting user: " + e.getMessage());
        }

        return user;
    }

    /**
     * Get user from database
     * @param uuid UUID of the user
     * @return the {@link UserModel} instance
     */
    public static UserModel getUser(UUID uuid) {
        return getUser("UUID", uuid.toString());
    }

    /**
     * Get user from database
     * @param username username of the user
     * @return the {@link UserModel} instance
     */
    public static UserModel getUser(String username) {
        return getUser("username", username);
    }
}
