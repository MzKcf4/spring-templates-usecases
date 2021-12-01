package com.springtemplate.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "READONLY_RECORD")
public class ReadonlyRecordEntity_NoCache {
	@Id
	@GeneratedValue
	@Column(name = "READONLY_RECORD_OID")
	private Long id;
	
	@Column(name = "NAME")
	String name;
}
