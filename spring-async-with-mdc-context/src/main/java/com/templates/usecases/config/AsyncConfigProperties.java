package com.templates.usecases.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "config.async")
public class AsyncConfigProperties {
	
	private static Long DEFAULT_TIME_OUT = 30000L;
	private static Integer DEFAULT_CORE_POOL_SIZE = 1;
	private static Integer DEFAULT_MAX_POOL_SIZE = 1;
	
	
	private Long timeout = DEFAULT_TIME_OUT;
	private Integer corePoolSize = DEFAULT_CORE_POOL_SIZE;
	private Integer maxPoolSize = DEFAULT_MAX_POOL_SIZE;
}
