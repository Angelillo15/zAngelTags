package es.angelillo15.zat.api.cmd.sender;

import es.angelillo15.zat.api.TagsInstance;

public class ConsoleCommandSender implements CommandSender {
    @Override
    public void sendMessage(String message) {
        TagsInstance.getLogger().info(message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }

    @Override
    public String getName() {
        return "CONSOLE";
    }

    @Override
    public String getUniqueId() {
        return "CONSOLE";
    }

    @Override
    public String getAddress() {
        return "127.0.0.1";
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isConsole() {
        return !isPlayer();
    }
}
