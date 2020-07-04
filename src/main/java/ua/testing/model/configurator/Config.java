package ua.testing.model.configurator;

import org.reflections.Reflections;

import java.util.Properties;

/**
 * Each class that provides configuration for {@link ua.testing.model.application.ApplicationContext}
 * have to implement this interface.
 */
public interface Config {
    Reflections getReflections();
    Properties getProperties();
}
