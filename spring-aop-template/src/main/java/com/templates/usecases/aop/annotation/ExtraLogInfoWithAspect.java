package com.templates.usecases.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.templates.usecases.aop.aspect.extralog.ExtraLogInfoDefault;
import com.templates.usecases.aop.aspect.extralog.IExtraLogInfo;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtraLogInfoWithAspect {
	
	Class<? extends IExtraLogInfo> logLevel() default ExtraLogInfoDefault.class; 
}
