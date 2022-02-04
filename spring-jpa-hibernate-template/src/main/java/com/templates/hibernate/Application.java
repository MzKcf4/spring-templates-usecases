package com.templates.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.templates.hibernate.usecases.services.DatabaseService;

@SpringBootApplication
@EnableAsync
public class Application {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
        
        DatabaseService dbService = context.getBean(DatabaseService.class);
        // dbService.populateData_Basic01();
        // dbService.populateData_Basic02_directional();
        // dbService.populateData_Basic02_undirectional();
        // dbService.testRun_lock();
        dbService.setup_entityGraph();
        dbService.testRun_entityGraph();
        dbService.testRun_UseEntityGraph();
        dbService.testRun_UseEntitySubGraph();

	}
	
    @Bean
    public TaskExecutor workExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        threadPoolTaskExecutor.setCorePoolSize(3);
        threadPoolTaskExecutor.setMaxPoolSize(3);
        threadPoolTaskExecutor.setQueueCapacity(600);
        threadPoolTaskExecutor.afterPropertiesSet();
        return threadPoolTaskExecutor;
    }
}
