package com.tecart.pqp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tecart.pqp.common.MyUserPrincipal;
import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.utils.constants.MasterConstants;

@Service
public class CommonService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean isGivenStringNtEmptyAndNtNull(String givenStrforValidation) {
		logger.info("Inside isGivenStringNtEmptyAndNtNull method in service");

		return givenStrforValidation != null && !givenStrforValidation.equals("") ? MasterConstants.DEFAULT_YES
				: MasterConstants.DEFAULT_NO;

	}
	
	public User getCurrentUserDetails() {
		User user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
		    user = myUserPrincipal.getUser();
		}
		return user;
	}
}
