package com.templates.usecases.aop.aspect.checker;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.templates.usecases.aop.annotation.CheckValue;

@Aspect
@Component
public class CheckValueAspect {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Pointcut("@annotation(com.templates.usecases.aop.annotation.CheckValue)")
	protected void checkByAnnotation() {
		
	}
	
	@Around("checkByAnnotation()")
	protected void doCheck(ProceedingJoinPoint joinPoint) {
		IValueChecker checker = getChecker(joinPoint);
		checker.check(joinPoint);
	}
	
	
	private IValueChecker getChecker(JoinPoint joinPoint){
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		CheckValue checkValueAnnotation = signature.getMethod().getAnnotation(CheckValue.class);
		Class<? extends IValueChecker> checker = checkValueAnnotation.checker();
		
		return applicationContext.getBean(checker);
	}
}
