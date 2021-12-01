package com.springtemplate.jpa.entity.generator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "GENERATOR")
public class GeneratorPoolLoEntity {
	
	@Id
	@Column(name = "GENERATOR_OID")
	@GenericGenerator(name = "pooled-lo-generator" , strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name" , value = "SEQ_GENERATOR"),		// using pooled-lo needs another sequence table.
			@Parameter(name = "initial_value" , value = "1"),
			@Parameter(name = "increment_size" , value = "100"),
			@Parameter(name = "optimizer" , value = "pooled-lo")
	})
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pooled-lo-generator")
	private Long id;
}
