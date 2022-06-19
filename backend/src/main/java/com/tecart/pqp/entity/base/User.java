package com.tecart.pqp.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tecart.pqp.common.BaseEntity;

@Entity
@Table(schema = "BASE", name = "USER")
public class User extends BaseEntity{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	@Column(name = "USER_TYPE")
	private int userType;
	
	@Column(name = "IS_EMAIL_NOTIFICATION_NEEDED")
	private boolean emailNotificationNeeded;
	
	@Column(name = "IS_VERIFIED")
	private boolean verified;

	@Column(name = "USER_IMG_URL")
	private String userImgUrl;
	
	@ManyToOne
	@JoinColumn(name = "ORG_ID", referencedColumnName = "ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "organization"})
	private Organization organization;

	public User() {
		super();
	}

	public User(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

//	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public boolean isEmailNotificationNeeded() {
		return emailNotificationNeeded;
	}

	public void setEmailNotificationNeeded(boolean emailNotificationNeeded) {
		this.emailNotificationNeeded = emailNotificationNeeded;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getUserImgUrl() {
		return userImgUrl;
	}

	public void setUserImgUrl(String userImgUrl) {
		this.userImgUrl = userImgUrl;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
		
}
