package utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public final class PropertiesManager {

    private static Properties properties = null;
    private static InputStream input = null;
    private static String propertyPath = "project.properties";

    public static String getProperty(String propertyName) {
        if (Objects.isNull(properties)) {
            properties = new Properties();
            try {
                input = PropertiesManager.class.getClassLoader().getResourceAsStream(propertyPath);
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return properties.getProperty(propertyName);
    }

}
