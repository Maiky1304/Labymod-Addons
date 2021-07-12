package nl.manthos.labymodonly.listeners;

import nl.manthos.labymodonly.Main;
import nl.manthos.labymodonly.util.config.Settings;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PlayerJoinListenerTwo implements PluginMessageListener {

    private Main main;

    public PlayerJoinListenerTwo(Main main) {
        this.main = main;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("LMC")) {
            if (player.hasPermission("labymodonly.bypass")) {
                System.out.println(player.getName() + " joined without LabyMod!");
                return;
            }
            player.kickPlayer(Settings.KICK_MESSAGE.getKeyAsString());
        } else {
            if (main.joined.contains(player.getUniqueId())) return;
            main.getLogger().info(Settings.CONSOLE_MESSAGE_APPROVED.getKeyAsString().replaceAll("%player%", player.getName()));
            main.joined.add(player.getUniqueId());
        }
    }
}
