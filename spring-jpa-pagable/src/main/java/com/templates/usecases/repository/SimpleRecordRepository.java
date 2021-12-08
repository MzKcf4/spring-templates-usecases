package com.templates.usecases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templates.usecases.entities.SimpleRecordEntity;

@Repository
public interface SimpleRecordRepository extends JpaRepository<SimpleRecordEntity, Long>{

}
