package ua.testing.model.configurator.imp.annotation;

import ua.testing.model.annotation.InjectByType;
import ua.testing.model.application.ApplicationContext;
import ua.testing.model.configurator.ObjectConfigurator;
import ua.testing.model.dao.connection.ConnectionPoolHolder;
import ua.testing.model.exceptions.FieldInjectionException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InjectByTypeObjectConfigurator implements ObjectConfigurator {
    @Override
    public void configure(Object object, ApplicationContext context) {
        Class<?> clazz = object.getClass();
        Set<Field> fields = Stream.of(clazz.getDeclaredFields()).
                filter(field -> field.isAnnotationPresent(InjectByType.class)).
                collect(Collectors.toSet());

        fields.stream().
                filter(field -> field.getAnnotation(InjectByType.class).value().equals(InjectByType.class)).
                filter(field -> field.getType().equals(Connection.class)).
                forEach(field -> {
                    field.setAccessible(true);

                    try {
                        field.set(object, ConnectionPoolHolder.getInstance().getConnection());
                    } catch (IllegalAccessException e) {
                        throw new FieldInjectionException(object.getClass(), field);
                    }
                });


        fields.stream().
                filter(field -> field.getAnnotation(InjectByType.class).value().equals(InjectByType.class)).
                filter(field -> !field.getType().equals(Connection.class)).
                forEach(field -> {
                    field.setAccessible(true);

                    try {
                        field.set(object, context.getComponent(field.getType()));
                    } catch (IllegalAccessException e) {
                        throw new FieldInjectionException(object.getClass(), field);
                    }
                });

        fields.stream().
                filter(field -> !field.getAnnotation(InjectByType.class).value().equals(InjectByType.class)).
                forEach(field -> {
                    field.setAccessible(true);

                    try {
                        field.set(object, context.getComponent(field.getAnnotation(InjectByType.class).value()));
                    } catch (IllegalAccessException e) {
                        throw new FieldInjectionException(object.getClass(), field);
                    }
                });
    }
}
