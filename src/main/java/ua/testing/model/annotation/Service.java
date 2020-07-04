package ua.testing.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * All services have to be marked with this annotation to be cached into
 * {@link ua.testing.model.application.ApplicationContext} if {@link Controller#singleton()}
 * is true.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Service {
    boolean singleton() default true;
}
