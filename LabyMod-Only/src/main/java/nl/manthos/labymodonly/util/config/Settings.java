package nl.manthos.labymodonly.util.config;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum Settings {

    KICK_MESSAGE("kick-message", "Je hebt LabyMod nodig om deze server te joinen, download het hier: https://www.labymod.net/nl/download"),
    CONSOLE_MESSAGE_APPROVED("console-message-approved", "Player %player% is using LabyMod, join approved!");

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
