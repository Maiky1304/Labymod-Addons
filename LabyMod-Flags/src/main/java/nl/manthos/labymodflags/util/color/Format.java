package nl.manthos.labymodflags.util.color;

import org.bukkit.ChatColor;

public class Format {

    public static String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
