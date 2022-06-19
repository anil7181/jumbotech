package com.tecart.pqp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tecart.pqp.entity.product.Testimonial;

public interface TestimonialRepo extends JpaRepository<Testimonial, Integer>, JpaSpecificationExecutor<Testimonial>{

}
