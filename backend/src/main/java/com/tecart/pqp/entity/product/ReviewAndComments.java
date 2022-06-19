package com.tecart.pqp.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tecart.pqp.common.BaseEntity;

@Entity
@Table(schema = "BASE", name = "REVIEW_AND_COMMENTS")
public class ReviewAndComments extends BaseEntity{
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "MESSAGE")
	private String messge;
	
	@Column(name = "RATINGS")
	private Double ratings;
	
	@Column(name = "VERIFIED_CUSTOMER")
	private String verifiedCustomer;

	public int getId() {
		return id;
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

	public String getVerifiedCustomer() {
		return verifiedCustomer;
	}

	public void setVerifiedCustomer(String verifiedCustomer) {
		this.verifiedCustomer = verifiedCustomer;
	}

}
