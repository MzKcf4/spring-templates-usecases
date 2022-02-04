package com.templates.hibernate.usecases.services;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.templates.hibernate.usecases.basic01.entity.SampleAEntity;
import com.templates.hibernate.usecases.basic01.enums.SampleEnum;
import com.templates.hibernate.usecases.basic01.repository.SampleARepository;
import com.templates.hibernate.usecases.basic02.entity.SampleBDetailsEntity;
import com.templates.hibernate.usecases.basic02.entity.SampleBEntity;
import com.templates.hibernate.usecases.basic02.entity.SampleCEntity;
import com.templates.hibernate.usecases.basic02.entity.SampleCbEntity;
import com.templates.hibernate.usecases.basic02.entity.SampleDEntity;
import com.templates.hibernate.usecases.basic02.entity.SampleDbEntity;
import com.templates.hibernate.usecases.basic02.repository.SampleBRepository;
import com.templates.hibernate.usecases.basic02.repository.SampleCRepository;
import com.templates.hibernate.usecases.basic02.repository.SampleDRepository;
import com.templates.hibernate.usecases.basic02.repository.SampleDbRepository;
import com.templates.hibernate.usecases.entitygraph.entity.SampleFEntity;
import com.templates.hibernate.usecases.entitygraph.entity.SampleGEntity;
import com.templates.hibernate.usecases.entitygraph.entity.SampleGgEntity;
import com.templates.hibernate.usecases.entitygraph.entity.SampleHfEntity;
import com.templates.hibernate.usecases.entitygraph.repository.SampleFRepository;
import com.templates.hibernate.usecases.entitygraph.repository.SampleGRepository;
import com.templates.hibernate.usecases.entitygraph.repository.SampleGgRepository;
import com.templates.hibernate.usecases.entitygraph.repository.SampleHfRepository;
import com.templates.hibernate.usecases.lock.entity.SampleOptLockEntity;
import com.templates.hibernate.usecases.lock.repository.SampleOptLockRepository;

@Service
@Transactional
public class DatabaseService {
	
	@Autowired
	private SampleARepository sampleARepository;
	
	@Autowired
	private SampleBRepository sampleBRepository;

	@Autowired
	private SampleCRepository sampleCRepository;
	
	@Autowired
	private SampleDRepository sampleDRepository;
	@Autowired
	private SampleDbRepository sampleDbRepository;
	
	@Autowired
	private SampleFRepository sampleFRepository;
	@Autowired
	private SampleGRepository sampleGRepository;
	@Autowired
	private SampleHfRepository sampleHfRepository;
	
	@Autowired
	private SampleGgRepository sampleGgRepository;
	
	@Autowired
	private SampleOptLockRepository sampleOptLockRepository;
	@Autowired
	private AsyncDatabaseService asyncDatabaseService;
	
	public void populateData_Basic01() {
		SampleAEntity newEnt = new SampleAEntity();
		newEnt.setEnumField(SampleEnum.EnumA);
		newEnt.setStringField("StringValue");
		newEnt.setTrueFalseField(true);
		newEnt.setYesNoField(false);
		sampleARepository.saveAndFlush(newEnt);
	}
	
	public void populateData_Basic02_directional() {
		SampleBEntity sampleB_1 = new SampleBEntity();
		
		SampleBDetailsEntity sampleBDetail = new SampleBDetailsEntity();
		sampleBDetail.setSampleB(sampleB_1);
		
		SampleCEntity sampleC = new SampleCEntity();
		SampleDEntity sampleD_1 = new SampleDEntity();
		SampleDEntity sampleD_2 = new SampleDEntity();
		sampleD_1.setSampleBEntity(sampleB_1);
		sampleD_2.setSampleBEntity(sampleB_1);
		List<SampleDEntity> sampleDList = Arrays.asList(sampleD_1 ,sampleD_2);
		
		sampleB_1.setSampleBDetail(sampleBDetail);
		sampleB_1.setSampleCEntity(sampleC);
		// In un-directional relationship , you will have to explicitly set/save both sides of entities
		sampleB_1.setSampleDEntitySet(sampleDList);
		
		SampleBEntity sampleB_2 = new SampleBEntity();
		sampleB_2.setSampleCEntity(sampleC);
		
		sampleBRepository.saveAll(Arrays.asList(sampleB_1 , sampleB_2));
		sampleCRepository.save(sampleC);
		sampleDRepository.saveAllAndFlush(sampleDList);
	}
	
	public void populateData_Basic02_undirectional() {
		SampleBEntity sampleB_1 = new SampleBEntity();
		
		SampleCbEntity sampleCb = new SampleCbEntity();
		sampleB_1.setSampleCbEntity(sampleCb);
		
		SampleDbEntity sampleDb_1 = new SampleDbEntity();
		SampleDbEntity sampleDb_2 = new SampleDbEntity();
		sampleDb_1.setSampleBEntity(sampleB_1);
		sampleDb_2.setSampleBEntity(sampleB_1);
		List<SampleDbEntity> sampleDbList = Arrays.asList(sampleDb_1 ,sampleDb_2);
		
		// In bi-directional relationship , you don't have to explicitly set/save both sides of entities
		sampleDbRepository.saveAll(sampleDbList);
		sampleBRepository.saveAndFlush(sampleB_1);
	}

	public void testRun_lock() {
		SampleOptLockEntity optLockEntity = new SampleOptLockEntity();
		optLockEntity.setLongField(0L);
		sampleOptLockRepository.saveAndFlush(optLockEntity);
		
		try {
			asyncDatabaseService.testRun_lock_asyncSave(null, 1000);
			asyncDatabaseService.testRun_lock_asyncSave(null, 500);
		} catch (ObjectOptimisticLockingFailureException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unexpected exception");
			e.printStackTrace();
		}
	}
	
	public void setup_entityGraph() {
		SampleFEntity sampleF = new SampleFEntity();
		SampleGEntity sampleG = new SampleGEntity();
		SampleHfEntity sampleHf_1 = new SampleHfEntity();
		SampleHfEntity sampleHf_2 = new SampleHfEntity();
		
		SampleGgEntity sampleGg_1 = new SampleGgEntity();
		SampleGgEntity sampleGg_2 = new SampleGgEntity();
		
		
		
		
		sampleHf_1.setSampleFEntity(sampleF);
		sampleHf_2.setSampleFEntity(sampleF);
		
		sampleG.setStringField("_SampleG_");
		sampleG.setSampleGgEntity(sampleGg_1);
		List<SampleGgEntity> sampleGgEntityList = Arrays.asList(sampleGg_1,sampleGg_2);
		
		
		sampleF.setSampleGEntity(sampleG);
		sampleF.setStringField("SampleF");
		
		List<SampleHfEntity> sampleHfList = Arrays.asList(sampleHf_1 , sampleHf_2); 
		sampleF.setSampleHfEntityList(sampleHfList);
		
		sampleGgRepository.saveAll(sampleGgEntityList);
		sampleHfRepository.saveAll(sampleHfList);
		sampleGRepository.save(sampleG);
		sampleFRepository.saveAndFlush(sampleF);
	}
	
	public void testRun_entityGraph() {
		System.out.println("----Fetching all SampleF in the DB :");
		SampleFEntity sampleF_1 = sampleFRepository.findAll().get(0);
		
		System.out.println("-----Getting a field from G Proxy Entity , you will see 1 additional DB call in console : ");
		sampleF_1.getSampleGEntity().getStringField();
		
		System.out.println("-----Getting a GG Entities from the G object , you will see 1 additional DB call in console : ");
		sampleF_1.getSampleGEntity().getSampleGgEntity().getStringField();
	}
	
	public void testRun_UseEntityGraph() {
		System.out.println("[UsingEntityGraph]");
		System.out.println("-----Getting a HF Entity from the proxy object");
		SampleFEntity sampleF_1 = sampleFRepository.findOneByStringField("SampleF");
		
		System.out.println("-----Getting a HF Entity from the proxy object , you will NOT see additional DB call in console : ");
		sampleF_1.getSampleGEntity().getStringField();
		
		System.out.println("-----Getting a GG Entities from the G object , you will see 1 additional DB call in console : ");
		sampleF_1.getSampleGEntity().getSampleGgEntity().getStringField();
	}
	
	public void testRun_UseEntitySubGraph() {
		System.out.println("[UsingEntitySubGraph]");
		System.out.println("-----Getting a HF Entity from the proxy object");
		SampleFEntity sampleF_1 = sampleFRepository.findOneByStringFieldWithSubGraph("SampleF");
		
		System.out.println("-----Getting a HF Entity from the proxy object , you will NOT see additional DB call in console : ");
		sampleF_1.getSampleGEntity().getStringField();
		
		System.out.println("-----Getting a GG Entity from the G object , you will NOT see additional DB call in console : ");
		sampleF_1.getSampleGEntity().getSampleGgEntity().getStringField();
	}

}


