package nl.manthos.labymodonly;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.manthos.labymodonly.util.config.Config;
import nl.manthos.labymodonly.util.config.Settings;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;


public final class Main extends JavaPlugin implements PluginMessageListener {

    private static @Getter @Setter(AccessLevel.PRIVATE) Main instance;
    private @Getter @Setter(AccessLevel.PRIVATE) Config config;

    @Override
    public void onEnable() {
        // Instance
        setInstance(this);

        // Config
        setConfig(new Config(getInstance(), "config.yml"));
        Settings.loadAll(getConfig());

        // Listeners
        getServer().getMessenger().registerIncomingPluginChannel(this, "labymod3:main", this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (channel.equals("labymod3:main")) {
            this.getLogger().info("Player " + player.getName() + " is using LabyMod, join approved!");
        } else if (channel.equals("LMC")) {
            this.getLogger().info("Player " + player.getName() + " is using LabyMod, join approved!");
        } else {
            player.kickPlayer(Settings.KICK_MESSAGE.getKeyAsString());
        }
    }
}
