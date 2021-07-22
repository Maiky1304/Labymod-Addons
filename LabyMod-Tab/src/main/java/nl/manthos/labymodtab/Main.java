package nl.manthos.labymodtab;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.manthos.labymodtab.commands.LabyModTabCommand;
import nl.manthos.labymodtab.listeners.PlayerJoinListener;
import nl.manthos.labymodtab.util.config.Config;
import nl.manthos.labymodtab.util.config.Settings;
import nl.manthos.labymodtab.util.dependencies.Dependecies;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public final class Main extends JavaPlugin {

    private static @Getter @Setter Main instance;
    private @Getter @Setter(AccessLevel.PRIVATE) Config config;

    public String link;

    @Override
    public void onEnable() {
        // Instance
        setInstance(this);

        // Dependencies
        Dependecies dependecies = new Dependecies("LabyModAPI");
        Dependecies.addPlugins();
        if (!dependecies.check()) {
            this.setEnabled(false);
            return;
        }

        // Set Config
        setConfig(new Config(getInstance(), "config.yml"));
        Settings.loadAll(getConfig());

        // Set Server Banner
        this.link = Settings.SERVER_BANNER_URL.getKeyAsString();

        // Register Listeners
        registerListeners(
                new PlayerJoinListener()
        );

        this.getCommand("labymodtab").setExecutor(new LabyModTabCommand());
    }

    private void registerListeners(Listener... listeners) {
        Arrays.asList(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, this);
        });
    }
}
