package com.templates.hibernate.usecases.entitygraph.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SAMPLE_G")
public class SampleGEntity {
	
	@Id
	@Column(name = "SAMPLE_G_OID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="sampleGEntity" , fetch = FetchType.LAZY)
    private Set<SampleFEntity> sampleFEntitySet;
	
	// Join on another table's  self-reference , THIS entity owns the relation
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAMPLE_GG_OID")
	private SampleGgEntity sampleGgEntity;
    
	@Column(name = "STRING_FIELD")
	private String stringField;
}
