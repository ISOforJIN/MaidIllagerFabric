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

    public static class Builder<T> {
        String key;
        T value;
        String description;

        public Builder(String key, T value, String description) {
            this.key = key;
            this.value = value;
            this.description = description;
        }

        public static Builder create() {
            return new Builder("", null, "");
        }

        public static Builder clone(ConfigRow configRow) {
            return new Builder(configRow.key, configRow.value, configRow.description);
        }

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setValue(T value) {
            this.value = value;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ConfigRow build() {
            return new ConfigRow(key, value);
        }

        public ConfigRow buildWithDescription() {
            return new ConfigRow(key, value, description);
        }
    }
}
