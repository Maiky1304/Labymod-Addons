package nl.manthos.labymodflags.commands;

import nl.manthos.labymodflags.Main;
import nl.manthos.labymodflags.util.color.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CountryCommand implements CommandExecutor {

    private Main main = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Format.format("&cJe kan deze command alleen als speler uitvoeren!"));
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("labymodflags.command")) {
            player.sendMessage(Format.format("&cJe hebt geen permissie om deze command uit te voeren!"));
            return true;
        }
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {

            player.sendMessage(Format.format("&4&m----------&r &c&lLabyMod Flags &7" + main.getDescription().getVersion() + " &3&m----------"));
            player.sendMessage("");
            player.sendMessage(Format.format("&c/" + label + " <country code> &7- Stel jou land voor op tab in."));
            player.sendMessage(Format.format("&c/" + label + " list &7- Krijg een lijst met alle toegestane landen."));
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("list")) {

            }
        }
        return true;
    }
}
