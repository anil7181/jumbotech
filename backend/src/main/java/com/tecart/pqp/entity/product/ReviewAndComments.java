package com.tecart.pqp.entity.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tecart.pqp.common.BaseEntity;

@Entity
@Table(schema = "PRODUCT", name = "REVIEW_AND_COMMENTS")
public class ReviewAndComments extends BaseEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "MESSAGE")
	private String messge;

	@Column(name = "RATINGS")
	private Double ratings;

	@Column(name = "VERIFIED_CUSTOMER")
	private boolean verifiedCustomer;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "REVIEWED_FOR")
	private String reviewedFor;

	@Column(name = "REVIEWED_ITEM_ID")
	private int reviewedItemId;

	public ReviewAndComments(int id) {
		super();
		this.id = id;
	}

	public ReviewAndComments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewAndComments(String createdBy, Date createdTime, String updatedBy, Date updatedTime, int active) {
		super(createdBy, createdTime, updatedBy, updatedTime, active);
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessge() {
		return messge;
	}

	public void setMessge(String messge) {
		this.messge = messge;
	}

	public Double getRatings() {
		return ratings;
	}

	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}

	public boolean isVerifiedCustomer() {
		return verifiedCustomer;
	}

	public void setVerifiedCustomer(boolean verifiedCustomer) {
		this.verifiedCustomer = verifiedCustomer;
	}

	public String getReviewedFor() {
		return reviewedFor;
	}

	public void setReviewedFor(String reviewedFor) {
		this.reviewedFor = reviewedFor;
	}

	public int getReviewedItemId() {
		return reviewedItemId;
	}

	public void setReviewedItemId(int reviewedItemId) {
		this.reviewedItemId = reviewedItemId;
	}

}
