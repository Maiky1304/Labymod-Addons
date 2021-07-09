package dev.maiky.labymodhud.util.color;

import org.bukkit.ChatColor;

/**
 * This project is owned by Maiky Perlee - © 2021
 */

public class Color {

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
