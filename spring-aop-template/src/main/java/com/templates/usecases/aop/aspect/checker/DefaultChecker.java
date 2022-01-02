package com.templates.usecases.aop.aspect.checker;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
public class DefaultChecker implements IValueChecker {

	@Override
	public void check(JoinPoint joinPoint) {
		System.out.println("[DefaultChecker] No checking is done in default checker");
	}

}
