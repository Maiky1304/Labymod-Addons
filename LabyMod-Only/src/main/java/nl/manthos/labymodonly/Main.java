package nl.manthos.labymodonly;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.manthos.labymodonly.listeners.PlayerJoinListener;
import nl.manthos.labymodonly.listeners.PlayerJoinListenerTwo;
import nl.manthos.labymodonly.listeners.PlayerQuitListener;
import nl.manthos.labymodonly.util.config.Config;
import nl.manthos.labymodonly.util.config.Settings;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;


public final class Main extends JavaPlugin {

    private static @Getter @Setter(AccessLevel.PRIVATE) Main instance;
    private @Getter @Setter(AccessLevel.PRIVATE) Config config;

    public ArrayList<UUID> joined = new ArrayList<>();

    @Override
    public void onEnable() {
        // Instance
        setInstance(this);

        // Config
        setConfig(new Config(getInstance(), "config.yml"));
        Settings.loadAll(getConfig());

        // PluginMessageListeners
        getServer().getMessenger().registerIncomingPluginChannel(this, "labymod3:main", new PlayerJoinListener(this));
        getServer().getMessenger().registerIncomingPluginChannel(this, "LMC", new PlayerJoinListenerTwo(this));

        //Listeners
        registerListeners(
                new PlayerQuitListener(this)
        );
    }

    public void registerListeners(Listener... listeners) {
        Arrays.asList(listeners).forEach(listener ->  {
            Bukkit.getPluginManager().registerEvents(listener, this);
        });
    }
}
