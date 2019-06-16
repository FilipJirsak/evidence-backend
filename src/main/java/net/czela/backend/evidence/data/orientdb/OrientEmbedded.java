package net.czela.backend.evidence.data.orientdb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Filip
 */
@Retention(RUNTIME)
@Target({ElementType.METHOD})
public @interface OrientEmbedded {
}
