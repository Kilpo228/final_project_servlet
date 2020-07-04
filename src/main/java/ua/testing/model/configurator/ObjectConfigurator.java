package ua.testing.model.configurator;

import ua.testing.model.application.ApplicationContext;

/**
 * All classes which configures object have to implement this class.
 * All implementations will be add to {@link ua.testing.model.factory.ObjectFactory}.
 *
 * @see ua.testing.model.factory.ObjectFactory
 */
public interface ObjectConfigurator {
    void configure(Object object, ApplicationContext context);
}
