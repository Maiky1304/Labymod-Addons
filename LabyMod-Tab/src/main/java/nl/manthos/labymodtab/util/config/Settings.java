package nl.manthos.labymodtab.util.config;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum Settings {
    SERVER_BANNER_URL("server-banner-url", "https://i.imgur.com/YdeR4J2.png");

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
