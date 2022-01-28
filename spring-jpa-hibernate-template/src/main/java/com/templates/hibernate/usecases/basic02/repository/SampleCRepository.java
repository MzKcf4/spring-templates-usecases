package com.templates.hibernate.usecases.basic02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templates.hibernate.usecases.basic02.entity.SampleCEntity;

@Repository
public interface SampleCRepository extends JpaRepository<SampleCEntity, Long>{
	
}