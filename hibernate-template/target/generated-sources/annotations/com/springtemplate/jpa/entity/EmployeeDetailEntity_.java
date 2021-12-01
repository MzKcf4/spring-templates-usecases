package com.springtemplate.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeeDetailEntity.class)
public abstract class EmployeeDetailEntity_ extends com.springtemplate.jpa.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<EmployeeDetailEntity, String> address;
	public static volatile SingularAttribute<EmployeeDetailEntity, EmployeeEntity> employee;

	public static final String ADDRESS = "address";
	public static final String EMPLOYEE = "employee";

}

