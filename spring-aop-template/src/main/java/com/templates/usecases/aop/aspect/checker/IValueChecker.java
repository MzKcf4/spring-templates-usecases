package com.templates.usecases.aop.aspect.checker;

import org.aspectj.lang.JoinPoint;

public interface IValueChecker {
	
	void check(JoinPoint joinPoint);
}
