package net.czela.backend.evidence.data.orientdb;

import io.micronaut.aop.Around;
import io.micronaut.context.annotation.AliasFor;
import io.micronaut.context.annotation.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Filip
 */
@Retention(RUNTIME)
@Target({ElementType.METHOD})
public @interface OrientProperty {
  @AliasFor(member = "name")
  String value() default "";

  @AliasFor(member = "value")
  String name() default "";

  boolean readonly() default false;
}
