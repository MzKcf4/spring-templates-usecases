package com.templates.usecases.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.templates.usecases.entities.SimpleRecordEntity;
import com.templates.usecases.repository.SimpleRecordRepository;



@Service
public class DbService {
	
	private final SimpleRecordRepository simpleRecordRepository;
	
	@Autowired
	public DbService(SimpleRecordRepository simpleRecordRepository) {
		this.simpleRecordRepository = simpleRecordRepository;
	}
	
	public void insertSimpleRecords(int count){
		List<SimpleRecordEntity> newRecordList = new ArrayList<SimpleRecordEntity>();
		for(int i = 0 ; i < count ; i++) {
			SimpleRecordEntity newEntity = new SimpleRecordEntity();
			newEntity.setName("NameOf-" + Integer.toString(i));
			newRecordList.add(newEntity);
		}
		simpleRecordRepository.saveAllAndFlush(newRecordList);
	}
	
}
