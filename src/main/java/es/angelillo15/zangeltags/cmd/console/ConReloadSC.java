package es.angelillo15.zangeltags.cmd.console;

import es.angelillo15.zangeltags.ZAngelTags;
import es.angelillo15.zangeltags.cmd.SubCommand;
import org.bukkit.entity.Player;

public class ConReloadSC extends SubCommand {
    private ZAngelTags plugin;

    public ConReloadSC(ZAngelTags plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "reload";
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
        return null;
    }

    @Override
    public void execute(Player player, String[] args) {
        plugin.reload();
    }
}
