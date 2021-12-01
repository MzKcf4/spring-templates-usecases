package com.springtemplate.jpa.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.springtemplate.jpa.enums.EquipmentType;

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
@Table(name = "EQUIPMENT")
@AttributeOverride(name = "id" , column = @Column(name = "EQUIPMENT_OID"))
public class EquipmentEntity extends AbstractAuditableEntity {
	
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_OID")
	EmployeeEntity employee;
	
	@Column(name = "EQUIPMENT_NAME")
	String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "EQUIPMENT_TYPE")
	EquipmentType type;
}
