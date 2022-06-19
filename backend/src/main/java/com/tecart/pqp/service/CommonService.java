package com.tecart.pqp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tecart.pqp.common.CommonKeyValueParameters;
import com.tecart.pqp.common.MyUserPrincipal;
import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.repository.UserRepository;
import com.tecart.pqp.utils.constants.MasterConstants;

@Service
public class CommonService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private UserRepository userRepository;
	
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
	
	public String getPasswordByUserId(int userId) {
		return userRepository.findPasswordById(userId);
	}
	
	public String getUserNameByLoggedInUser(User loggedInUser) {
		return loggedInUser != null ? loggedInUser.getUserName() : "GuestUser";
	}

	public String replaceKeyByValueParamsFromStr(List<CommonKeyValueParameters> listOfKeyValueParam, String srcStr) {
		logger.info("Entering into method replaceKeyByValueParamsFromStr in CommonService");

		String paramsReplacedStr = srcStr;
		if (srcStr != null && !srcStr.isEmpty() && !listOfKeyValueParam.isEmpty()) {
			for (CommonKeyValueParameters commonkeyValueParmsObj : listOfKeyValueParam) {
				if ((commonkeyValueParmsObj.getKey() != null && !commonkeyValueParmsObj.getKey().isEmpty())
						|| (commonkeyValueParmsObj.getValue() != null
								&& !commonkeyValueParmsObj.getValue().isEmpty())) {
					paramsReplacedStr = paramsReplacedStr.replace(commonkeyValueParmsObj.getKey(),
							commonkeyValueParmsObj.getValue());
				}
			}

		}

		return paramsReplacedStr;

	}
}
