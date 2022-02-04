package com.templates.hibernate.usecases.lock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SAMPLE_OPT_LOCK")
public class SampleOptLockEntity {
	@Id
	@Column(name = "SAMPLE_OPT_LOCK_OID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Long longField;
	
	@Column(name = "VERSION")
	@Version
	private Long version;
}
