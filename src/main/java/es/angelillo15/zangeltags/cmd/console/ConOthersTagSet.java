package es.angelillo15.zangeltags.cmd.console;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.cmd.SubCommand;
import es.angelillo15.zangeltags.cmd.commandsmanagers.MainCommandManager;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SqlQueries;
import es.angelillo15.zangeltags.utils.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConOthersTagSet extends SubCommand {
    ZAngelTags plugin;
    MainCommandManager mainCommandManager;

    public ConOthersTagSet(ZAngelTags plugin, MainCommandManager mainCommandManager) {
        this.plugin = plugin;
        this.mainCommandManager = mainCommandManager;
    }

    @Override
    public String getName() {
        return "player";
    }

    @Override
    public String getDescription() {
        return "Set a player tag";
    }

    @Override
    public String getSyntax() {
        return "/zat player <Player> set <tag>";
    }

    @Override
    public String getPermission() {
        return "zAngelTags.admin";
    }

    @Override
    public void execute(Player player, String[] args) {
        FileConfiguration messages = ConfigLoader.getMessageConfig().getConfig();

        String dontExist = messages.getString("Messages.dontExist");
        String selectedTag = messages.getString("Messages.selectedTag");
        String noPerm = ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.noPerm"));
        String offline = messages.getString("Messages.offline");
        if (args.length >= 4) {
            if (!(SqlQueries.getTagInGameTag(plugin.getConnection(), args[3]).equals(""))) {
                String tag = args[3];
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        if (SqlQueries.playerInDB(plugin.getConnection(), target.getUniqueId())) {
                            SqlQueries.updateData(plugin.getConnection(), target.getUniqueId(), tag);

                        } else {
                            SqlQueries.insertData(plugin.getConnection(), target.getUniqueId(), tag);
                        }
                        Bukkit.getConsoleSender().sendMessage((ColorUtils.translateColorCodes(selectedTag.replace("{tag}", args[3]))));
                        target.sendMessage(ColorUtils.translateColorCodes(selectedTag.replace("{tag}", args[3])));
                    } else {
                        Bukkit.getConsoleSender().sendMessage(ColorUtils.translateColorCodes(offline));
                    }
            } else {
                 Bukkit.getConsoleSender().sendMessage(ColorUtils.translateColorCodes(dontExist));
            }
        }
    }
}