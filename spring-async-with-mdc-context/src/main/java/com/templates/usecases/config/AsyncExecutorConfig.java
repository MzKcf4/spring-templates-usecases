package com.templates.usecases.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import com.templates.usecases.decorator.DelegatingMdcTaskDecorator;

@EnableConfigurationProperties(AsyncConfigProperties.class)
@Configuration
@EnableAsync
public class AsyncExecutorConfig {
	
	private AsyncConfigProperties asyncConfigProperties;
	
	@Autowired
	public AsyncExecutorConfig(AsyncConfigProperties asyncConfigProperties) {
		this.asyncConfigProperties = asyncConfigProperties;
	}
	
	@Bean("defaultAsyncExecutor")
	public AsyncTaskExecutor defaultAsyncTaskExecutor() {
		ThreadPoolTaskExecutor executor = getExecutor("Default-Async-Task");
		executor.initialize();
		return executor;
	}
	
	@Bean("asyncSecurityContextDelegationExecutor")
	public AsyncTaskExecutor asyncSecurityContextDelegationExecutor() {
		ThreadPoolTaskExecutor executor = getExecutor("Security-Delegation-Async-Task");
		executor.initialize();
		return new DelegatingSecurityContextAsyncTaskExecutor(executor);
	}
	
	@Bean("asyncSecurityContextAndMdcDelegationExecutor")
	public AsyncTaskExecutor asyncSecurityContextAndMdcDelegationExecutor() {
		ThreadPoolTaskExecutor executor = getExecutor("Security-MDC-Delegation-Async-Task");
		executor.setTaskDecorator(new DelegatingMdcTaskDecorator());
		executor.initialize();
		return new DelegatingSecurityContextAsyncTaskExecutor(executor);
	}
	
	private ThreadPoolTaskExecutor getExecutor(String threadNamePrefix) {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(asyncConfigProperties.getCorePoolSize());
		executor.setMaxPoolSize(asyncConfigProperties.getMaxPoolSize());
		executor.setThreadNamePrefix(threadNamePrefix);
		return executor;
	}
}
