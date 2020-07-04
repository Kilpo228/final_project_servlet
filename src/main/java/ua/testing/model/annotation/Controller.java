package ua.testing.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * All implementations of {@link ua.testing.controller.command.Command}
 * have to be marked with this annotation to be cached into
 * {@link ua.testing.model.application.ApplicationContext} if {@link Controller#singleton()}
 * is true.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
    boolean singleton() default true;
}
