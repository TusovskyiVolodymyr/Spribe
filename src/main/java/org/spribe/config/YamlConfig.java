package org.spribe.config;

import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.io.Files.asByteSource;

@Log4j2
public class YamlConfig {

    private static Map<String, Object> config = new LinkedHashMap<>();

    public static void loadConfig(String env) {
        mergeConfig("config/config-default.yaml");
        mergeConfig("config/config-" + env + ".yaml");
        log.info("Loaded config: {}", env);
    }

    private static void mergeConfig(String fileName) {
        try (InputStream inputStream = asByteSource(new File(fileName)).openStream()) {
            if (inputStream == null) {
                throw new RuntimeException("Config file not found: " + fileName);
            }
            Yaml yaml = new Yaml();
            Map<String, Object> loadedConfig = yaml.load(inputStream);
            mergeMaps(config, loadedConfig);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void mergeMaps(Map<String, Object> base, Map<String, Object> overrides) {
        for (Map.Entry<String, Object> entry : overrides.entrySet()) {
            if (entry.getValue() instanceof Map && base.get(entry.getKey()) instanceof Map) {
                mergeMaps((Map<String, Object>) base.get(entry.getKey()), (Map<String, Object>) entry.getValue());
            } else {
                base.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static String getString(String key) {
        String envKey = key.toUpperCase().replace(".", "_");
        String envValue = System.getenv(envKey);
        if (envValue != null) {
            return envValue;
        }

        String[] keys = key.split("\\.");
        Map<String, Object> tempMap = config;
        for (int i = 0; i < keys.length - 1; i++) {
            tempMap = (Map<String, Object>) tempMap.get(keys[i]);
        }
        return tempMap.get(keys[keys.length - 1]).toString();
    }
}
