package es.angelillo15.zangeltags.cmd;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.database.SQLQuerys;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RemoveTagTabComplete implements TabCompleter {

    private Connection connection;
    public RemoveTagTabComplete(Connection connection){
        this.connection = connection;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = SQLQuerys.getTagsName(this.connection);
        if(args.length == 1) {
            return arguments;
        }
        return null;
    }
}
