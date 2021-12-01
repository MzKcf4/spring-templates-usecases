package com.springtemplate.jpa.entity.generator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GENERATOR")
public class GeneratorAutoEntity {
	@Id
	@Column(name = "GENERATOR_OID")
	@GeneratedValue
	private Long id;
}
