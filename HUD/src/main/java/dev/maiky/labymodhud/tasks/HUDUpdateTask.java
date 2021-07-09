package dev.maiky.labymodhud.tasks;

import dev.maiky.labymodhud.Main;
import dev.maiky.labymodhud.util.labymod.methods.LabyModBalance;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This project is owned by Maiky Perlee - © 2021
 */

public class HUDUpdateTask extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(this::update);
    }

    private final Economy economy = Main.getInstance().getEconomy();

    private void update(Player player) {
        final double balance = economy.getBalance(player);
        LabyModBalance.updateBalanceDisplay(player, LabyModBalance.EnumBalanceType.BANK, true, balance);
    }

}
