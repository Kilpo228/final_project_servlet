package ua.testing.model.exceptions;

import java.lang.reflect.Field;

public class FieldInjectionException extends RuntimeException {
    public FieldInjectionException(Class<?> clazz, Field field) {
        super("Error while injecting filed " + field + " in class " + clazz);
    }
}
