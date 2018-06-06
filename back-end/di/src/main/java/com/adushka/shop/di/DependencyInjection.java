package com.adushka.shop.di;

import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class DependencyInjection {
    private final static Logger LOGGER = Logger.getLogger(DependencyInjection.class);
    private static Map<String, Object> stringObjectMap;
    private static DependencyInjection instance = null;

    private DependencyInjection() {
        stringObjectMap = new HashMap<>();
    }

    public static DependencyInjection getInstance() {
        if (instance == null) {
            instance = new DependencyInjection();
        }
        return instance;
    }

    public Object getObject(Class<?> clazz) {
        Object obj1 = stringObjectMap.get(clazz.getName());
        if (obj1 == null) {
            try {
                String className = DIProperty.getInstance().getProperty(clazz.getSimpleName());
                Constructor<?> constructor = Class.forName(className).getDeclaredConstructor();
                constructor.setAccessible(true);
                Object obj2 = constructor.newInstance();
                stringObjectMap.put(className, obj2);
                return obj2;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return obj1;
    }
}
