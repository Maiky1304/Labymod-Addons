package dev.maiky.labymodhud.util.config;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This project is owned by Maiky Perlee - © 2021
 */

public enum Settings {

    CUSTOM_ICON("custom-icon", ""),
    DECIMAL_FORMAT("decimal-format", "##-##"),
    UPDATE_TIMER("update-time", "5s");

    private @Getter
    final String yamlPath;
    private @Getter
    final Object defaultValue;

    Settings(String yamlPath, Object defaultValue) {
        this.yamlPath = yamlPath;
        this.defaultValue = defaultValue;
    }

    public Object get() {
        return settings.get(this.yamlPath);
    }

    public String getKeyAsString() {
        return ((String) settings.get(this.yamlPath));
    }

    public Integer getKeyAsInt() {
        return ((Integer) settings.get(this.yamlPath));
    }

    public Double getKeyAsDouble() {
        return ((Double) settings.get(this.yamlPath));
    }

    public List<String> getKeyAsList() {
        return ((List<String>) settings.get(this.yamlPath));
    }

    private static @Getter final HashMap<String, Object> settings = new HashMap<>();

    public static void loadAll(Config config) {
        Arrays.stream(values()).forEach(s -> {
            settings.put(s.yamlPath, config.contains(s.yamlPath) ? config.get(s.yamlPath) : s.defaultValue);
        });
    }

}
