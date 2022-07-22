package es.angelillo15.zangeltags.cmd.createtag;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SqlQueries;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateTag implements CommandExecutor {
    private final ZAngelTags plugin;

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
                        if (!SqlQueries.tagExist(plugin.getConnection(), args[0])) {
                            String tag = "";
                            for (int i = 2; i < args.length; i++){
                                if(tag == ""){
                                    tag = tag + args[i];
                                }else {
                                    tag = tag + " " + args[i];
                                }

                            }
                            SqlQueries.createTag(plugin.getConnection(), args[0], tag, args[1]);
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
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6----------------zAngelTags----------------"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bAvailable Command:"));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/createTag <name> <tag_formatted> <permission>"));
    }
}
