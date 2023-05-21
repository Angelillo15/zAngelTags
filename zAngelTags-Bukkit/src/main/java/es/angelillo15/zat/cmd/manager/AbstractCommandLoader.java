package es.angelillo15.zat.cmd.manager;

import es.angelillo15.zat.TagsPlugin;
import es.angelillo15.zat.api.TagsInstance;
import es.angelillo15.zat.api.cmd.Command;
import es.angelillo15.zat.api.cmd.CommandData;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

public class AbstractCommandLoader {
    public static void registerCommand(Command command) {
        CommandData data;
        try {
            data = command.getClass().getAnnotation(CommandData.class);
        } catch (NullPointerException e) {
            throw new NullPointerException("CommandData annotation not found in " + command.getClass().getName());
        }

        if (data == null) {
            throw new NullPointerException("CommandData annotation not found in " + command.getClass().getName());
        }

        if (data.name().isEmpty()) {
            throw new NullPointerException("CommandData annotation name is empty in " + command.getClass().getName());
        }

        if (data.aliases().length == 0 && data.permission().isEmpty()) {
            registerIntoCommandMap(data, new CommandTemplate(data.name(), command, data.aliases()));
            return;
        }

        if (data.aliases().length == 0) {
            registerIntoCommandMap(data, new CommandTemplate(data.name(), data.permission(), command));
            return;
        }

        if (data.permission().isEmpty()) {
            registerIntoCommandMap(data, new CommandTemplate(data.name(), command, data.aliases()));
            return;
        }

        registerIntoCommandMap(data, new CommandTemplate(data.name(), data.permission(), command, data.aliases()));
    }

    @SneakyThrows
    public static void registerIntoCommandMap(CommandData data, org.bukkit.command.Command command) {
        Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        commandMapField.setAccessible(true);

        CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());

        commandMap.register(
                TagsPlugin.getInstance().getDescription().getName(),
                command
        );

        TagsInstance.getLogger().debug("Registered command " + data.name() + " into command map");

    }
}
