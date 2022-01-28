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
@Table(name = "SAMPLE_D")
public class SampleDEntity {
	
	@Id
	@Column(name = "SAMPLE_D_OID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// Many of THIS entity can have 1 B Entity	
	// A unidirectional association via a foreign key
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAMPLE_B_OID")		// Use this FK column to match the PK in SampleB table
	private SampleBEntity sampleBEntity;
}