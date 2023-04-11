package com.thinking.machines.webrock.annotation;
import java.lang.annotation.*;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Forward
{
public String value() default "";
}