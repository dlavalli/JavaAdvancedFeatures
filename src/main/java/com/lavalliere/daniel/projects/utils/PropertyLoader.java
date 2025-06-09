package com.lavalliere.daniel.projects.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    public final static Properties properties;
    static {
        properties=loadProperties("application.properties");
    }

    public static Properties loadProperties(String resourcePath) {
        Properties properties = new Properties();
        try (InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + resourcePath);
                return null;
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return properties;
    }
}
