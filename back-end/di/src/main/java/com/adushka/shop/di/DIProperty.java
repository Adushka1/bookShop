package com.senla.shop.di;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DIProperty {
    private static Properties properties = null;
    private final static Logger LOGGER = Logger.getLogger(DIProperty.class);
    private InputStream in = getClass().getResourceAsStream("/di.properties");

    private DIProperty(){
        load();
    }

    public static Properties getInstance() {
        if (properties == null) {
            properties = new Properties();
            new DIProperty();
        }
        return properties;
    }

    private void load(){
        try {
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error("Not found di.properties", e);
        }
    }
}
