package com.tecart.pqp.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tecart.pqp.common.BaseEntity;

@Entity
@Table(schema = "BASE", name = "ORGANIZATION")
public class Organization extends BaseEntity{

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
	
	@Column(name = "VISION")
	private String vision;
	
	@Column(name = "MISSION")
	private String mission;
	
	@Column(name = "CONTACT_PERSON_NAME")
	private String contactPersonName;
	
	@Column(name = "CONTACT_PERSON_EMAIL")
	private String contactPersonEmail;
	
	@Column(name = "CONTACT_PERSON_PHONE_NO")
	private String contactPersonPhoneNo;
	
	@Column(name = "CONTACT_PERSON_SALUTION")
	private String contactPersonSalution;
	
	@Column(name = "ADDRESS1")
	private String address1;
	
	@Column(name = "ADDRESS2")
	private String address2;
	
	@Column(name = "ADDRESS3")
	private String address3;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "PINCODE")
	private String pincode;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "OFFICE_TELE_PHONE_NO")
	private String officeTelePhoneNo;
	
	@Column(name = "OFFICE_EMAIL_ID")
	private String officeEmailId;
	
	@Column(name = "OFFICE_MOBILE_NO")
	private String officeMobileNo;
	
	@Column(name = "WORKING_HOURS")
	private String workingHours;
	
	@Column(name = "FACEBOOK_LINK")
	private String facebookLink;
	
	@Column(name = "TWITTER_LINK")
	private String twitterLink;
	
	@Column(name = "LINKEDIN_LINK")
	private String linkeInLink;
	
	@Column(name = "YOUTUBE_LINK")
	private String youtubeLink;
	
	@Column(name = "ABOUT_US")
	private String aboutUs;
	
	@Column(name = "ABOUT_US_IMG")
	private String aboutUsImg;
	
	@Column(name = "LOGO")
	private String logo;

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

	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}

	public String getContactPersonPhoneNo() {
		return contactPersonPhoneNo;
	}

	public void setContactPersonPhoneNo(String contactPersonPhoneNo) {
		this.contactPersonPhoneNo = contactPersonPhoneNo;
	}

	public String getContactPersonSalution() {
		return contactPersonSalution;
	}

	public void setContactPersonSalution(String contactPersonSalution) {
		this.contactPersonSalution = contactPersonSalution;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOfficeTelePhoneNo() {
		return officeTelePhoneNo;
	}

	public void setOfficeTelePhoneNo(String officeTelePhoneNo) {
		this.officeTelePhoneNo = officeTelePhoneNo;
	}

	public String getOfficeEmailId() {
		return officeEmailId;
	}

	public void setOfficeEmailId(String officeEmailId) {
		this.officeEmailId = officeEmailId;
	}

	public String getOfficeMobileNo() {
		return officeMobileNo;
	}

	public void setOfficeMobileNo(String officeMobileNo) {
		this.officeMobileNo = officeMobileNo;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public String getTwitterLink() {
		return twitterLink;
	}

	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}

	public String getLinkeInLink() {
		return linkeInLink;
	}

	public void setLinkeInLink(String linkeInLink) {
		this.linkeInLink = linkeInLink;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public String getAboutUsImg() {
		return aboutUsImg;
	}

	public void setAboutUsImg(String aboutUsImg) {
		this.aboutUsImg = aboutUsImg;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}
