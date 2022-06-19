package com.tecart.pqp.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecart.pqp.entity.base.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer>, JpaSpecificationExecutor<Otp>{

	@Query(value = "SELECT * FROM BASE.OTP WHERE CAUSE_OF_GENERATION = :causeOfGeneration AND USER_ID = :requestedUserId AND ACTIVE = TRUE "
			+ " AND (SELECT (DATE_PART('day', :currentDateWithTimeStamp \\:\\:timestamp - UPDATED_TIME \\:\\:timestamp) * 24 + "
			+ "               DATE_PART('hour', :currentDateWithTimeStamp \\:\\:timestamp - UPDATED_TIME \\:\\:timestamp)) * 60 + "
			+ "               DATE_PART('minute', :currentDateWithTimeStamp \\:\\:timestamp - UPDATED_TIME \\:\\:timestamp)) <= :maxValidTimeOfOtp ORDER BY OTP_ID DESC LIMIT 1", nativeQuery = true)
	Otp findOtpByCauseOfGenerationAndUserAndValue(@Param("causeOfGeneration") String causeOfGeneration,
			@Param("requestedUserId") int requestedUserId,
			@Param("maxValidTimeOfOtp") int maxValidTimeOfOtp, @Param("currentDateWithTimeStamp") Date currentDateWithTimeStamp);

	@Query(value = "SELECT * FROM BASE.OTP WHERE CAUSE_OF_GENERATION = :causeOfGeneration AND USER_ID = :requestedUserId "
			+ " AND (SELECT (DATE_PART('day', :currentDateWithTimeStamp \\:\\:timestamp - UPDATED_TIME \\:\\:timestamp) * 24 + "
			+ "               DATE_PART('hour', :currentDateWithTimeStamp \\:\\:timestamp - UPDATED_TIME \\:\\:timestamp)) * 60 + "
			+ "               DATE_PART('minute', :currentDateWithTimeStamp \\:\\:timestamp - UPDATED_TIME \\:\\:timestamp)) <= :maxValidTimeOfOtp ORDER BY OTP_ID DESC LIMIT 1", nativeQuery = true)
	Otp findOtpByCauseOfGenerationAndUserAndInActive(@Param("causeOfGeneration") String causeOfGeneration,
			@Param("requestedUserId") int requestedUserId, @Param("maxValidTimeOfOtp") int maxValidTimeOfOtp, @Param("currentDateWithTimeStamp") Date currentDateWithTimeStamp);

	@Transactional
	@Modifying
	@Query(value = "UPDATE BASE.OTP SET ACTIVE = FALSE, UPDATED_TIME = NOW(), UPDATED_BY = :lastUpdatedBy WHERE OTP_ID = :otpId", nativeQuery = true)
	void updateActiveFieldAfterSuccessfullOtpValidation(@Param("otpId") int otpId,
			@Param("lastUpdatedBy") String lastUpdatedBy);
	
}
