package com.templates.hibernate.usecases.entitygraph.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SAMPLE_GG")
public class SampleGgEntity {
	@Id
	@Column(name = "SAMPLE_GG_OID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// A bidirectional association via a foreign key 
	// 'mappedBy' indicates it's not relationship owner 
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "sampleGgEntity", fetch = FetchType.LAZY)
	private List<SampleGEntity> sampleGEntityList;
    
	@Column(name = "STRING_FIELD")
	private String stringField;
}
