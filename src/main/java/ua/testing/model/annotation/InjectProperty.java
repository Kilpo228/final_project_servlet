package ua.testing.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * In each class marked with {@link Service}, {@link Controller}, {@link DAO}
 * each field annotated with this annotation will be assigned to according
 * value from application.properties.
 *
 * @see ua.testing.model.configurator.imp.annotation.InjectPropertyObjectConfigurator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectProperty {
    String value();
}
