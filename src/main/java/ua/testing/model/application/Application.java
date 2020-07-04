package ua.testing.model.application;

import ua.testing.model.configurator.imp.ApplicationConfig;
import ua.testing.model.factory.ObjectFactory;

/**
 * This is main class which have only one method to
 * initialize {@link ApplicationContext} and {@link ObjectFactory}
 * and return ApplicationContext with cached classes.
 *
 * @see ApplicationContext
 * @see ObjectFactory
 */
public class Application {
    public static ApplicationContext run(String packageToScan) {
        ApplicationContext context = new ApplicationContext(new ApplicationConfig(packageToScan));
        ObjectFactory factory = new ObjectFactory(context);
        context.setObjectFactory(factory);
        context.scan();
        return context;
    }
}
