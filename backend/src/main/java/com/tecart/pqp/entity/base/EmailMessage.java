package com.tecart.pqp.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tecart.pqp.common.BaseEntity;

@Entity
@Table(schema = "BASE", name = "EMAIL_MESSAGE")
public class EmailMessage extends BaseEntity{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "FROM_EMAIL")
	private String fromEmail;
	
	@Column(name = "DESTINATION_EMAIL_ID")
	private String emailTo;
	
	@Column(name = "DELIVERY_STATUS")
	private int deliveryStatus;
	
	@Column(name = "EMAIL_SUBJECT")
	private String emailSubject;
	
	@Column(name = "EMAIL_BODY")
	private String emailBody;
	
	@Column(name = "ATTACHMENT_PATH")
	private String attachmentPath;
	
	@Column(name = "DESTINATION_CCEMAIL_ID")
	private String emailCc;
	
	@Column(name = "MESSAGE_DELIVERY_REMARKS")
	private String messageDeliveryRemarks;
	
	@Column(name = "SENDER_ID")
	private int senderId;
	
	@Column(name = "EMAIL_BCC")
	private String emailBcc;

	/**
	 * @param id
	 * @param fromEmail
	 * @param deliveryStatus
	 * @param emailSubject
	 * @param emailBody
	 * @param attachmentPath
	 * @param messageDeliveryRemarks
	 * @param senderId
	 * @param emailTo
	 * @param emailCc
	 * @param emailBcc
	 */
	public EmailMessage(int id, String fromEmail, int deliveryStatus, String emailSubject,
			String emailBody, String attachmentPath, String messageDeliveryRemarks,
			int senderId, String emailTo, String emailCc, String emailBcc) {
		super();
		this.id = id;
		this.fromEmail = fromEmail;
		this.deliveryStatus = deliveryStatus;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		this.attachmentPath = attachmentPath;
		this.messageDeliveryRemarks = messageDeliveryRemarks;
		this.senderId = senderId;
		this.emailTo = emailTo;
		this.emailCc = emailCc;
		this.emailBcc = emailBcc;
	}

	/**
	 * 
	 */
	public EmailMessage() {
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
	 * @return the deliveryStatus
	 */
	public int getDeliveryStatus() {
		return deliveryStatus;
	}

	/**
	 * @param deliveryStatus the deliveryStatus to set
	 */
	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	/**
	 * @return the emailSubject
	 */
	public String getEmailSubject() {
		return emailSubject;
	}

	/**
	 * @param emailSubject the emailSubject to set
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	/**
	 * @return the emailBody
	 */
	public String getEmailBody() {
		return emailBody;
	}

	/**
	 * @param emailBody the emailBody to set
	 */
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	/**
	 * @return the attachmentPath
	 */
	public String getAttachmentPath() {
		return attachmentPath;
	}

	/**
	 * @param attachmentPath the attachmentPath to set
	 */
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	/**
	 * @return the messageDeliveryRemarks
	 */
	public String getMessageDeliveryRemarks() {
		return messageDeliveryRemarks;
	}

	/**
	 * @param messageDeliveryRemarks the messageDeliveryRemarks to set
	 */
	public void setMessageDeliveryRemarks(String messageDeliveryRemarks) {
		this.messageDeliveryRemarks = messageDeliveryRemarks;
	}

	/**
	 * @return the senderId
	 */
	public int getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId the senderId to set
	 */
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the emailTo
	 */
	public String getEmailTo() {
		return emailTo;
	}

	/**
	 * @param emailTo the emailTo to set
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	/**
	 * @return the emailCc
	 */
	public String getEmailCc() {
		return emailCc;
	}

	/**
	 * @param emailCc the emailCc to set
	 */
	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	/**
	 * @return the emailBcc
	 */
	public String getEmailBcc() {
		return emailBcc;
	}

	/**
	 * @param emailBcc the emailBcc to set
	 */
	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}
	
}
