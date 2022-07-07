package es.angelillo15.zangeltags.cmd;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SQLQuerys;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateTag implements CommandExecutor {
    private ZAngelTags plugin;

    public CreateTag(ZAngelTags plugin) {
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
                if (args.length < 3) {
                    help(p);
                } else {
                    if (p.hasPermission("zAngelTags.admin")) {
                        if (!SQLQuerys.tagExist(plugin.getConnection(), args[0])) {
                            SQLQuerys.createTag(plugin.getConnection(), args[0], args[1], args[2]);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getMessageConfig().getConfig().getString("Messages.createdTag")));
                            plugin.reloadCache();
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getMessageConfig().getConfig().getString("Messages.alreadyExist")));
                        }
                    }

                }
            }
        }
        return false;
    }

    public void help(Player p) {
        p.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "&6----------------zAngelTags----------------"));
        p.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "&bAvailable Command:"));
        p.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "&b/createTag <name> <tag_formatted> <permission>"));
    }
}
