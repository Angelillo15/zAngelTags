package es.angelillo15.zat.cmd.manager;

import es.angelillo15.zat.api.cmd.sender.PlayerCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CommandTemplate extends org.bukkit.command.Command {
    private final es.angelillo15.zat.api.cmd.Command command;
    public CommandTemplate(String name, es.angelillo15.zat.api.cmd.Command command) {
        super(name);
        this.command = command;
    }

    public CommandTemplate(String name, String permission, es.angelillo15.zat.api.cmd.Command command) {
        super(name);
        setPermission(permission);
        this.command = command;
    }

    public CommandTemplate(String name, String permission, es.angelillo15.zat.api.cmd.Command command, String... aliases) {
        super(name);
        setAliases(Arrays.asList(aliases));
        setPermission(permission);
        this.command = command;
    }

    public CommandTemplate(String name, es.angelillo15.zat.api.cmd.Command command, String... aliases) {
        super (name);
        setAliases(Arrays.asList(aliases));
        this.command = command;
    }


    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player) {
            es.angelillo15.zat.api.cmd.sender.CommandSender commandSender = new PlayerCommandSender((Player) sender);

            if (!commandSender.hasPermission(getPermission())) {
                return true;
            }

            command.execute(commandSender, commandLabel, args);
            return true;
        }

        command.execute(new es.angelillo15.zat.api.cmd.sender.ConsoleCommandSender(), commandLabel, args);
        return true;
    }
}
