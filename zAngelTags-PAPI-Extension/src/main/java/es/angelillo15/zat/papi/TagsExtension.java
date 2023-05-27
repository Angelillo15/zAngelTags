package es.angelillo15.zat.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TagsExtension extends PlaceholderExpansion {
    private static final String IDENTIFIER = "zat";
    private static final String PLUGIN_NAME = "zAngelTags";
    private static final String AUTHOR = "angelillo15";
    private static final String VERSION = "{version}";

    @Override
    public boolean canRegister() {
        return Bukkit.getPluginManager().getPlugin(PLUGIN_NAME) != null;
    }

    @Override
    public boolean register() {
        if (!canRegister()) {
            return false;
        }

        return super.register();
    }

    @Override
    public @NotNull String getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public @NotNull String getAuthor() {
        return AUTHOR;
    }

    @Override
    public @NotNull String getVersion() {
        return VERSION;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        return RequestDispatcher.onRequest(player, params);
    }
}
