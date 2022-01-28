package com.templates.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.templates.hibernate.usecases.services.DatabaseService;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
        
        DatabaseService dbService = context.getBean(DatabaseService.class);
        dbService.populateData_Basic01();
        dbService.populateData_Basic02_directional();
        dbService.populateData_Basic02_undirectional();
        
	}
}
