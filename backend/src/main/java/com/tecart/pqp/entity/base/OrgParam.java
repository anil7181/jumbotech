package com.tecart.pqp.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tecart.pqp.common.BaseEntity;

@Entity
@Table(schema = "BASE", name = "ORG_PARAM")
public class OrgParam  extends BaseEntity{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "VALUE")
	private String value;
	
	@Column(name = "DATA_TYPE")
	private String dataType;
	
	@ManyToOne
	@JoinColumn(name = "ORG_ID", referencedColumnName = "ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "organization"})
	private Organization organization;

	public OrgParam() {
		super();
	}

	public OrgParam(int id, Organization organization) {
		super();
		this.id = id;
		this.organization = organization;
	}

	public OrgParam(String name, Organization organization) {
		super();
		this.name = name;
		this.organization = organization;
	}

	public OrgParam(String name, String value, Organization organization) {
		super();
		this.name = name;
		this.value = value;
		this.organization = organization;
	}

	public OrgParam(int id, String name, String value, String dataType, Organization organization, String createdBy, Date createdTime, String updatedBy, Date updatedTime, int active) {
		super(createdBy, createdTime, updatedBy, updatedTime, active);
		this.id = id;
		this.name = name;
		this.value = value;
		this.dataType = dataType;
		this.organization = organization;
	}

	public OrgParam(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
