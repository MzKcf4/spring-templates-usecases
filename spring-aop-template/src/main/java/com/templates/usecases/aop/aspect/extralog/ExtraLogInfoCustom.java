package com.templates.usecases.aop.aspect.extralog;

import org.springframework.stereotype.Component;

@Component
public class ExtraLogInfoCustom implements IExtraLogInfo {
	
	@Override
	public String getExtraMessage() {
		return "This is custom extra message !";
	}
	
}
