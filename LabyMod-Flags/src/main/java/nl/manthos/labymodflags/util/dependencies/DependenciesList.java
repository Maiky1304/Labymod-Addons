package nl.manthos.labymodflags.util.dependencies;

import java.util.HashMap;

public class DependenciesList {

    private static HashMap<String, String> dependenciesDataBase = new HashMap<>();

    public static void addPlugins() {
        dependenciesDataBase.put("LabyModAPI", "https://www.spigotmc.org/resources/labymod-server-api.52423/");
        dependenciesDataBase.put("Vault", "https://www.spigotmc.org/resources/vault.34315/");
    }

    public static boolean check(String plugin) {
        return dependenciesDataBase.containsKey(plugin);
    }

    public static String getLink(String plugin) {
        return dependenciesDataBase.get(plugin);
    }
}
