package com.springtemplate.jpa.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "EMPLOYEE_DETAIL")
@AttributeOverride(name = "id" , column = @Column(name = "EMPLOYEE_DETAIL_OID"))
public class EmployeeDetailEntity extends AbstractAuditableEntity {

	// @MapsId means : My primary key (or the column in 'value') is also a foreign key of the following entity
	// @JoinColumn means : I'm the owner of relationship ( I owns the foreign key ) , you can find the linked entity by the column name defined.
	
	// "@MapsId + @OneToOne" is the recommended way to handle bidirectional OneToOne relationship , because it prevents eager fetch ( also n+1 problem )
	// More info : 
	// 		For every managed entity, the Persistence Context requires both the entity type and the identifier,
	// 		so the child identifier must be known when loading the parent entity, 
	// 		and the only way to find the associated child primary key is to execute a secondary query.
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_DETAIL_OID")
	@MapsId
	private EmployeeEntity employee;
	
	@JoinColumn(name = "ADDRESS")
	private String address;
}