package com.templates.hibernate.usecases.basic01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templates.hibernate.usecases.basic01.entity.SampleAEntity;

@Repository
public interface SampleARepository extends JpaRepository<SampleAEntity, Long>{
	
}