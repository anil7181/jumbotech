package com.tecart.pqp.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tecart.pqp.common.BaseEntity;

@Entity
@Table(schema = "PRODUCT", name = "DOCUMENTS")
public class Documents extends BaseEntity{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "SET_AUTO_NAME")
	private boolean setAutoName;
	
	@Column(name = "DOC_LOCATION")
	private String docLocation;
	
	@Column(name = "DOC_TYPE")
	private String docType;
	
	@Column(name = "RELATED_TO")
	private String relatedTo;

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

	public boolean isSetAutoName() {
		return setAutoName;
	}

	public void setSetAutoName(boolean setAutoName) {
		this.setAutoName = setAutoName;
	}

	public String getDocLocation() {
		return docLocation;
	}

	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getRelatedTo() {
		return relatedTo;
	}

	public void setRelatedTo(String relatedTo) {
		this.relatedTo = relatedTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
