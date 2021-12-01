package com.templates.usecases.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.templates.usecases.enums.AsyncType;
import com.templates.usecases.services.AsyncService;

@RestController
@RequestMapping(path = "async")
public class AsyncResource {
	
	@Autowired
	private AsyncService asyncService;
	
	@GetMapping("/test")
	public String testAsync(@RequestParam("asyncType") AsyncType asyncType , @RequestParam("timeout") Long timeout) {
		return asyncService.doAsync(asyncType , timeout);
	}
}
