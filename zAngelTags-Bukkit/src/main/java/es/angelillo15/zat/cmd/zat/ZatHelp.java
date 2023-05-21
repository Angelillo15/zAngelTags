package es.angelillo15.zat.cmd.zat;

import es.angelillo15.zat.api.cmd.SubCommand;
import es.angelillo15.zat.api.cmd.sender.CommandSender;

public class ZatHelp extends SubCommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows the help page";
    }

    @Override
    public String getSyntax() {
        return "/zat help";
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public void onCommand(CommandSender sender, String label, String[] args) {
        ZatParent.getInstance().sendHelp(sender);
    }
}
