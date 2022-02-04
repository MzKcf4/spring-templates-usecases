package com.templates.hibernate.usecases.entitygraph.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SAMPLE_HF")
public class SampleHfEntity {
	
	@Id
	@Column(name = "SAMPLE_HF_OID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// Join on another table's  self-reference , THIS entity owns the relation
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAMPLE_F_OID")
	private SampleFEntity sampleFEntity;
	
	@Column(name = "STRING_FIELD")
	private String stringField;
	
}
