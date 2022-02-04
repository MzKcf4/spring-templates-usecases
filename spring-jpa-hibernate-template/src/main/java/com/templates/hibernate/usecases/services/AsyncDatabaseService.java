package com.templates.hibernate.usecases.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.templates.hibernate.usecases.lock.entity.SampleOptLockEntity;
import com.templates.hibernate.usecases.lock.repository.SampleOptLockRepository;

@Component
@Transactional
public class AsyncDatabaseService {

	@Autowired
	private SampleOptLockRepository sampleOptLockRepository;
	
	@Async
	public void testRun_lock_asyncSave(SampleOptLockEntity optLockEntity , long sleepTime) throws InterruptedException {
		
		optLockEntity = sampleOptLockRepository.findAll().get(0);
		optLockEntity.setLongField(sleepTime);
		Thread.sleep(sleepTime);
		sampleOptLockRepository.saveAndFlush(optLockEntity);
	}
	
	
}
