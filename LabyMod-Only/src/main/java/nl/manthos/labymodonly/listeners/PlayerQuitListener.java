package nl.manthos.labymodonly.listeners;

import nl.manthos.labymodonly.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {

    private Main main;

    public PlayerQuitListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        main.joined.remove(uuid);
    }
}
