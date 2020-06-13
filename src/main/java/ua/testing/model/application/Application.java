package ua.testing.model.application;

import ua.testing.model.configurator.imp.ApplicationConfig;
import ua.testing.model.factory.ObjectFactory;

public class Application {
    public static ApplicationContext run(String packageToScan) {
        ApplicationContext context = new ApplicationContext(new ApplicationConfig(packageToScan));
        ObjectFactory factory = new ObjectFactory(context);
        context.setObjectFactory(factory);
        context.scan();
        return context;
    }
}
