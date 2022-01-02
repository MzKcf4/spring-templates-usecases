package com.templates.usecases.aop.aspect.extralog;

import org.springframework.stereotype.Component;

@Component
public class ExtraLogInfoDefault implements IExtraLogInfo {

	@Override
	public String getExtraMessage() {
		return "Extra Info from Default !";
	}

}
