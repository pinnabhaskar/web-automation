package com.org.web.automation.configuration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertiesResolver {

    private static final boolean DEBUG = false;

    private static void debugPrintln(String msg, Object... args) {
        if (DEBUG) {
            System.out.println(String.format("%s: %s", PropertiesResolver.class.getSimpleName(), String.format(msg, (Object[]) args)));
        }
    }

    public static void mapPropertiesToObject(Properties p, Object annotatedObject) throws IllegalAccessException {
        debugPrintln("Mapping %s to object %s", p, annotatedObject);
        
        if (annotatedObject instanceof IPropertiesDefinitionsContainer)
        	((IPropertiesDefinitionsContainer)annotatedObject).setProperties(p);
        
        for (Field f : annotatedObject.getClass().getDeclaredFields()) {
            Property propertyAnnotation = f.getAnnotation(Property.class);
            if (propertyAnnotation != null) {
                // get value from properties
                String value = p.getProperty(propertyAnnotation.value());
                debugPrintln("%s -> %s", f.getName(), value);
                setFieldToValue(f, annotatedObject, value);
            }
        }
    }

    public static void mapObjectToProperties(Object annotatedObject, Properties p) throws IllegalAccessException {
        debugPrintln("Mapping %s from object %s", p, annotatedObject);

        for (Field f : annotatedObject.getClass().getDeclaredFields()) {
            Property propertyAnnotation = f.getAnnotation(Property.class);
            if (propertyAnnotation != null) {

                setPropertyFromObject(f, annotatedObject, propertyAnnotation, p);

            }
        }
    }

    private static void setPropertyFromObject(Field f, Object annotatedObject, Property propertyAnnotation, Properties p) throws IllegalAccessException, IllegalArgumentException {
        // make field accessible
        boolean oldAccessible = f.isAccessible();
        f.setAccessible(true);
        // get value from properties
        Object o = f.get(annotatedObject);
        String value = null;
        if (o != null) {
            value = o.toString();
        }
        debugPrintln("%s -> %s=%s", f.getName(), propertyAnnotation.value(), value);
        // store in properties file
        p.setProperty(propertyAnnotation.value(), value);

        f.setAccessible(oldAccessible);
    }

    private static void setFieldToValue(Field f, Object annotatedObject, String value) throws IllegalAccessException, IllegalArgumentException, SecurityException {
        // enable property access
        boolean oldAccess = f.isAccessible();
        f.setAccessible(true);
        // set value in object
        Class<?> t = f.getType();

        // set empty values to null
        if (value != null && value.isEmpty()) {
            value = null;
        }
        if (t.equals(String.class) || value == null) {
            f.set(annotatedObject, value);
        } else if (t.isAssignableFrom(Long.class) || t.isAssignableFrom(long.class)) {
            f.set(annotatedObject, Long.parseLong(value));
        } else if (t.isAssignableFrom(Integer.class) || t.isAssignableFrom(int.class)) {
            f.set(annotatedObject, Integer.parseInt(value));
        } else if (t.isAssignableFrom(Float.class) || t.isAssignableFrom(float.class)) {
            f.set(annotatedObject, Float.parseFloat(value));
        } else if (t.isAssignableFrom(Double.class) || t.isAssignableFrom(double.class)) {
            f.set(annotatedObject, Double.parseDouble(value));
        } else if (t.isAssignableFrom(Boolean.class) || t.isAssignableFrom(boolean.class)) {
            f.set(annotatedObject, Boolean.parseBoolean(value));
        } else if (t.isAssignableFrom(List.class)) {
        	f.set(annotatedObject, Arrays.asList(value.split(",")));
        } else {
            throw new IllegalAccessException("Type " + t + " not available for property mapping.");
        }

        // revert write access
        f.setAccessible(oldAccess);
    }

}