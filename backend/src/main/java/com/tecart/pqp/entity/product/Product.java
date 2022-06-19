package com.tecart.pqp.entity.product;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tecart.pqp.common.BaseEntity;

@Entity
@Table(schema = "PRODUCT", name = "PRODUCT")
public class Product extends BaseEntity{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "MULTI_FUNCTIONAL_HEADING")
	private String multiFunctionalHeading;
	
	@Column(name = "MULTI_FUNCTIONAL_DESCRIPTION")
	private String multiFunctionalDescription;
	
	@Transient
	private List<Documents> documentList;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "physicalStatus"})
	private Category category;
	
	@Column(name = "MODEL")
	private String model;
	
	@Column(name = "MANUFACTURED_BY")
	private String manufacturedBy;
	
	@Column(name = "MANUFACTURED_INFO")
	private String manufacturedInfo;
	
	@Column(name = "INGREDIENTS")
	private String ingredients;
	
	@Column(name = "SAFTY_MEASURS")
	private String saftyMeasures;
	
	@Column(name = "WARRENTY_INFO")
	private String warrentyInfo;
	
	@Column(name = "GUARANTY_INFO")
	private String guarantyInfo;
	
	@Transient
	private List<ReviewAndComments> reviewAndCommentList;

	public Product(int id) {
		super();
		this.id = id;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMultiFunctionalHeading() {
		return multiFunctionalHeading;
	}

	public void setMultiFunctionalHeading(String multiFunctionalHeading) {
		this.multiFunctionalHeading = multiFunctionalHeading;
	}

	public String getMultiFunctionalDescription() {
		return multiFunctionalDescription;
	}

	public void setMultiFunctionalDescription(String multiFunctionalDescription) {
		this.multiFunctionalDescription = multiFunctionalDescription;
	}

	public List<Documents> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Documents> documentList) {
		this.documentList = documentList;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturedBy() {
		return manufacturedBy;
	}

	public void setManufacturedBy(String manufacturedBy) {
		this.manufacturedBy = manufacturedBy;
	}

	public String getManufacturedInfo() {
		return manufacturedInfo;
	}

	public void setManufacturedInfo(String manufacturedInfo) {
		this.manufacturedInfo = manufacturedInfo;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getSaftyMeasures() {
		return saftyMeasures;
	}

	public void setSaftyMeasures(String saftyMeasures) {
		this.saftyMeasures = saftyMeasures;
	}

	public String getWarrentyInfo() {
		return warrentyInfo;
	}

	public void setWarrentyInfo(String warrentyInfo) {
		this.warrentyInfo = warrentyInfo;
	}

	public String getGuarantyInfo() {
		return guarantyInfo;
	}

	public void setGuarantyInfo(String guarantyInfo) {
		this.guarantyInfo = guarantyInfo;
	}

	public List<ReviewAndComments> getReviewAndCommentList() {
		return reviewAndCommentList;
	}

	public void setReviewAndCommentList(List<ReviewAndComments> reviewAndCommentList) {
		this.reviewAndCommentList = reviewAndCommentList;
	}
	
}
