package nl.manthos.labymodflags;

import lombok.Getter;
import lombok.Setter;
import nl.manthos.labymodflags.util.dependencies.Dependencies;
import nl.manthos.labymodflags.util.dependencies.DependenciesList;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class Main extends JavaPlugin {

    private static @Getter @Setter Main instance;

    @Override
    public void onEnable() {
        // Instance
        setInstance(this);

        // Dependencies
        DependenciesList.addPlugins();

        Dependencies dependencies = new Dependencies("LabyModAPI");
        if (!dependencies.check()) {
            this.setEnabled(false);
            return;
        }

        // Register Listeners
        registerListeners(

        );
    }

    private void registerListeners(Listener... listeners) {
        Arrays.asList(listeners).forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, this);
        });
    }
}
