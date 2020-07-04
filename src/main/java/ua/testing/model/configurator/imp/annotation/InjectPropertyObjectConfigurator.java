package ua.testing.model.configurator.imp.annotation;

import ua.testing.model.annotation.InjectProperty;
import ua.testing.model.application.ApplicationContext;
import ua.testing.model.configurator.ObjectConfigurator;

import java.util.stream.Stream;

/**
 * This class configures object fields annotated with {@link InjectProperty}
 */
public class InjectPropertyObjectConfigurator implements ObjectConfigurator {
    @Override
    public void configure(Object object, ApplicationContext context) {
        Class<?> clazz = object.getClass();

        Stream.of(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(InjectProperty.class)).
                forEach(field -> {
                    field.setAccessible(true);

                    try {
                        field.set(object, context.
                                getConfig().
                                getProperties().
                                getProperty(field.getAnnotation(InjectProperty.class).value()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
