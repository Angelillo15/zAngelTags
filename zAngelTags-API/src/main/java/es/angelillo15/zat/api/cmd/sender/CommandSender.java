package es.angelillo15.zat.api.cmd.sender;

public interface CommandSender {
    void sendMessage(String message);

    boolean hasPermission(String permission);

    String getName();

    String getUniqueId();

    String getAddress();

    boolean isPlayer();

    boolean isConsole();
}
