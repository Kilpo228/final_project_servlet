package ua.testing.model.factory;

import ua.testing.model.application.ApplicationContext;
import ua.testing.model.configurator.ObjectConfigurator;
import ua.testing.model.exceptions.MultipleImplementationException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
