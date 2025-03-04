package org.spribe.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PojoToMapConverter {
    public static Map<String, Object> convertToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) return map;

        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field: " + field.getName(), e);
            }
        }
        return map;
    }
}
