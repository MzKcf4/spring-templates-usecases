package com.templates.usecases.rest;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.templates.usecases.dto.PageableDataDto;
import com.templates.usecases.dto.SimpleRecordDto;

public interface PageableResourceApi {
	
	// https://stackoverflow.com/questions/34889613/passing-pageable-spring-data-in-post-request
	// Pageable parameters need to be passed by request parameters 
	// 	(e.g  test-pageable?page=2&size=10)
	@GetMapping(value = "/test-pageable" , produces = MediaType.APPLICATION_JSON_VALUE)
	PageableDataDto<SimpleRecordDto> getFromPage(Pageable pageable);

}
