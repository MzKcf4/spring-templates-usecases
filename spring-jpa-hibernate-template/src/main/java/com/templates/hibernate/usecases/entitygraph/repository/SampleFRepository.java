package com.templates.hibernate.usecases.entitygraph.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.templates.hibernate.usecases.entitygraph.entity.SampleFEntity;

@Repository
public interface SampleFRepository extends JpaRepository<SampleFEntity, Long>{
	
	@EntityGraph(value = "basic-entity-graph" , type = EntityGraphType.FETCH)
	public SampleFEntity findOneByStringField(String stringField);
	
	@EntityGraph(value = "basic-entity-graph-with-subgraph" , type = EntityGraphType.FETCH)
	@Query(value = "SELECT f from SampleFEntity f WHERE f.stringField = ?1")
	public SampleFEntity findOneByStringFieldWithSubGraph(String stringField);
}