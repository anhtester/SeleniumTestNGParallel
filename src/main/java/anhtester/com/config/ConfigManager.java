package anhtester.com.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigManager {

    private ConfigManager() {
    }

    public static Configuration getConfig() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}

