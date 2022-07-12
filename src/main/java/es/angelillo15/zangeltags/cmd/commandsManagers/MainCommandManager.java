package es.angelillo15.zangeltags.cmd.commandsManagers;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.cmd.SubCommand;
import es.angelillo15.zangeltags.cmd.mainSubcommands.GuiSC;
import es.angelillo15.zangeltags.cmd.mainSubcommands.MainHelpSC;
import es.angelillo15.zangeltags.cmd.mainSubcommands.ReloadSC;
import es.angelillo15.zangeltags.cmd.mainSubcommands.TagSC;
import es.angelillo15.zangeltags.gui.TagsGui;
import es.angelillo15.zangeltags.utils.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.plaf.basic.BasicButtonUI;
import java.util.ArrayList;

public class MainCommandManager implements CommandExecutor {
    private ArrayList<SubCommand> subcommands = new ArrayList<>();

    private ZAngelTags plugin;

    public MainCommandManager(ZAngelTags plugin) {
        this.plugin = plugin;
        this.subcommands.add(new ReloadSC(plugin));
        this.subcommands.add(new MainHelpSC(this));
        this.subcommands.add(new MainHelpSC(this));
        this.subcommands.add(new GuiSC(plugin));
        this.subcommands.add(new TagSC(plugin, this));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length > 0) {
                for (int i = 0; i < getSubcommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                        getSubcommands().get(i).execute(p, args);
                        return true;
                    }
                }
                MainHelpSC mainHelpSC = new MainHelpSC(this);
                mainHelpSC.execute(p, args);
            } else {
                TagsGui tagsGui = new TagsGui(plugin, p);
            }

        } else {
            if (args.length < 1) {
                consoleHelp();
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reload();
                    Bukkit.getConsoleSender().sendMessage(ColorUtils.translateColorCodes(plugin.getPrefix() + "Reloaded"));
                } else {
                    consoleHelp();
                }
            }
        }
        return true;
    }

    public ArrayList<SubCommand> getSubcommands() {
        return subcommands;
    }

    public void consoleHelp() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6----------------zAngelTags----------------"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bAvailable Commands:"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&bzAngelTags reload &8&l» &f To reload the plugin"));
    }

}
