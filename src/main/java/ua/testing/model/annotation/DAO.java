package ua.testing.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * All implementations {@link ua.testing.model.dao.GenericDAO} have to
 * be annotated with this annotation to be cached in {@link ua.testing.model.application.ApplicationContext}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DAO {
}
