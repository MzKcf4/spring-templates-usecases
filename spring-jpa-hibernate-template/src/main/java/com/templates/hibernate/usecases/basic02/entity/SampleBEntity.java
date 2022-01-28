package com.templates.hibernate.usecases.basic02.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

// ---- Template regarding "relation" ----- //

@Getter
@Setter
@Entity
@Table(name = "SAMPLE_B")
public class SampleBEntity {
	
	@Id
	@Column(name = "SAMPLE_B_OID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// When you also define association in EmployeeDetail , this becomes 'bidirectional association'.
	//
	// "mappedBy" : Signals Hibernate that the (foreign) key for the relationship is on the other side ( EmployeeDetailsEntity )
	//				So it allows you to still link from the table not containing the constraint to the other table.
	//				Can think as "current entity (EmployeeEntity) is mapped by "employee" field in EmployeeDetailEntity
	// "orphanRemoval" : It affects how this entity responses to disconnecting a relationship (e.g when setting to a new EmployeeDetail , or null)
	//				     When 'true' , the old EmployeeDetail is automatically deleted.
	//					 It's not same as CascadeType.REMOVE , because disconnecting a relationship is NOT a remove operation.
	//					 CascadeType.REMOVE is only effective when you delete THIS entity
	@OneToOne(mappedBy = "sampleB", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private SampleBDetailsEntity sampleBDetail;
	
	@OneToOne(mappedBy = "sampleB", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
	private SampleBDetailsEntity sampleBDetail_noOrphan;
	
	
	// ------------------- Undirectional @ManyToOne / @OneToMany relationship --------------- //
	// Many of THIS entity can have 1 C Entity	
	// A unidirectional association via a foreign key
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAMPLE_C_OID")		// Use this FK column to match the PK in SampleC table
	private SampleCEntity sampleCEntity;
	
	
	// Many EntityD can have THIS entity
	// A unidirectional association via a join table
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAMPLE_B_OID")		// Use this PK column to match the FK in SampleD table
	private Collection<SampleDEntity> sampleDEntitySet;
	
	
	// --------- Bidirectional @ManyToOne / @OneToMany relationship --------------- //
	// bidirectional association via a foreign key with a foreign column name specification
	// Used when you want to propagate update / delete of relation 
	// 'nullable = false' to verify foreign key is really mapped
	
	// Join on another table's  self-reference , THIS entity owns the relation
	// Note that the MANY side MUST own the relation.
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name = "SAMPLE_CB_OID")
	private SampleCbEntity sampleCbEntity;
	
	// A bidirectional association via a foreign key 
	// 'mappedBy' indicates it's not relationship owner 
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "sampleBEntity")
	private List<SampleDbEntity> sampleDbEntitySet;
	
}
