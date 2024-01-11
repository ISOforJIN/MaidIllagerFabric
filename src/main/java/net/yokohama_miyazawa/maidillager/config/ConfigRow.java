package net.yokohama_miyazawa.maidillager.config;

public class ConfigRow<T> {
    public String key;
    public T value;
    public String description = "";

    public ConfigRow(String key, T value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public ConfigRow(String key, T value) {
        this.key = key;
        this.value = value;
    }
}
