package com.tecart.pqp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tecart.pqp.entity.product.ReviewAndComments;

public interface ReviewAndCommentsRepo extends JpaRepository<ReviewAndComments, Integer>, JpaSpecificationExecutor<ReviewAndComments>{

}
