package nl.manthos.labymodtab.util.dependencies;

import nl.manthos.labymodtab.Main;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

import java.util.HashMap;

public class Dependecies {

    private String[] dependecies;
    private static HashMap<String, String> dependenciesDataBase = new HashMap<>();

    public Dependecies(String... dependencies) {
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
                        if (checkDatabase(string)) {
                            main.getLogger().warning("Downloadlink gevonden: " + getLink(string));
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

    public static void addPlugins() {
        dependenciesDataBase.put("LabyModAPI", "https://www.spigotmc.org/resources/labymod-server-api.52423/");
        dependenciesDataBase.put("Vault", "https://www.spigotmc.org/resources/vault.34315/");
    }

    public static boolean checkDatabase(String plugin) {
        return dependenciesDataBase.containsKey(plugin);
    }

    public static String getLink(String plugin) {
        return dependenciesDataBase.get(plugin);
    }
}
