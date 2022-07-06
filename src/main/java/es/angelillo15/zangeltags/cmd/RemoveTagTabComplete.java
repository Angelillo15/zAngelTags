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


    private ZAngelTags plugin;

    public RemoveTagTabComplete(ZAngelTags plugin){
        this.plugin = plugin;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = SQLQuerys.getTagsName(plugin.getConnection());
        if(args.length == 1) {
            return arguments;
        }
        return null;
    }
}
