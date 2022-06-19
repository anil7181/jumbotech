package com.tecart.pqp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecart.pqp.entity.base.EmailServer;

public interface EmailServerRepo extends JpaRepository<EmailServer, Integer>, JpaSpecificationExecutor<EmailServer>{

	@Query("FROM EmailServer e WHERE e.organization.id = :id")
	EmailServer findSendMailingDetails(@Param("id") int id);
	
	
}
