package es.angelillo15.zangeltags.cmd;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.config.ConfigLoader;
import es.angelillo15.zangeltags.database.SQLQuerys;
import es.angelillo15.zangeltags.gui.TagsGui;
import es.angelillo15.zangeltags.utils.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements CommandExecutor {
    private ZAngelTags plugin;

    public MainCommand(ZAngelTags plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length < 1) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6----------------zAngelTags----------------"));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bAvailable Commands:"));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bzAngelTags reload &8&l» &f To reload the plugin"));
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reload();
                } else {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6----------------zAngelTags----------------"));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bAvailable Commands:"));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bzAngelTags reload &8&l» &f To reload the plugin"));
                }
            }
        } else {
            Player p = (Player) sender;
            FileConfiguration messages = ConfigLoader.getMessageConfig().getConfig();

            String reloadMessage = messages.getString("Messages.reloadMessage");
            String noTagSelected = messages.getString("Messages.noTagSelected");
            String dontExist = messages.getString("Messages.dontExist");
            String ActualTag = messages.getString("Messages.actualTag");
            String disableTag = messages.getString("Messages.disableTag");
            String selectedTag = messages.getString("Messages.selectedTag");
            String noPerm = ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.noPerm"));

            if (args.length < 1) {
                TagsGui tagsGui = new TagsGui(plugin, p);
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reload();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getPrefix() + "&bSuccessfully reload"));
                    return true;
                }

                if(args[0].equalsIgnoreCase("gui")){
                    TagsGui tagsGui = new TagsGui(plugin, p);
                }

                if(args[0].equalsIgnoreCase("test")){
                    p.sendMessage(ColorUtils.translateColorCodes("#FBAAFE Hola &#FBAAFE Hola"));
                }


                if (args[0].equalsIgnoreCase("tag")) {
                    if (args.length >= 2) {

                        if (args[1].equalsIgnoreCase("list")) {
                            if(p.hasPermission("zAngelTags.list")){
                                ArrayList<String> sqlList = SQLQuerys.getTagsName(plugin.getConnection());
                                for (String s : sqlList) {
                                    ArrayList<String> list = new ArrayList<>();
                                    String name = s;
                                    String inGameTag = ColorUtils.translateColorCodes( SQLQuerys.getTagInGameTag(plugin.getConnection(), s));
                                    String permission = SQLQuerys.getTagPermission(plugin.getConnection(), s);

                                    List<String> message = (List<String>) messages.getList("Messages.list");
                                    for (String c : message) {
                                        list.add(ColorUtils.translateColorCodes( c
                                                        .replace("{tag_name}", name))
                                                .replace("{tag_displayName}", inGameTag)
                                                .replace("{tag_perm}", permission));
                                    }
                                    for (String d : list) {
                                        p.sendMessage(d);
                                    }
                                }
                                return true;
                            }
                        }

                        if (args[1].equalsIgnoreCase("set")) {
                            if (args.length >= 3) {
                                if (!(SQLQuerys.getTagInGameTag(plugin.getConnection(), args[2]).equals(""))) {
                                    String tag = args[2];
                                    if(p.hasPermission(SQLQuerys.getTagPermission(plugin.getConnection(), tag)) || p.hasPermission("zAngelTags.tags.all")){
                                        if (SQLQuerys.playerInDB(plugin.getConnection(), p.getUniqueId())) {
                                            SQLQuerys.updateData(plugin.getConnection(), p.getUniqueId(), tag);
                                            p.sendMessage(ColorUtils.translateColorCodes( selectedTag.replace("{tag}", args[2])));
                                        } else {
                                            SQLQuerys.insertData(plugin.getConnection(), p.getUniqueId(), tag);
                                            p.sendMessage(ColorUtils.translateColorCodes( selectedTag.replace("{tag}", args[2])));
                                        }
                                    }else {
                                        p.sendMessage(noPerm);
                                    }
                                } else {
                                    p.sendMessage(ColorUtils.translateColorCodes( dontExist));
                                }
                            } else {
                                help(p);
                            }
                            return true;
                        }

                        if(args[1].equalsIgnoreCase("disable")){
                            SQLQuerys.updateData(plugin.getConnection(), p.getUniqueId(), "");
                            p.sendMessage(ColorUtils.translateColorCodes( disableTag));
                            return true;
                        }

                        if(args[1].equalsIgnoreCase("get")){
                            if(p.hasPermission("zAngelTags.get")){
                                if(SQLQuerys.playerInDB(plugin.getConnection(), p.getUniqueId())){
                                    String tag = SQLQuerys.getTag(plugin.getConnection(), p.getUniqueId());
                                    if(!(tag.equals(""))){
                                        p.sendMessage(ColorUtils.translateColorCodes( messages.getString("Messages.actualTag").replace("{tag}", SQLQuerys.getTagInGameTag(plugin.getConnection(), SQLQuerys.getTag(plugin.getConnection(), p.getUniqueId()))) ));
                                    }else {
                                        p.sendMessage(ColorUtils.translateColorCodes( noTagSelected));
                                    }
                                }
                                return true;
                            }else {
                                p.sendMessage(noPerm);
                            }
                        }

                        if(args[0].equalsIgnoreCase("help")){
                            help(p);
                        }

                    }
                    help(p);
                    return true;
                }
            }
        }
        return false;
    }

    public void help(Player p) {
        p.sendMessage(ColorUtils.translateColorCodes( "&6----------------zAngelTags----------------"));
        p.sendMessage(ColorUtils.translateColorCodes( "&bAvailable Commands:"));
        p.sendMessage(ColorUtils.translateColorCodes( "&bzAngelTags reload &8&l» &f To reload the plugin"));
        p.sendMessage(ColorUtils.translateColorCodes( "&bzAngelTags tag get &8&l» &f Return your selected"));
        p.sendMessage(ColorUtils.translateColorCodes( "&bzAngelTags tag list &8&l» &f list all the tags"));
        p.sendMessage(ColorUtils.translateColorCodes( "&bzAngelTags tag set <tag> &8&l» &f set a tag"));
        p.sendMessage(ColorUtils.translateColorCodes( "&bzAngelTags gui &8&l» &f open plugin gui "));
        p.sendMessage(ColorUtils.translateColorCodes( "&bzAngelTags tag disable &8&l» &f open plugin gui "));
        p.sendMessage(ColorUtils.translateColorCodes( "&bzAngelTags help &8&l» &f view the help of the plugin "));

    }
}
