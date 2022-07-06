package es.angelillo15.zangeltags.cmd;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.database.SQLQuerys;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {
    List<String> arguments = new ArrayList<String>();
    List<String> arguments2 = new ArrayList<String>();
    List<String> arguments3 = new ArrayList<String>();

    private ZAngelTags plugin;
    public TabComplete(ZAngelTags plugin){
        this.plugin = plugin;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if(arguments.isEmpty()){
            arguments.add("reload");
            arguments.add("tag");
            arguments.add("gui");
            arguments.add("help");
        }
        if(arguments2.isEmpty()){
            arguments2.add("set");
            arguments2.add("get");
            arguments2.add("list");
            arguments2.add("disable");
        }
        if(arguments3.isEmpty()){
            arguments3.add("<Tag>");
        }

        List<String> result = new ArrayList<String>();
        if(args.length == 1) {
            for(String a : arguments){
                if(a.toLowerCase().startsWith(args[0].toLowerCase())){
                    result.add(a);
                }
            }
            return result;
        }
        List<String> result2 = new ArrayList<String>();
        if(args.length == 2 && args[0].equalsIgnoreCase("tag")) {
            for(String a : arguments2){
                if(a.toLowerCase().startsWith(args[1].toLowerCase())){
                    result2.add(a);
                }
            }
            return result2;
        }
        if(args.length == 3 && args[0].equalsIgnoreCase("tag")){
            return SQLQuerys.getTagsName(plugin.getConnection());
        }


        return null;
    }
}
