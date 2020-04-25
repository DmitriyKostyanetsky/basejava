package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        Method method = r.getClass().getDeclaredMethod("toString");
        method.setAccessible(true);
        method.invoke(r);
        System.out.println(r);
    }
}
