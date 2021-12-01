package com.springtemplate.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springtemplate.jpa.entity.ReadonlyRecordEntity;

@Repository
public interface ReadonlyRecordRepository extends JpaRepository<ReadonlyRecordEntity, Long>{
	
}
