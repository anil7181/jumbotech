package com.tecart.pqp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tecart.pqp.entity.base.EmailMessage;

public interface EmailMessageRepo extends JpaRepository<EmailMessage, Integer>, JpaSpecificationExecutor<EmailMessage>{

}
