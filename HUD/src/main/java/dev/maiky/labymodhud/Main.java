package dev.maiky.labymodhud;

import dev.maiky.labymodhud.listeners.ConnectionListener;
import dev.maiky.labymodhud.tasks.HUDUpdateTask;
import dev.maiky.labymodhud.util.config.Config;
import dev.maiky.labymodhud.util.config.Settings;
import dev.maiky.labymodhud.util.dependecies.Dependecies;
import dev.maiky.labymodhud.util.time.NumUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static @Getter @Setter(AccessLevel.PRIVATE) Main instance;

    private @Getter @Setter(AccessLevel.PRIVATE) Config config;
    private @Getter @Setter(AccessLevel.PRIVATE) Economy economy;

    @Override
    public void onEnable() {
        // Instance
        setInstance(this);

        // Dependencies
        Dependecies dependecies = new Dependecies("LabymodAPI", "Vault");
        if (!dependecies.check()) {
            this.setEnabled(false);
            return;
        }

        // Set Config
        setConfig(new Config(getInstance(), "config.yml"));
        Settings.loadAll(getConfig());

        // Set Economy
        RegisteredServiceProvider<Economy> provider = getServer().getServicesManager()
                .getRegistration(Economy.class);
        setEconomy(provider.getProvider());

        // Listeners
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);

        // Tasks
        NumUtil.Time time = NumUtil.convert(Settings.UPDATE_TIMER.getKeyAsString());
        if (time == null) {
            getLogger().warning("De waarde van de key update-timer in de config is fout, pas deze aan!");
            this.setEnabled(false);
            return;
        }

        HUDUpdateTask updateTask = new HUDUpdateTask();
        updateTask.runTaskTimer(this, 0, 20 * time.getUnit().toSeconds(time.getValue()));
    }

}
