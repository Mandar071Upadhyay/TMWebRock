package com.thinking.machines.webrock.annotation;
import java.lang.annotation.*;
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParameter
{
public String value() default "";
}