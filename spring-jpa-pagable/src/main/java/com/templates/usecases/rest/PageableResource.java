package com.templates.usecases.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.templates.usecases.dto.PageableDataDto;
import com.templates.usecases.dto.SimpleRecordDto;
import com.templates.usecases.services.SimpleRecordService;

@RestController
public class PageableResource implements PageableResourceApi {

	@Autowired
	private SimpleRecordService simpleRecordService;
	
	@Override
	public PageableDataDto<SimpleRecordDto> getFromPage(Pageable pageable) {
		
		return simpleRecordService.getSimpleRecords(pageable);
	}
	
}
