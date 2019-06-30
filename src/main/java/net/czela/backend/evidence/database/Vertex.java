package net.czela.backend.evidence.database;

import io.micronaut.context.annotation.AliasFor;
import io.micronaut.core.annotation.Introspected;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Filip
 */
@Retention(RUNTIME)
@Target({ElementType.TYPE})
public @interface Vertex {
  @AliasFor(member = "type")
  String value() default "";

  @AliasFor(member = "value")
  String type() default "";
}
