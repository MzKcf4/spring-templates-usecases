package com.templates.usecases.decorator;

import java.util.Map;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

// https://stackoverflow.com/questions/45890181/logging-mdc-with-async-and-taskdecorator
public class DelegatingMdcTaskDecorator implements TaskDecorator {
	
	@Override
	public Runnable decorate(Runnable runnable) {
	    // Web thread context
	    // Get the logging MDC context
	    Map<String, String> contextMap = MDC.getCopyOfContextMap();

	    return () -> {
	        try {
	            // @Async thread context
	            // Restore the web thread MDC context
	            if(contextMap != null) {
	                MDC.setContextMap(contextMap);
	            }
	            else {
	                MDC.clear();
	            }

	            // Run the new thread
	            runnable.run();
	        }
	        finally {
	            MDC.clear();
	        }
	    };
	}
	

}
