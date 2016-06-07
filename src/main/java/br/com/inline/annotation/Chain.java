package br.com.inline.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lacau on 05/05/16.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Chain {

    String qualifier();

    Class<? extends org.apache.commons.chain.Command>[] commands() default org.apache.commons.chain.Command.class;
}