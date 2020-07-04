package ua.testing.model.factory;

import ua.testing.model.application.ApplicationContext;
import ua.testing.model.configurator.ObjectConfigurator;
import ua.testing.model.exceptions.MultipleImplementationException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class create object and configures each one, if implementations
 * of {@link ObjectConfigurator} was found.
 */
public class ObjectFactory {
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private ApplicationContext context;

    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        context.getConfig().getReflections().getSubTypesOf(ObjectConfigurator.class).forEach(configurator -> {
            try {
                configurators.add(configurator.getDeclaredConstructor().newInstance());
            } catch (InstantiationException |
                    NoSuchMethodException |
                    InvocationTargetException |
                    IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * This method create object of class and configure it with all founded
     * implementations of {@link ObjectConfigurator}.
     *
     * @param clazz class of object to create and configure
     * @return configured object
     */
    public <T> T createObject(Class<? extends T> clazz) {
        T obj;

        try {
            obj = getType(clazz).getDeclaredConstructor().newInstance();
        } catch (InstantiationException |
                NoSuchMethodException |
                InvocationTargetException |
                IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        configurators.forEach(configurator -> configurator.configure(obj, context));
        return obj;
    }

    /**
     * This methods check if class is interface and look for it's implementation.
     * If class isn't interface then method will return class itself.
     *
     * @param clazz class to determine type
     * @throws MultipleImplementationException if were found more than 1 implementation of interface
     * @return class itself or implementation of class
     */
    private <T> Class<? extends T> getType(Class<T> clazz) {
        if (clazz.isInterface()) {
            Set<Class<? extends T>> imp = context.getConfig().getReflections().getSubTypesOf(clazz);

            if (imp.size() != 1) {
                throw new MultipleImplementationException(clazz);
            }

            return imp.iterator().next();
        } else {
            return clazz;
        }
    }
}
