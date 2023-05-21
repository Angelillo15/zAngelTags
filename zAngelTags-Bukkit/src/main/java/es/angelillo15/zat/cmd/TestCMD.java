package es.angelillo15.zat.cmd;

import es.angelillo15.zat.api.cmd.Command;
import es.angelillo15.zat.api.cmd.CommandData;
import es.angelillo15.zat.api.cmd.sender.CommandSender;

@CommandData(
        name = "test",
        permission = "zat.test",
        aliases = {"t", "testcmd"},
        description = "Test command"
)
public class TestCMD extends Command {
    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("Test command executed!");
    }
}
