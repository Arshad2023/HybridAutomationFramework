package common.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = loadProperties();

    private ConfigReader() {
    }

    private static Properties loadProperties() {
        Properties loadedProperties = new Properties();

        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            loadedProperties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }

        return loadedProperties;
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Missing config property: " + key);
        }

        return value;
    }
}
