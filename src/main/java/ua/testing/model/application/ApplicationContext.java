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

public class ApplicationContext {
    private Map<Class, Object> cachedControllers = new HashMap<>();
    private Map<Class, Object> cachedComponents = new HashMap<>();
    private ObjectFactory objectFactory;
    private Config config;

    ApplicationContext(Config config) {
        this.config = config;
    }

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

    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<T> clazz) {
        if (cachedComponents.containsKey(clazz)) {
            return (T) cachedComponents.get(clazz);
        } else {
            return objectFactory.createObject(clazz);
        }
    }

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
