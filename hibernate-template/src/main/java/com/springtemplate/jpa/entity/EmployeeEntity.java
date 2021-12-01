package com.springtemplate.jpa.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Table(name = "EMPLOYEE")
@AttributeOverride(name = "id", column = @Column(name = "EMPLOYEE_OID"))
public class EmployeeEntity extends AbstractAuditableEntity {

	@Id
	@Column(name = "EMPLOYEE_OID")
	@GenericGenerator(name = "pooled-lo-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name" , value = "SEQ_EMPLOYEE"),
			@Parameter(name = "initial_value" , value = "1"),
			@Parameter(name = "increment_size" , value = "100"),
			@Parameter(name = "optimizer" , value = "pooled-lo")
	})
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "pooled-lo-generator")
	private Long id;

	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;

	// When you also define association in EmployeeDetail , this becomes 'bidirectional association'.
	//
	// "mappedBy" : Signals Hibernate that the (foreign) key for the relationship is on the other side ( EmployeeDetailsEntity )
	//				So it allows you to still link from the table not containing the constraint to the other table.
	//				Can think as "current entity (EmployeeEntity) is mapped by "employee" field in EmployeeDetailEntity
	// "orphanRemoval" : It affects how this entity responses to disconnecting a relationship (e.g when setting to a new EmployeeDetail , or null)
	//				     When 'true' , the old EmployeeDetail is automatically deleted.
	//					 It's not same as CascadeType.REMOVE , because disconnecting a relationship is NOT a remove operation.
	//					 CascadeType.REMOVE is only effective when you delete THIS entity
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, optional = false)
	private EmployeeDetailEntity employeeDetail;
	
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, optional = false)
	private EmployeeDetailEntity employeeDetail_noOrphan;
}