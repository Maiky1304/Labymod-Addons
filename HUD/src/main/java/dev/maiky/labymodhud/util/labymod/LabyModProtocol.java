package dev.maiky.labymodhud.util.labymod;

import com.google.gson.JsonElement;
import dev.maiky.labymodhud.Main;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

/**
 * This project is owned by Maiky Perlee - © 2021
 */

public class LabyModProtocol {

    public static void sendLabyModMessage(Player player, String key, JsonElement messageContent) {
        final Server server = Bukkit.getServer();
        final String version = server.getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";

        System.out.println(version);
        if (version.contains("1_12"))
            new LabyModProtocol_112().sendLabyModMessage(player, key, messageContent);
        else if (version.contains("1_13")) new LabyModProtocol_116().sendLabyModMessage(player, key, messageContent);
        else if (version.contains("1_8")) new LabyModProtocol_18().sendLabyModMessage(player, key, messageContent);
        else {
            Main.getInstance().getLogger().warning("Jouw server versie wordt niet ondersteund door ons, contacteer Maiky#0001 of Matthijs#0001 om support voor een specifieke versie aan te vragen, vermeld hierbij de server versie en stuur je server jar file.");
        }
    }

}
