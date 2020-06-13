package ua.testing.model.configurator;

import ua.testing.model.application.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object object, ApplicationContext context);
}
