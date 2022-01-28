package com.templates.hibernate.usecases.basic02.entity;

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
@Table(name = "SAMPLE_DB")
public class SampleDbEntity {
	
	@Id
	@Column(name = "SAMPLE_DB_OID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// Join on another table's  self-reference , THIS entity owns the relation
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAMPLE_B_OID")
	private SampleBEntity sampleBEntity;
	
}
