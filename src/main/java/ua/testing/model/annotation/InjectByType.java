package ua.testing.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * In each class marked with {@link Service}, {@link Controller}, {@link DAO}
 * each field annotated with this annotation will be assigned to
 * cached object from {@link ua.testing.model.application.ApplicationContext}
 * with according data type.
 *
 * If annotated field is {@link java.sql.Connection}, {@link ua.testing.model.dao.connection.ConnectionPoolHolder}
 * will assign implementation to it.
 *
 * @see ua.testing.model.configurator.imp.annotation.InjectByTypeObjectConfigurator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectByType {
    Class<?> value() default InjectByType.class;
}
