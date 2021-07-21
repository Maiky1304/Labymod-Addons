package nl.manthos.labymodflags.util.dependencies;

import nl.manthos.labymodflags.Main;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

public class Dependencies {

    private String[] dependecies;

    public Dependencies(String... dependencies) {
        this.dependecies = dependencies;
    }

    public boolean check() {
        final Main main = Main.getInstance();
        final Server server = Bukkit.getServer();
        final PluginManager pluginManager = server.getPluginManager();

        for (String string : this.dependecies) {
            if (!pluginManager.isPluginEnabled(string)) {
                main.getLogger().warning("De plugin " + string + " is niet geinstalleerd, deze heb je nodig om de plugin te kunnen laden.");
                main.getLogger().warning("Aan het zoeken naar een downloadlink in database...");
                Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                    @Override
                    public void run() {
                        if (DependenciesList.check(string)) {
                            main.getLogger().warning("Downloadlink gevonden: " + DependenciesList.getLink(string));
                        } else {
                            main.getLogger().warning("Geen downloadlink gevonden in database!");
                        }
                    }
                }, 20L);
                return false;
            }
        }
        return true;
    }
}
