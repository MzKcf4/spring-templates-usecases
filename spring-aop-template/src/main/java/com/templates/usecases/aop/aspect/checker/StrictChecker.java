package com.templates.usecases.aop.aspect.checker;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import com.templates.usecases.aop.utils.AspectUtils;

@Component
public class StrictChecker implements IValueChecker {

	@Override
	public void check(JoinPoint joinPoint) {
		Optional<String> valueToCheckOptional = AspectUtils.FindArgInJoinPoint(joinPoint, String.class);
		if(valueToCheckOptional.isPresent()) {
			String value = valueToCheckOptional.get();
			if(StringUtils.isBlank(value)) {
				throw new RuntimeException("[StrictChecker] Empty value not allowed!");
			}
		}
		System.out.println("[StrictChecker] value is valid");
		
	}

}
