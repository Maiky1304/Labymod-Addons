package nl.manthos.labymodtab;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static @Getter @Setter Main instance;

    @Override
    public void onEnable() {
        // Instance
        setInstance(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
