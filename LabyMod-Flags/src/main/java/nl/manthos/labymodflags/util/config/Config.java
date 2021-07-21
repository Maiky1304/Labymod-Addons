package nl.manthos.labymodflags.util.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Config extends YamlConfiguration {

    private final File configFile;

    public Config(JavaPlugin plugin, String fileName) {
        this.configFile = new File(plugin.getDataFolder(), fileName);

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        if (!configFile.exists()) {
            plugin.saveResource(fileName, false);
        }

        try {
            super.load(configFile);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    public void reloadConfig() {
        try {
            super.load(this.configFile);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            super.save(this.configFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean allowed(String country) {
        return getAllowedCountries().contains(country.toUpperCase());
    }

    public List<String> getAllowedCountries() {
        return (List<String>) getList("countries");
    }
}
