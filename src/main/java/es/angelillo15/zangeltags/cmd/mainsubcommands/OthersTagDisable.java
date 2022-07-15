package es.angelillo15.zangeltags.cmd.mainsubcommands;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.cmd.SubCommand;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SQLQuerys;
import es.angelillo15.zangeltags.utils.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class OthersTagDisable extends SubCommand {
    private ZAngelTags plugin;

    public OthersTagDisable(ZAngelTags plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Player";
    }

    @Override
    public String getDescription() {
        return "Command to disable tag to a player";
    }

    @Override
    public String getSyntax() {
        return "/zat player <Player> disable";
    }

    @Override
    public String getPermission() {
        return "zAngelTags.admin";
    }

    @Override
    public void execute(Player player, String[] args) {
        FileConfiguration messages = ConfigLoader.getMessageConfig().getConfig();

        String noPerm = ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.noPerm"));
        String offline = messages.getString("Messages.offline");
        String disableTag = messages.getString("Messages.disableTag");
        if (player.hasPermission(getPermission())) {
            if (args.length == 3) {
                if (args[2].equalsIgnoreCase("disable")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        SQLQuerys.updateData(plugin.getConnection(), player.getUniqueId(), "");
                        player.sendMessage(ColorUtils.translateColorCodes(disableTag));
                    } else {
                        player.sendMessage(ColorUtils.translateColorCodes(offline));
                    }
                }
            }
        } else {
            player.sendMessage(ColorUtils.translateColorCodes(noPerm));
        }
    }
}
