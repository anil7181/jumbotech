package com.tecart.pqp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tecart.pqp.entity.base.ReferencedFrom;

public interface ReferencedFormRepo extends JpaRepository<ReferencedFrom, Integer>, JpaSpecificationExecutor<ReferencedFrom>{

}