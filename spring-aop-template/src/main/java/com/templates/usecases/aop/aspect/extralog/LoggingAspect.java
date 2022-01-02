package com.templates.usecases.aop.aspect.extralog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.templates.usecases.aop.annotation.ExtraLogInfoWithAspect;

@Aspect
@Component
public class LoggingAspect {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	// There are several ways to define Pointcut expressions :
	// https://www.baeldung.com/spring-aop-pointcut-tutorial
	
	// pointcut tells what methods will advise handle, they don't process anything.
	// They provide a more readable way to chain the pointcuts by advices
	@Pointcut("@annotation(com.templates.usecases.aop.annotation.ExtraLogInfoWithAspect)")
	protected void logByAnnotation() {
		
	}
	
	@Pointcut("@within(org.springframework.web.bind.annotation.RestController) || @annotation(org.springframework.web.bind.annotation.RestController)")
	protected void restController() {
		
	}
		
	// Usually we do actual handling here 
	@Before("logByAnnotation()")
	public void beforeLogByAnnotation(JoinPoint joinpoint) {
		System.out.println("[beforeLogByAnnotation] before execution of annotated method or @restController");
		printExtraInfo(joinpoint);
	}
	
	
	
	// Note that expression need to include *Package Name* if they're not in same package !
	@Around("logByAnnotation()")
	public Object aroundPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[Around] before execution");
		
		Object proceededObject = joinPoint.proceed();
		
		System.out.println("[Around] after execution");
		
		return proceededObject;
	} 
	
	protected void printExtraInfo(JoinPoint joinPoint)
	{
		IExtraLogInfo extraLogInfo = findLogLevel(joinPoint);
		System.out.println("[ExtraInfo] : " + extraLogInfo.getExtraMessage());
	}
	
	
	private IExtraLogInfo findLogLevel(JoinPoint joinPoint){
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		ExtraLogInfoWithAspect logWithAspectAnnotation = signature.getMethod().getAnnotation(ExtraLogInfoWithAspect.class);
		Class<? extends IExtraLogInfo> logLevel = logWithAspectAnnotation.logLevel();
		
		return applicationContext.getBean(logLevel);
	}
}
