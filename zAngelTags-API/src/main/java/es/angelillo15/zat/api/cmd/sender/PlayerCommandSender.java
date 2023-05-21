package es.angelillo15.zat.api.cmd.sender;

import es.angelillo15.zat.api.TextUtils;
import org.bukkit.entity.Player;

public class PlayerCommandSender implements CommandSender {
    private final Player player;

    public PlayerCommandSender(Player player) {
        this.player = player;
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(TextUtils.colorize(message));
    }

    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public String getUniqueId() {
        return player.getUniqueId().toString();
    }

    @Override
    public String getAddress() {
        return player.getAddress().getHostName();
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public boolean isConsole() {
        return !isPlayer();
    }
}
