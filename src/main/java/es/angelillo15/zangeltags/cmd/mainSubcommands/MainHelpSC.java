package es.angelillo15.zangeltags.cmd.mainSubcommands;

import es.angelillo15.zangeltags.cmd.commandsManagers.MainCommandManager;
import es.angelillo15.zangeltags.cmd.SubCommand;
import es.angelillo15.zangeltags.utils.ColorUtils;
import org.bukkit.entity.Player;

public class MainHelpSC extends SubCommand {
    private MainCommandManager mainCommandManager;

    public MainHelpSC(MainCommandManager mainCommandManager) {
        this.mainCommandManager = mainCommandManager;
    }

    @Override
    public String getName() {
        return "Help";
    }

    @Override
    public String getDescription() {
        return "Show the plugin help";
    }

    @Override
    public String getSyntax() {
        return "/zat help";
    }

    @Override
    public String getPermission() {
        return "default";
    }

    @Override
    public void execute(Player player, String[] args) {
        player.sendMessage(ColorUtils.translateColorCodes("&6----------------zAngelTags----------------"));
        player.sendMessage(ColorUtils.translateColorCodes("&bAvailable Commands:"));
        for (int i = 0; i < mainCommandManager.getSubcommands().size(); i++) {
            player.sendMessage(ColorUtils.translateColorCodes("&b" + mainCommandManager.getSubcommands().get(i).getSyntax() + " &8&l» &f " + mainCommandManager.getSubcommands().get(i).getDescription()));
        }
    }
}
