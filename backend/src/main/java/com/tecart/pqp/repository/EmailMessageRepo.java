package com.tecart.pqp.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecart.pqp.entity.base.EmailMessage;

public interface EmailMessageRepo extends JpaRepository<EmailMessage, Integer>, JpaSpecificationExecutor<EmailMessage>{
	
	@Transactional
	@Query(value = "select * from BASE.EMAIL_MESSAGE where delivery_status =:status", nativeQuery = true)
	List<EmailMessage> getUnSendEmailBased(@Param("status") Integer emailStatus);

	@Transactional
	@Modifying
	@Query(value = "UPDATE BASE.EMAIL_MESSAGE SET delivery_status = :status, MESSAGE_DELIVERY_REMARKS = :deliveryRemarks, UPDATED_TIME = :date WHERE ID = :emailInfoId", nativeQuery = true)
	void setMailStatus(@Param("emailInfoId") Integer emailInfoId, @Param("status") Integer emailStatus,
			@Param("deliveryRemarks") String deliveryRemarks, @Param("date") Date date);

}
