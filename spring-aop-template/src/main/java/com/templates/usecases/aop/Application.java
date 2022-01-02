package com.templates.usecases.aop;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class Application {
	public static void main(String[] args) throws BeansException {
		ApplicationContext context = SpringApplication.run(Application.class, args);
	}
}