package com.springtemplate.jpa.entity;

import com.springtemplate.jpa.enums.EquipmentType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EquipmentEntity.class)
public abstract class EquipmentEntity_ extends com.springtemplate.jpa.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<EquipmentEntity, String> name;
	public static volatile SingularAttribute<EquipmentEntity, EmployeeEntity> employee;
	public static volatile SingularAttribute<EquipmentEntity, EquipmentType> type;

	public static final String NAME = "name";
	public static final String EMPLOYEE = "employee";
	public static final String TYPE = "type";

}

