package com.tecart.pqp.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public class BaseEntity {
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@CreationTimestamp
	private Date createdTime;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@UpdateTimestamp
	private Date updatedTime;
	
	@Column(name = "ACTIVE")
    private int active;
	
	@Transient
	private boolean success;
	
	@Transient
	private String messageKey;
	
//	@Transient
//	private List<HeaderChipInfo> headerChipList;

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	public BaseEntity() {
	}

	public BaseEntity(String createdBy, Date createdTime, String updatedBy, Date updatedTime, int active) {
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.active = active;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the messageKey
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * @param messageKey the messageKey to set
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}


}
