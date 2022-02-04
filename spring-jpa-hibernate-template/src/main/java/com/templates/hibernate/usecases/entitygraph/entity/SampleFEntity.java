package com.templates.hibernate.usecases.entitygraph.entity;

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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
 * Lazily loading associations fires additional SQL call to DB , usually you want to avoid this
 * But changing to EAGER fetch on entity level is not a good option
 * We can use EntityGraph as a fetch hint , all nodes defined in entity graphs are always eager fetched irrespective of their definition on entity level
 * */

@NamedEntityGraph(
		name = "basic-entity-graph",
		attributeNodes = {
				@NamedAttributeNode("sampleGEntity"),
				@NamedAttributeNode("stringField"),
				@NamedAttributeNode("sampleHfEntityList")
		}
	)
@NamedEntityGraph(
		name = "basic-entity-graph-with-subgraph",
		attributeNodes = {
				@NamedAttributeNode("stringField"),
				@NamedAttributeNode("sampleHfEntityList"),
				@NamedAttributeNode(value = "sampleGEntity" , subgraph = "sampleGEntity-subgraph"),
		},
		subgraphs = {
				@NamedSubgraph (
						name = "sampleGEntity-subgraph",
						attributeNodes = {
								@NamedAttributeNode("sampleGgEntity")
						}
				)
		}
	)
@Getter
@Setter
@Entity
@Table(name = "SAMPLE_F")
public class SampleFEntity {
	@Id
	@Column(name = "SAMPLE_F_OID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAMPLE_G_OID")		// Use this FK column to match the PK in SampleG table
	private SampleGEntity sampleGEntity;
	
	@Column(name = "STRING_FIELD")
	private String stringField;
	
	// A bidirectional association via a foreign key 
	// 'mappedBy' indicates it's not relationship owner 
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "sampleFEntity")
	private List<SampleHfEntity> sampleHfEntityList;
}
