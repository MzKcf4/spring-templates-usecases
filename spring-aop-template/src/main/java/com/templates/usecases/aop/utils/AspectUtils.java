package com.templates.usecases.aop.utils;

import java.util.Arrays;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;

public class AspectUtils {
	
	public static <T> Optional<T> FindArgInJoinPoint(JoinPoint joinPoint , Class<T> argClass){
		return Arrays.stream(joinPoint.getArgs())
				.filter(argClass::isInstance)
				.map(argClass::cast)
				.findFirst();
	}
}
