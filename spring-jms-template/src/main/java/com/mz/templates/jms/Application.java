package com.mz.templates.jms;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mz.templates.jms.messaging.SampleMessaging;
import com.mz.templates.jms.messaging.SampleMessagingWithId;

@SpringBootApplication
public class Application {
	public static void main(String[] args) throws BeansException {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		context.getBean(SampleMessaging.class).sendRequest();
		context.getBean(SampleMessagingWithId.class).sendAndReceive("customMessageId");
	}
}