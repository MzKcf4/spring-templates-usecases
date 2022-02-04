package com.templates.hibernate.usecases.basic02.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SAMPLE_C")
public class SampleCEntity {
	
	@Id
	@Column(name = "SAMPLE_C_OID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
}
