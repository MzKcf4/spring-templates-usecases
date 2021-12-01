package com.springtemplate.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeeEntity.class)
public abstract class EmployeeEntity_ extends com.springtemplate.jpa.entity.AbstractAuditableEntity_ {

	public static volatile SingularAttribute<EmployeeEntity, String> employeeName;
	public static volatile SingularAttribute<EmployeeEntity, EmployeeDetailEntity> employeeDetail;

	public static final String EMPLOYEE_NAME = "employeeName";
	public static final String EMPLOYEE_DETAIL = "employeeDetail";

}

