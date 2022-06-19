package com.tecart.pqp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tecart.pqp.entity.base.EmailServer;

public interface EmailServerRepo extends JpaRepository<EmailServer, Integer>, JpaSpecificationExecutor<EmailServer>{

}
