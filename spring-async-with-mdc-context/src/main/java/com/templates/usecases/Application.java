package com.templates.usecases;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) throws BeansException {
		SpringApplication.run(Application.class, args);
	}
}