package ua.testing.model.configurator.imp;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import ua.testing.model.application.ApplicationContext;
import ua.testing.model.configurator.Config;
import ua.testing.model.dao.connection.ConnectionPoolHolder;
import ua.testing.model.util.ScriptRunner;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class ApplicationConfig implements Config {
    private Properties properties = new Properties();
    private Reflections reflections;

    public ApplicationConfig(String packageToScan) {
        this.reflections = new Reflections(packageToScan, new TypeAnnotationsScanner(), new SubTypesScanner());
        checkResources();
    }

    @Override
    public Reflections getReflections() {
        return reflections;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    private void checkResources() {
        ClassLoader classLoader = ApplicationContext.class.getClassLoader();

        Optional.ofNullable(classLoader.getResourceAsStream("application.properties")).
                ifPresent(stream -> {
                    try {
                        properties.load(stream);
                        stream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        Optional.ofNullable(classLoader.getResource("data.sql")).
                ifPresent(file ->
                        ScriptRunner.execute(ConnectionPoolHolder.getInstance().getConnection(), file.getPath()));
    }
}
