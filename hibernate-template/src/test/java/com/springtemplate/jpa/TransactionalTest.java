package com.springtemplate.jpa;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springtemplate.jpa.service.DbService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTest {
	@Autowired 
	private DbService dbService;
	
	@Test
	public void test() {
		dbService.insertReadonlyRecords(5000);
		
		runAll();		// Warmup
		runAll();
		runAll();
		runAll();
		runAll();
		runAll();
	}
	
	private void runAll() {
		test_readonly();
		test_not_readonly();
		System.out.println("==============================================");
	}
	
	private void test_readonly() {
		StopWatch watch = new StopWatch();
		watch.start();
		for(int i = 0 ; i < 1000 ; i++) {
			dbService.loadAllReadonlyRecord_ReadonlyMode();
		}
		watch.stop();
		System.out.println("Load All with readonly takes : " + watch.getTime());
	}
	
	private void test_not_readonly() {
		StopWatch watch = new StopWatch();
		watch.start();
		for(int i = 0 ; i < 1000 ; i++) {
			dbService.loadAllReadonlyRecord_Not_ReadonlyMode();
		}
		watch.stop();
		System.out.println("Load All without readonly takes : " + watch.getTime());
	}
}
