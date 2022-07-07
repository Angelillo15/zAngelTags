package es.angelillo15.zangeltags.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

public class ColorUtils {
    static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    public static String translateColorCodes(String text) {
        String[] split = Bukkit.getBukkitVersion().split("-")[0].split("\\.");
        String minorVer = split[1];
        int version = Integer.parseInt(minorVer);
        if(version < 16){
            return ChatColor.translateAlternateColorCodes('&', text);
        }

        String[] texts = text.split(String.format(WITH_DELIMITER, "&"));

        StringBuilder finalText = new StringBuilder();

        for (int i = 0; i < texts.length; i++) {
            if (texts[i].charAt(0) == '#') {
                finalText.append(net.md_5.bungee.api.ChatColor.of(texts[i].substring(0, 7)) + texts[i].substring(7));
            } else if (texts[i].equalsIgnoreCase("&")) {
                i++;
                if (texts[i].charAt(0) == '#') {
                    finalText.append(net.md_5.bungee.api.ChatColor.of(texts[i].substring(0, 7)) + texts[i].substring(7));
                } else {
                    finalText.append(ChatColor.translateAlternateColorCodes('&', "&" + texts[i]));
                }
            } else {
                finalText.append(texts[i]);
            }
        }

        return finalText.toString();
    }
}