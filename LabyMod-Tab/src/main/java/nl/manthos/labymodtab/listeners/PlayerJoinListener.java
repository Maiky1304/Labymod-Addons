package nl.manthos.labymodtab.listeners;

import nl.manthos.labymodtab.Main;
import nl.manthos.labymodtab.util.labymod.methods.LabyModBanner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private Main main = Main.getInstance();

    /**
     * Sends to the banner to the player!
     */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        LabyModBanner.sendServerBanner(player, main.link);
    }
}
