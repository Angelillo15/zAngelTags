package es.angelillo15.zangeltags.cmd.commandsmanagers;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.cmd.SubCommand;
import es.angelillo15.zangeltags.cmd.console.ConOthersTagDisable;
import es.angelillo15.zangeltags.cmd.console.ConOthersTagGet;
import es.angelillo15.zangeltags.cmd.console.ConOthersTagSet;
import es.angelillo15.zangeltags.cmd.console.ConReloadSC;
import es.angelillo15.zangeltags.cmd.mainsubcommands.*;
import es.angelillo15.zangeltags.gui.TagsGui;
import es.angelillo15.zangeltags.utils.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MainCommandManager implements CommandExecutor {
    private ArrayList<SubCommand> subcommands = new ArrayList<>();

    private ArrayList<SubCommand> consoleSubcommands = new ArrayList<>();

    private ZAngelTags plugin;

    public MainCommandManager(ZAngelTags plugin) {
        this.plugin = plugin;
        this.subcommands.add(new ReloadSC(plugin));
        this.subcommands.add(new MainHelpSC(this));
        this.subcommands.add(new MainHelpSC(this));
        this.subcommands.add(new GuiSC(plugin));
        this.subcommands.add(new TagSC(plugin, this));
        this.subcommands.add(new OthersTagSet(plugin, this));
        this.subcommands.add(new OthersTagGet(plugin, this));
        this.subcommands.add(new OthersTagDisable(plugin));

        this.consoleSubcommands.add(new ConReloadSC(plugin));
        this.consoleSubcommands.add(new ConOthersTagDisable(plugin));
        this.consoleSubcommands.add(new ConOthersTagGet(plugin, this));
        this.consoleSubcommands.add(new ConOthersTagSet(plugin, this));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length > 0) {
                for (int i = 0; i < getSubcommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                        getSubcommands().get(i).execute(p, args);
                    }
                }
            } else {
                TagsGui tagsGui = new TagsGui(plugin, p);
            }

        } else {
            if (args.length > 0) {
                for (int i = 0; i < getConsoleSubcommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getConsoleSubcommands().get(i).getName())) {
                        getConsoleSubcommands().get(i).execute(null, args);
                    }
                }
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6----------------zAngelTags----------------"));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bAvailable Commands:"));
                for(SubCommand csb : getConsoleSubcommands()){
                    Bukkit.getConsoleSender().sendMessage(ColorUtils.translateColorCodes("&b" + csb.getSyntax() + " &8&l» &f " + csb.getDescription()));
                }
            }
        }
        return true;
    }

    public ArrayList<SubCommand> getSubcommands() {
        return subcommands;
    }

    public ArrayList<SubCommand> getConsoleSubcommands(){
        return consoleSubcommands;
    }


}
