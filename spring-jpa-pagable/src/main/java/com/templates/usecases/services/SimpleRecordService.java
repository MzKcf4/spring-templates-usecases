package com.templates.usecases.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.templates.usecases.dto.PageableDataDto;
import com.templates.usecases.dto.SimpleRecordDto;
import com.templates.usecases.entities.SimpleRecordEntity;
import com.templates.usecases.repository.SimpleRecordRepository;

@Service
public class SimpleRecordService {
	
	private final SimpleRecordRepository simpleRecordRepository;
	
	@Autowired
	public SimpleRecordService(SimpleRecordRepository simpleRecordRepository) {
		this.simpleRecordRepository = simpleRecordRepository;
	}
	
	public PageableDataDto<SimpleRecordDto> getSimpleRecords(Pageable pageableRequest){
		
		Page<SimpleRecordEntity> result = simpleRecordRepository.findAll(pageableRequest);
		
		List<SimpleRecordDto> simpleRecordDtoList = result.getContent().stream()
				.map(SimpleRecordDto::asSimpleRecordDto)
				.toList();
		
		return PageableDataDto.<SimpleRecordDto>builder()
			.elementList(simpleRecordDtoList)
			.pageIndex(result.getNumber())
			.pageSize(result.getSize())
			.pageElementCount(result.getNumber())
			.totalPages(result.getTotalPages())
			.totalElements(result.getTotalElements())
			.build();
		
	}
	
}
