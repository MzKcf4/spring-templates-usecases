package com.templates.hibernate.usecases.basic01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.templates.hibernate.usecases.basic01.enums.SampleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SAMPLE_A")
public class SampleAEntity {

	@Id
	@Column(name = "SAMPLE_A_OID")
	@GenericGenerator(name = "pooled-lo-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name" , value = "SEQ_EMPLOYEE"),
			@Parameter(name = "initial_value" , value = "1"),
			@Parameter(name = "increment_size" , value = "100"),
			@Parameter(name = "optimizer" , value = "pooled-lo")
	})
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "pooled-lo-generator")
	private Long id;
	
	@Column(name = "STRING_FIELD" , length = 256)
	private String stringField;
	
	@Column(name = "YES_NO_FIELD")
	@Type(type = "yes_no")
	private Boolean yesNoField;
	
	@Column(name = "TRUE_FALSE_FIELD")
	private Boolean trueFalseField;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ENUM_FIELD")
	private SampleEnum enumField;
}
