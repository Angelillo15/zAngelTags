package es.angelillo15.zat.api.cmd;

import es.angelillo15.zat.api.cmd.sender.CommandSender;

public abstract class Command {
    public abstract void execute(CommandSender sender, String label, String[] args);
}
