package es.angelillo15.zangeltags.cmd.mainsubcommands;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.cmd.SubCommand;
import es.angelillo15.zangeltags.config.ConfigLoader;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ReloadSC extends SubCommand {
    private ZAngelTags plugin;
    public ReloadSC(ZAngelTags plugin){
        this.plugin = plugin;
    }
    @Override
    public String getName() {
        return "Reload";
    }

    @Override
    public String getDescription() {
        return "Command to reload the plugin";
    }

    @Override
    public String getSyntax() {
        return "/zat reload";
    }

    @Override
    public String getPermission() {
        return "zAngelTags.reload";
    }

    @Override
    public void execute(Player player, String[] args) {
        if(player.hasPermission(getPermission())){
            plugin.reload();
            FileConfiguration msg = ConfigLoader.getMessageConfig().getConfig();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.getString("Messages.reloadMessage")));
        }
    }
}
