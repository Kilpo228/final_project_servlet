package ua.testing.model.configurator;

import org.reflections.Reflections;

import java.util.Properties;

public interface Config {
    Reflections getReflections();
    Properties getProperties();
}
