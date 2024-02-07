//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\lukes\OneDrive\Desktop\deobfer\1.8.9 MAPPINGS"!

//Decompiled by Procyon!

package me.oringo.oringoclient.utils;

import java.lang.reflect.*;

public class ReflectionUtils
{
    public static void setFieldByIndex(final Object object, final int index, final Object value) {
        try {
            final Field field = object.getClass().getDeclaredFields()[index];
            field.setAccessible(true);
            field.set(object, value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setFieldByIndex(final Class clazz, final int index, final Object object, final Object value) {
        try {
            final Field field = clazz.getDeclaredFields()[index];
            field.setAccessible(true);
            field.set(object, value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Object getFieldByIndex(final Object object, final int index) {
        try {
            final Field field = object.getClass().getDeclaredFields()[index];
            field.setAccessible(true);
            return field.get(object);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getStaticField(Class<?> clasz, String name)
    {
        try
        {
            Field field = clasz.getField(name);
            field.setAccessible(true);
            return field.get(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Object getFieldByName(final Class clazz, final String name, final Object object) {
        try {
            final Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static Object getFieldByName(final Object obj, final String name) {
        try {
            final Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static void setFieldByName(final Object object, final String name, final Object value) {
        try {
            final Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
