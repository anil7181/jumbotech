package com.tecart.pqp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecart.pqp.entity.base.EmailServer;
import com.tecart.pqp.repository.EmailServerRepo;
import com.tecart.pqp.utils.exceptions.RecordNotFoundException;

@Service
public class EmailServerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmailServerRepo emailServerRepo;

	public EmailServer findEmailServerDetailsByOrgId(int orgId) {
		logger.info("Inside the findEmailServerDetailsByOrgId method in service");

		EmailServer emailServerFoundByOrgCodeAndEntityCode = emailServerRepo
				.findSendMailingDetails(orgId);

		if (emailServerFoundByOrgCodeAndEntityCode == null) {
			logger.error("Error occurred in the findEmailServerDetailsByOrgId method in service");
			throw new RecordNotFoundException(
					"Record not Found for email Server Id = " + orgId );
		}
		return emailServerFoundByOrgCodeAndEntityCode;
	}

	public EmailServer saveOrUpdateEmailServer(EmailServer emailServerToBeSavedOrUpdated) {
		logger.info("Inside the saveOrUpdateEmailServer method in service");

		return emailServerRepo.saveAndFlush(emailServerToBeSavedOrUpdated);
	}
}
