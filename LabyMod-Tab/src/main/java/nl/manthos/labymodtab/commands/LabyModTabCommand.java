package nl.manthos.labymodtab.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LabyModTabCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDeze server maakt gebruik van &4&lLabyMod Tab &cdoor &4Manthos & Maiky1304"));
        return true;
    }
}
