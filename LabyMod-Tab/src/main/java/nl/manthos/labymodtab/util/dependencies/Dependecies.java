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
                main.getLogger().warning("The plugin " + string + " is not installed, you need this to load the plugin.");
                main.getLogger().warning("Searching for a downloadlink in database...");
                Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                    @Override
                    public void run() {
                        if (checkDatabase(string)) {
                            main.getLogger().warning("Found Downloadlink: " + getLink(string));
                        } else {
                            main.getLogger().warning("There was no downloadlink found in the database!");
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
