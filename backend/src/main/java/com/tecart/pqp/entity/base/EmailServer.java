package com.tecart.pqp.entity.base;

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
@Table(schema = "BASE", name = "EMAIL_SERVER_INFO")
public class EmailServer extends BaseEntity{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SMTP_HOST")
	private String smtpHost;
	
	@Column(name = "SMTP_PORT_NUMBER")
	private int smtpPortNumber;
	
	@Column(name = "FROM_EMAIL_ID")
	private String fromEmail;
	
	@Column(name = "FROM_EMAIL_NAME")
	private String fromEmailName;
	
	@Column(name = "SMTP_USER_NAME")
	private String smtpUserName;
	
	@Column(name = "SMTP_PASSWORD")
	private String smtpPassword;
	
	@ManyToOne
	@JoinColumn(name = "ORG_ID", referencedColumnName = "ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "organization"})
	private Organization organization;

	/**
	 * 
	 */
	public EmailServer() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the smtpHost
	 */
	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * @param smtpHost the smtpHost to set
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * @return the smtpPortNumbert
	 */
	public int getSmtpPortNumber() {
		return smtpPortNumber;
	}

	/**
	 * @param smtpPortNumbert the smtpPortNumbert to set
	 */
	public void setSmtpPortNumber(int smtpPortNumber) {
		this.smtpPortNumber = smtpPortNumber;
	}

	/**
	 * @return the fromEmail
	 */
	public String getFromEmail() {
		return fromEmail;
	}

	/**
	 * @param fromEmail the fromEmail to set
	 */
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	/**
	 * @return the fromEmailName
	 */
	public String getFromEmailName() {
		return fromEmailName;
	}

	/**
	 * @param fromEmailName the fromEmailName to set
	 */
	public void setFromEmailName(String fromEmailName) {
		this.fromEmailName = fromEmailName;
	}

	/**
	 * @return the smtpUserName
	 */
	public String getSmtpUserName() {
		return smtpUserName;
	}

	/**
	 * @param smtpUserName the smtpUserName to set
	 */
	public void setSmtpUserName(String smtpUserName) {
		this.smtpUserName = smtpUserName;
	}

	/**
	 * @return the smtpPassword
	 */
	public String getSmtpPassword() {
		return smtpPassword;
	}

	/**
	 * @param smtpPassword the smtpPassword to set
	 */
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}
