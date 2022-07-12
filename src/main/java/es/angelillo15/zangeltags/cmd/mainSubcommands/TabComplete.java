package es.angelillo15.zangeltags.cmd.mainSubcommands;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.database.SQLQuerys;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TabComplete implements TabCompleter {
    List<String> arguments = new ArrayList<String>();
    List<String> arguments2 = new ArrayList<String>();
    List<String> arguments3 = new ArrayList<String>();
    List<String> arguments4 = new ArrayList<String>();




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
            arguments.add("player");
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
        if(arguments4.isEmpty()){
            arguments2.add("set");
            arguments2.add("get");
            arguments2.add("list");
            arguments2.add("disable");
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
        if(args.length == 3 && args[0].equalsIgnoreCase("player")) {
            return arguments2;
        }
        FileConfiguration tags = plugin.tagsCache.getCache();
        Set<String> tagsArray = tags.getConfigurationSection("Tags").getKeys(false);
        List<String> tagsArraya = new ArrayList<>(tagsArray);


        if(args.length == 3 && args[0].equalsIgnoreCase("tag")){
            return tagsArraya;
        }
        return null;
    }
}
