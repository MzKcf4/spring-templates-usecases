package com.templates.hibernate.usecases.basic02.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "SAMPLE_CB")
public class SampleCbEntity {
	
	@Id
	@Column(name = "SAMPLE_CB_OID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// 'mappedBy' indicates it's not relationship owner , the table in DB will NOT hold the SAMPLE_B_OID column
    @OneToMany(cascade=CascadeType.ALL, mappedBy="sampleCbEntity")
    private Set<SampleBEntity> sampleBEntitySet;
	
}
