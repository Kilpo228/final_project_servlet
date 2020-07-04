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

/**
 * This class configures object fields annotated with {@link InjectByType}.
 */
public class InjectByTypeObjectConfigurator implements ObjectConfigurator {
    /**
     * First stream will get all fields annotated with {@link InjectByType} with
     * {@link InjectByType#value()} equals {@link InjectByType}. Then will found
     * fields with type {@link Connection} and will assign implementation by
     * {@link ConnectionPoolHolder}.
     *
     * Second stream will get all fields annotated with {@link InjectByType} with
     * {@link InjectByType#value()} equals {@link InjectByType}. Then will found
     * all fields not equals {@link Connection} and will assign implementation from
     * cached object in {@link ApplicationContext#getComponent(Class)}.
     *
     * Third stream will get all fields annotated with {@link InjectByType} with
     * {@link InjectByType#value()} not equals {@link InjectByType}. And will assign
     * object to this filed from cached object in {@link ApplicationContext} or will
     * construct new one.
     *
     * @param object which fields to configure
     * @param context reference to {@link ApplicationContext} object
     */
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
