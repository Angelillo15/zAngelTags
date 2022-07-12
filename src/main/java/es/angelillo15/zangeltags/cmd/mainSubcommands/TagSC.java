package es.angelillo15.zangeltags.cmd.mainSubcommands;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.cmd.commandsManagers.MainCommandManager;
import es.angelillo15.zangeltags.cmd.SubCommand;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SQLQuerys;
import es.angelillo15.zangeltags.utils.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TagSC extends SubCommand {
    private ZAngelTags plugin;

    private MainCommandManager mainCommandManager;

    public TagSC(ZAngelTags plugin, MainCommandManager mainCommandManager) {
        this.plugin = plugin;
        this.mainCommandManager = mainCommandManager;
    }

    @Override
    public String getName() {
        return "tag";
    }

    @Override
    public String getDescription() {
        return "Main subcommand for tags";
    }

    @Override
    public String getSyntax() {
        return "/zat tag disable/get/list/set";
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public void execute(Player player, String[] args) {
        FileConfiguration messages = ConfigLoader.getMessageConfig().getConfig();

        String reloadMessage = messages.getString("Messages.reloadMessage");
        String noTagSelected = messages.getString("Messages.noTagSelected");
        String dontExist = messages.getString("Messages.dontExist");
        String ActualTag = messages.getString("Messages.actualTag");
        String disableTag = messages.getString("Messages.disableTag");
        String selectedTag = messages.getString("Messages.selectedTag");
        String noPerm = ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.noPerm"));

        MainHelpSC mainHelpSC = new MainHelpSC(mainCommandManager);
        if (args[0].equalsIgnoreCase("tag")) {
            if (args.length >= 2) {

                if (args[1].equalsIgnoreCase("list")) {
                    if (player.hasPermission("zAngelTags.list")) {
                        ArrayList<String> sqlList = SQLQuerys.getTagsName(plugin.getConnection());
                        for (String s : sqlList) {
                            ArrayList<String> list = new ArrayList<>();
                            String name = s;
                            String inGameTag = ColorUtils.translateColorCodes(SQLQuerys.getTagInGameTag(plugin.getConnection(), s));
                            String permission = SQLQuerys.getTagPermission(plugin.getConnection(), s);

                            List<String> message = (List<String>) messages.getList("Messages.list");
                            for (String c : message) {
                                list.add(ColorUtils.translateColorCodes(c
                                                .replace("{tag_name}", name))
                                        .replace("{tag_displayName}", inGameTag)
                                        .replace("{tag_perm}", permission));
                            }
                            for (String d : list) {
                                player.sendMessage(d);
                            }
                        }
                        return;
                    }
                }

                if (args[1].equalsIgnoreCase("set")) {
                    if (args.length >= 3) {
                        if (!(SQLQuerys.getTagInGameTag(plugin.getConnection(), args[2]).equals(""))) {
                            String tag = args[2];
                            if (player.hasPermission(SQLQuerys.getTagPermission(plugin.getConnection(), tag)) || player.hasPermission("zAngelTags.tags.all")) {
                                if (SQLQuerys.playerInDB(plugin.getConnection(), player.getUniqueId())) {
                                    SQLQuerys.updateData(plugin.getConnection(), player.getUniqueId(), tag);
                                    player.sendMessage(ColorUtils.translateColorCodes(selectedTag.replace("{tag}", args[2])));
                                } else {
                                    SQLQuerys.insertData(plugin.getConnection(), player.getUniqueId(), tag);
                                    player.sendMessage(ColorUtils.translateColorCodes(selectedTag.replace("{tag}", args[2])));
                                }
                            } else {
                                player.sendMessage(noPerm);
                            }
                        } else {
                            player.sendMessage(ColorUtils.translateColorCodes(dontExist));
                        }
                    } else {
                        mainHelpSC.execute(player, args);
                    }
                    return;
                }

                if (args[1].equalsIgnoreCase("disable")) {
                    SQLQuerys.updateData(plugin.getConnection(), player.getUniqueId(), "");
                    player.sendMessage(ColorUtils.translateColorCodes(disableTag));
                    return;
                }

                if (args[1].equalsIgnoreCase("get")) {
                    if (player.hasPermission("zAngelTags.get")) {
                        if (SQLQuerys.playerInDB(plugin.getConnection(), player.getUniqueId())) {
                            String tag = SQLQuerys.getTag(plugin.getConnection(), player.getUniqueId());
                            if (!(tag.equals(""))) {
                                player.sendMessage(ColorUtils.translateColorCodes(messages.getString("Messages.actualTag").replace("{tag}", SQLQuerys.getTagInGameTag(plugin.getConnection(), SQLQuerys.getTag(plugin.getConnection(), player.getUniqueId())))));
                            } else {
                                player.sendMessage(ColorUtils.translateColorCodes(noTagSelected));
                            }
                        }
                        return;
                    } else {
                        player.sendMessage(noPerm);
                    }
                }

                if (args[0].equalsIgnoreCase("help")) {
                    mainHelpSC.execute(player, args);
                }

            }
            mainHelpSC.execute(player, args);
        }
    }
}
