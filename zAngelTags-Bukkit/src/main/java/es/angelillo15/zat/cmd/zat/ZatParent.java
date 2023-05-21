package es.angelillo15.zat.cmd.zat;

import es.angelillo15.zat.api.cmd.CommandData;
import es.angelillo15.zat.api.cmd.CommandParent;
import lombok.Getter;

@CommandData(
        name = "zat",
        aliases = {"zangeltags"},
        description = "Main command of the plugin",
        permission = "zat.admin",
        usage = "/zat <subcommand>"
)
public class ZatParent extends CommandParent {
    @Getter
    private static ZatParent instance;

    public ZatParent() {
        instance = this;
        registerSubCommand(new ZatHelp());
    }
}
