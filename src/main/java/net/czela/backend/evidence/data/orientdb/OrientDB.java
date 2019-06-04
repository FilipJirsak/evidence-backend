package net.czela.backend.evidence.data.orientdb;

import io.micronaut.aop.Around;
import io.micronaut.context.annotation.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Filip Jirsák
 */
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Around
@Type(OrientDBInterceptor.class)
public @interface OrientDB {
  boolean readOnly() default false;
}
