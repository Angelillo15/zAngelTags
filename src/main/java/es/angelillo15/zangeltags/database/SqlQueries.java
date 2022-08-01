package es.angelillo15.zangeltags.database;

import org.bukkit.Bukkit;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class SqlQueries {
    public static boolean userDatatableCreated(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("SHOW TABLES LIKE 'userData';");
            ResultSet result = statement.executeQuery();
            boolean rb = result.next();
            statement.close();
            return rb;
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
        }

        return false;
    }

    public static boolean tagsTableCreated(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("SHOW TABLES LIKE 'tags';");
            ResultSet result = statement.executeQuery();
            boolean rb = result.next();
            statement.close();
            return rb;
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
        }

        return false;
    }

    public static void createUserDataTables(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `userData` (`ID` INT(11) NOT NULL AUTO_INCREMENT,`UUID` VARCHAR(50) NULL DEFAULT '' COLLATE 'utf8mb4_general_ci',`Tag` VARCHAR(100) NULL DEFAULT '' COLLATE 'utf8mb4_general_ci',PRIMARY KEY (`ID`) USING BTREE)");
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
        }
    }

    public static void createTagsTables(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `tags` (`name` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',`inGameTag` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',`permission` VARCHAR(200) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci');");
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createUserDataSQLiteTable(Connection connection){
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS 'userData' ('ID' INTEGER, 'UUID' TEXT, 'Tag' TEXT, PRIMARY KEY('ID' AUTOINCREMENT));");
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createTagsDataSQLiteTable(Connection connection){
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS 'tags' ('name' TEXT, 'inGameTag' TEXT, 'permission' TEXT);");
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void insertData(Connection connection, UUID uuid, String tag) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `userData` (`ID`, `UUID`, `Tag`) VALUES (null, ?, ?);");
            statement.setString(1, uuid.toString());
            statement.setString(2, tag);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean playerInDB(Connection connection, UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM userData WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            boolean rb = result.next();
            statement.close();

            return rb;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateData(Connection connection, UUID uuid, String tag) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `userData` SET `Tag` = ? WHERE UUID=?");
            statement.setString(1, tag);
            statement.setString(2, uuid.toString());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTag(Connection connection, UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT Tag From `userData` WHERE UUID=?");

            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String tag = result.getString("Tag");

                statement.close();
                return tag;
            }

        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            throw new RuntimeException(e);

        }
        return "";

    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
        }
    }

    public static ArrayList<String> getTagsName(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `tags`");
            ResultSet result = statement.executeQuery();
            ArrayList<String> list = new ArrayList<String>();
            while (result.next()) {
                list.add(result.getString(1));
            }
            statement.close();
            return list;
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> getTagsInGameTag(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `tags`");
            ResultSet result = statement.executeQuery();
            ArrayList<String> list = new ArrayList<String>();
            while (result.next()) {
                list.add(result.getString(2));
            }
            statement.close();
            return list;
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> getTagsPermission(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `tags`");
            ResultSet result = statement.executeQuery();
            ArrayList<String> list = new ArrayList<String>();
            while (result.next()) {
                list.add(result.getString(2));
            }
            statement.close();
            return list;
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    public static String getTagInGameTag(Connection connection, String tag){
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `tags` WHERE (name=?)");
            statement.setString(1, tag);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                String resultString = result.getString("inGameTag");
                statement.close();
                return resultString;
            }else {
                return "";
            }

        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            throw new RuntimeException(e);
        }
    }
    public static String getTagPermission(Connection connection, String tag){
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `tags` WHERE (name=?)");
            statement.setString(1, tag);
            ResultSet result = statement.executeQuery();
            String rs = result.getString("permission")
            if(result.next()){
                statement.close();
                return rs;
            }else {
                statement.close();
                return "";
            }

        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    public static void createTag(Connection connection, String name, String inGameTag, String permission){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `tags` (`name`, `inGameTag`, `permission`) VALUES (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, inGameTag);
            statement.setString(3, permission);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void removeTag(Connection connection, String tag){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tags WHERE (name=?)");
            statement.setString(1, tag);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean tagExist(Connection connection, String name){
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `tags` WHERE (name=?)");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            boolean rb = resultSet.next();
            statement.close();
            return rb;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
