package com.templates.hibernate.usecases.lock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templates.hibernate.usecases.basic02.entity.SampleBEntity;
import com.templates.hibernate.usecases.lock.entity.SampleOptLockEntity;

@Repository
public interface SampleOptLockRepository extends JpaRepository<SampleOptLockEntity, Long>{
	
}