package com.templates.hibernate.usecases.entitygraph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templates.hibernate.usecases.entitygraph.entity.SampleGEntity;

@Repository
public interface SampleGRepository extends JpaRepository<SampleGEntity, Long>{
	
}