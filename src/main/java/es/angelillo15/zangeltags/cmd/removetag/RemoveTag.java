package es.angelillo15.zangeltags.cmd.removetag;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SqlQueries;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveTag implements CommandExecutor {
    private ZAngelTags plugin;

    public RemoveTag(ZAngelTags plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getPrefix() + "&b Command will be soon able at console"));
        } else {
            Player p = (Player) sender;
            if (args.length < 1) {
                help(p);
            } else {
                if (p.hasPermission("zAngelTags.admin")) {
                    if (SqlQueries.tagExist(plugin.getConnection(), args[0])) {
                        SqlQueries.removeTag(plugin.getConnection(), args[0]);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getMessageConfig().getConfig().getString("Messages.deletedTag")));
                        plugin.reloadCache();

                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getMessageConfig().getConfig().getString("Messages.dontExist")));
                    }
                }
            }
        }
        return false;
    }

    public void help(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6----------------zAngelTags----------------"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bAvailable Command:"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/removeTag <name>"));
    }
}
