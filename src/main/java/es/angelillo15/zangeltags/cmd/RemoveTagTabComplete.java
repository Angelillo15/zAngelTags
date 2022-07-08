package es.angelillo15.zangeltags.cmd;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.database.SQLQuerys;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RemoveTagTabComplete implements TabCompleter {


    private ZAngelTags plugin;

    public RemoveTagTabComplete(ZAngelTags plugin){
        this.plugin = plugin;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        FileConfiguration tags = plugin.tagsCache.getCache();
        Set<String> tagsArray = tags.getConfigurationSection("Tags").getKeys(false);
        List<String> tagsArraya = new ArrayList<>(tagsArray);
        if(args.length == 1) {
            return tagsArraya;
        }
        return null;
    }
}
