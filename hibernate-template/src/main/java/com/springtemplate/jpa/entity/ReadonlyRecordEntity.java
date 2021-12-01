package com.springtemplate.jpa.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Cacheable()
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Immutable
@Table(name = "READONLY_RECORD")
public class ReadonlyRecordEntity {
	@Id
	@GeneratedValue
	@Column(name = "READONLY_RECORD_OID")
	private Long id;
	
	@Column(name = "NAME")
	String name;
	
}
