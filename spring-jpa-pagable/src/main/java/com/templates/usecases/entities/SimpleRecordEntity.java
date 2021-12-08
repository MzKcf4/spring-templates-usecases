package com.templates.usecases.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SIMPLE_RECORD")
public class SimpleRecordEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "SIMPLE_RECORD_OID")
	private Long id;
	
	@Column(name = "NAME")
	String name;
}
