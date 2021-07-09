package dev.maiky.labymodhud.util.dependecies;

import dev.maiky.labymodhud.Main;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;

/**
 * This project is owned by Maiky Perlee - © 2021
 */

public class Dependecies {

    private String[] dependecies;

    public Dependecies(String... dependecies) {
        this.dependecies = dependecies;
    }

    public boolean check() {
        final Main main = Main.getInstance();
        final Server server = Bukkit.getServer();
        final PluginManager pluginManager = server.getPluginManager();

        for (String string : this.dependecies) {
            if (!pluginManager.isPluginEnabled(string)) {
                main.getLogger().warning("De plugin " + string + " is niet geinstalleerd, deze heb je nodig om de plugin te kunnen laden.");
                return false;
            }
        }

        return true;
    }

}
