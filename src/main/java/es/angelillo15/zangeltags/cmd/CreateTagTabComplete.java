package es.angelillo15.zangeltags.cmd;

import es.angelillo15.zangeltags.database.SQLQuerys;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CreateTagTabComplete implements TabCompleter {

    List<String> arguments = new ArrayList<String>();
    List<String> arguments2 = new ArrayList<String>();
    List<String> arguments3 = new ArrayList<String>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(arguments.isEmpty()){
            arguments.add("<Tag_name>");
        }
        if(arguments2.isEmpty()){
            arguments2.add("<Tag_formated>");
        }
        if(arguments3.isEmpty()){
            arguments3.add("<permission>");
            arguments3.add("zAngelTags.tag.example");
        }

        if(args.length == 1) {
            return arguments;
        }

        if(args.length == 2) {

            return arguments2;
        }
        if(args.length == 3){
            return arguments3;
        }
        return null;
    }
}
