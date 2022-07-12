package es.angelillo15.zangeltags.cmd.mainSubcommands;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.cmd.SubCommand;
import es.angelillo15.zangeltags.cmd.commandsManagers.MainCommandManager;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SQLQuerys;
import es.angelillo15.zangeltags.utils.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class OthersTagGet extends SubCommand {
    ZAngelTags plugin;
    MainCommandManager mainCommandManager;


    public OthersTagGet(ZAngelTags plugin, MainCommandManager mainCommandManager) {
        this.plugin = plugin;
        this.mainCommandManager = mainCommandManager;
    }

    @Override
    public String getName() {
        return "player";
    }

    @Override
    public String getDescription() {
        return "get a player tag";
    }

    @Override
    public String getSyntax() {
        return "/zat player <Player> get";
    }

    @Override
    public String getPermission() {
        return "zAngelTags.admin";
    }

    @Override
    public void execute(Player player, String[] args) {
        FileConfiguration messages = ConfigLoader.getMessageConfig().getConfig();

        String noTagSelected = messages.getString("Messages.noTagSelected");
        String noPerm = ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.noPerm"));
        String offline = messages.getString("Messages.offline");

        if (player.hasPermission(getPermission())) {
            if (args.length == 3) {
                if (args[2].equalsIgnoreCase("get")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (!(target == null)) {
                        String tag = SQLQuerys.getTag(plugin.getConnection(), player.getUniqueId());
                        if (!(tag.equals(""))) {
                            player.sendMessage(ColorUtils.translateColorCodes(messages.getString("Messages.actualTag").replace("{tag}", SQLQuerys.getTagInGameTag(plugin.getConnection(), SQLQuerys.getTag(plugin.getConnection(), target.getUniqueId())))));
                        } else {
                            player.sendMessage(ColorUtils.translateColorCodes(noTagSelected));
                        }
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
