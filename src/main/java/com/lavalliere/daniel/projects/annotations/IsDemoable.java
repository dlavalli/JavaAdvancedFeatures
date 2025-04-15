package com.lavalliere.daniel.projects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// https://www.baeldung.com/java-custom-annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IsDemoable {
    // name attribute Required by the spring annotation scanner
    // but can be overwritten to value or some other value
    String name() default "com.lavalliere.daniel.projects.annotations.Demoable";
}
