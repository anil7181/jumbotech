package com.tecart.pqp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tecart.pqp.entity.base.OrgParam;

public interface OrgParamRepo extends JpaRepository<OrgParam, Integer>, JpaSpecificationExecutor<OrgParam>{

}
