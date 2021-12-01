package com.templates.usecases.services;

import java.util.concurrent.Future;

import org.jboss.logging.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncWorkerService {
	
	@Async("defaultAsyncExecutor")
	public Future<String> doDefaultAsync() throws InterruptedException {
		// Security Context should be lost 
		log.info("SecurityContext in defaultAsync : " + SecurityContextHolder.getContext().getAuthentication());
		// Mdc context should be lost
	    log.info("MDC in defaultAsync : " + MDC.getMap());
		Thread.sleep(1000L);
		
		return new AsyncResult<String>("Async call done");
	}
	
	@Async("asyncSecurityContextDelegationExecutor")
	public Future<String> doAsyncWithDelegation() throws InterruptedException {
		// Security Context should be kept
		log.info("SecurityContext in asyncContextDelegationExecutor : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		// Mdc context should be lost
	    log.info("MDC in asyncContextDelegationExecutor : " + MDC.getMap());
		Thread.sleep(1000L);
		
		return new AsyncResult<String>("Async call done");
	}
	
	@Async("asyncSecurityContextAndMdcDelegationExecutor")
	public Future<String> doAsyncWithSecurityAndMdcDelegation() throws InterruptedException {
		// Security Context should be kept
		log.info("SecurityContext in doAsyncWithSecurityAndMdcDelegation : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		// Mdc context should be kept
	    log.info("MDC in doAsyncWithSecurityAndMdcDelegation : " + MDC.getMap());
		Thread.sleep(1000L);
		
		return new AsyncResult<String>("Async call done");
	}
}
