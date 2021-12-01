package com.springtemplate.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springtemplate.jpa.entity.ReadonlyRecordEntity;
import com.springtemplate.jpa.repository.ReadonlyRecordRepository;


@Service
public class DbServiceImpl implements DbService {
	
	@Autowired
	ReadonlyRecordRepository readonlyRecordRepository;
	
	@Override
	@Transactional	
	public void insertReadonlyRecords(int count) {
		List<ReadonlyRecordEntity> newList = new ArrayList<>();
		for(int i = 0 ; i < count ; i++) {
			ReadonlyRecordEntity entity = new ReadonlyRecordEntity();
			entity.setName(Integer.toString(i));
			newList.add(entity);
		}
		readonlyRecordRepository.saveAllAndFlush(newList);
	}
	
	@Override
	@Transactional(readOnly = true)
	public void loadAllReadonlyRecord_ReadonlyMode(){
		List<ReadonlyRecordEntity> list = readonlyRecordRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = false)
	public void loadAllReadonlyRecord_Not_ReadonlyMode(){
		List<ReadonlyRecordEntity> list = readonlyRecordRepository.findAll();
	}
	
}
