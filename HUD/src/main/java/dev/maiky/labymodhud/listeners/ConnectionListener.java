package dev.maiky.labymodhud.listeners;

import dev.maiky.labymodhud.Main;
import dev.maiky.labymodhud.util.labymod.LabyModProtocol;
import dev.maiky.labymodhud.util.labymod.methods.LabyModBalance;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This project is owned by Maiky Perlee - © 2021
 */

public class ConnectionListener implements Listener {

    private final Economy economy = Main.getInstance().getEconomy();

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        LabyModBalance.getCache().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final double balance = economy.getBalance(event.getPlayer());
        LabyModBalance.updateBalanceDisplay(event.getPlayer(), LabyModBalance.EnumBalanceType.BANK, true, balance);
    }

}
