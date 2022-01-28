package com.templates.hibernate.usecases.basic02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templates.hibernate.usecases.basic02.entity.SampleBEntity;

@Repository
public interface SampleBRepository extends JpaRepository<SampleBEntity, Long>{
	
}