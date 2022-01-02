package com.templates.usecases.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.templates.usecases.aop.aspect.checker.DefaultChecker;
import com.templates.usecases.aop.aspect.checker.IValueChecker;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckValue {
	
	Class<? extends IValueChecker> checker() default DefaultChecker.class;
}
