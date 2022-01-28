package com.templates.hibernate.usecases.services;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		// In directional relationship , you don't have to explicitly set/save both sides of entities
		sampleDbRepository.saveAll(sampleDbList);
		sampleBRepository.saveAndFlush(sampleB_1);
	}
}
