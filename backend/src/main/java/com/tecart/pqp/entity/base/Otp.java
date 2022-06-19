package com.tecart.pqp.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tecart.pqp.common.BaseEntity;

@Entity
@Table(schema = "BASE", name = "OTP")
public class Otp extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OTP_ID")
	private int id;
	
	@Column(name = "CAUSE_OF_GENERATION")
	private String causeOfGeneration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
	private User user;
	
	@Column(name = "VALUE", nullable = false, length = 100)
	private String value;
	
	@Transient
	private String emailId;
	
	public Otp() {
		super();
	}

	public Otp(String causeOfGeneration, String value) {
		super();
		this.causeOfGeneration = causeOfGeneration;
		this.value = value;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the causeOfGeneration
	 */
	public String getCauseOfGeneration() {
		return causeOfGeneration;
	}

	/**
	 * @param causeOfGeneration the causeOfGeneration to set
	 */
	public void setCauseOfGeneration(String causeOfGeneration) {
		this.causeOfGeneration = causeOfGeneration;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}

