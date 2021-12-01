package com.templates.usecases.services;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.templates.usecases.enums.AsyncType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncService {
	
	@Autowired
	private AsyncWorkerService asyncWorkerService;
	
	public String doAsync(AsyncType asyncType , long timeout) {
		MDC.put("CustomMdcKey", "CustomMdcValue");
		
	    log.info("SecurityContext Before Async logic : " + SecurityContextHolder.getContext().getAuthentication());
	    log.info("MDC Before Async logic : " + MDC.getMap());
	    
	    try {
	    	Future<String> result;
	    	if(asyncType == AsyncType.DEFAULT)
	    		result = asyncWorkerService.doDefaultAsync();
	    	else if (asyncType == AsyncType.DELEGLATE_SECURITY)
	    		result = asyncWorkerService.doAsyncWithDelegation();
	    	else if (asyncType == AsyncType.DELEGLATE_SECURITY_MDC)
	    		result = asyncWorkerService.doAsyncWithSecurityAndMdcDelegation();
	    	else
    			return "";
	    	
	    	return result.get(timeout , TimeUnit.MILLISECONDS);
	    } catch (TimeoutException e) {
    		return "Timeout";
		} catch (Exception ee) {
			ee.printStackTrace();
			return "Error";
		}
	}
	

}
