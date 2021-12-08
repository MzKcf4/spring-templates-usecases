package com.templates.usecases.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.templates.usecases.services.DbService;

/*
 * This class runs methods once , after Spring has started.
 * https://stackoverflow.com/questions/2401489/execute-method-on-startup-in-spring
 * */

@Component
public class StartupInitializer {
	
	@Autowired
	private DbService dbService;
	
	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent(){
		dbService.insertSimpleRecords(100);
	}
}
