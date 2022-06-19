package com.tecart.pqp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tecart.pqp.entity.product.Quotation;

public interface QuotationRepo extends JpaRepository<Quotation, Integer>, JpaSpecificationExecutor<Quotation>{

}
