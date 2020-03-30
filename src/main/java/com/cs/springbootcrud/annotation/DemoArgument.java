package com.cs.springbootcrud.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DemoArgument {
    String value() default "";
}

