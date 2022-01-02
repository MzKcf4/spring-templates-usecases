package com.templates.usecases.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.templates.usecases.aop.annotation.CheckValue;
import com.templates.usecases.aop.annotation.ExtraLogInfoWithAspect;
import com.templates.usecases.aop.aspect.checker.StrictChecker;
import com.templates.usecases.aop.aspect.extralog.ExtraLogInfoCustom;


@RestController
public class TestController {
	
	@ExtraLogInfoWithAspect
	@GetMapping("/test")
	public String test(){
		return "Test";
	}
	
	@ExtraLogInfoWithAspect(logLevel = ExtraLogInfoCustom.class)
	@GetMapping("/test2")
	public String test2() {
		return "Test2";
	}
    
	
	@CheckValue
	@GetMapping("/defaultCheck")
	public String defaultCheck(String value) {
		return "DefaultCheck - Done";
	}
	
	@CheckValue(checker = StrictChecker.class)
	@GetMapping("/strictCheck")
	public String strictCheck(String value) {
		return "StrictCheck - Done";
	}
}
