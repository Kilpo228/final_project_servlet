package ua.testing.model.application;

import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.DAO;
import ua.testing.model.annotation.Service;
import ua.testing.model.configurator.Config;
import ua.testing.model.factory.ObjectFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class holds all classes marked with:
 * {@link ua.testing.model.annotation.Service},
 * {@link ua.testing.model.annotation.DAO},
 * {@link ua.testing.model.annotation.Controller}.
 *
 * @see ua.testing.model.annotation.Service
 * @see ua.testing.model.annotation.Controller
 * @see ua.testing.model.annotation.DAO
 */
public class ApplicationContext {
    private Map<Class, Object> cachedControllers = new HashMap<>();
    private Map<Class, Object> cachedComponents = new HashMap<>();
    private ObjectFactory objectFactory;
    private Config config;

    ApplicationContext(Config config) {
        this.config = config;
    }

    /**
     * This method search for annotated classes in specified package in
     * order: {@link DAO}, {@link Service}, {@link Controller} and
     * stores each object in {@link ApplicationContext#cachedControllers} if
     * class was annotated with {@link Controller} or in {@link ApplicationContext#cachedComponents}
     * if class was annotated with {@link DAO} or {@link Service}.
     * If class marked with {@link Service} or {@link Controller} has
     * {@link Service#singleton()} or {@link Controller#singleton()} false,
     * object of this class won't be cached.
     */
    void scan() {
        config.getReflections().getTypesAnnotatedWith(DAO.class).
                forEach(clazz ->
                        Arrays.stream(clazz.getInterfaces()).
                                forEach(i ->
                                        cachedComponents.put(i, objectFactory.createObject(clazz)))
                );

        config.getReflections().getTypesAnnotatedWith(Service.class).stream().
                filter(service -> service.getAnnotation(Service.class).singleton()).
                forEach(service -> cachedComponents.put(service, objectFactory.createObject(service)));

        config.getReflections().getTypesAnnotatedWith(Controller.class).stream().
                filter(controller -> controller.getAnnotation(Controller.class).singleton()).
                forEach(controller -> cachedControllers.put(controller, objectFactory.createObject(controller)));
    }

    /**
     * This methods uses in implementations of {@link ua.testing.model.configurator.ObjectConfigurator}
     * to get cached object from {@link ApplicationContext#cachedComponents} or
     * construct new object.
     *
     * @param clazz key of object in {@link ApplicationContext#cachedComponents}
     * @return cached object from {@link ApplicationContext#cachedComponents} or new object
     */
    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<T> clazz) {
        if (cachedComponents.containsKey(clazz)) {
            return (T) cachedComponents.get(clazz);
        } else {
            return objectFactory.createObject(clazz);
        }
    }

    /**
     * This method uses to get cached object from {@link ApplicationContext#cachedControllers} or
     * construct new object. Uses in {@link ua.testing.controller.Servlet}.
     *
     * @param clazz key of object in {@link ApplicationContext#cachedControllers}
     * @return cached object from {@link ApplicationContext#cachedControllers} or new object
     */
    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> clazz) {
        return (T) Optional.of(cachedControllers.get(clazz)).orElse(objectFactory.createObject(clazz));
    }

    public Config getConfig() {
        return config;
    }

    void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }
}
