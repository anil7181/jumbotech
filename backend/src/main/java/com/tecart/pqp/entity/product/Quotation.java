package com.tecart.pqp.entity.product;

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
@Table(schema = "PRODUCT", name = "QUOTATION")
public class Quotation extends BaseEntity{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "COMPANY_TYPE")
	private String companyType;
	
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "product"})
	private Product product;
	
	@Column(name = "NO_OF_QUANTITIES")
	private Double NoOfQuantities;
	
	@Column(name = "MESSAGE")
	private String message;
	
	@Column(name = "SELLER_TERM_AND_COND")
	private boolean sellerTermAndCond;
	
	@Column(name = "OTHER_TERM_AND_COND")
	private boolean otherTermAndCond;
	
	@Column(name = "EMAIL_ID")
	private String emailId;

	public Quotation(int id) {
		super();
		this.id = id;
	}

	public Quotation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quotation(String createdBy, Date createdTime, String updatedBy, Date updatedTime, int active) {
		super(createdBy, createdTime, updatedBy, updatedTime, active);
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getNoOfQuantities() {
		return NoOfQuantities;
	}

	public void setNoOfQuantities(Double noOfQuantities) {
		NoOfQuantities = noOfQuantities;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSellerTermAndCond() {
		return sellerTermAndCond;
	}

	public void setSellerTermAndCond(boolean sellerTermAndCond) {
		this.sellerTermAndCond = sellerTermAndCond;
	}

	public boolean isOtherTermAndCond() {
		return otherTermAndCond;
	}

	public void setOtherTermAndCond(boolean otherTermAndCond) {
		this.otherTermAndCond = otherTermAndCond;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
