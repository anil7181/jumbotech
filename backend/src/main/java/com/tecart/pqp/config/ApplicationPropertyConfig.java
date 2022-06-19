package com.tecart.pqp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationPropertyConfig {

	@Value("${root.path}")
	private String rootPath;

	@Value("${otp.length}")
	private String otpLength;

	@Value("${otp.validtime.in.minutes}")
	private String otpValidTimeInMin;
	
	@Value("${email.sender.name}")
	private String emailSenderName;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public String getMaxLengthOfOtp() {
		return otpLength;
	}

	public String getMaxValidTimeInMinOfOtp() {
		return otpValidTimeInMin;
	}

	public String getBasicRootPath() throws Exception {
		logger.info("Entering into method getBasicRootPath in CommonService");

		String rootPathToBeReturned;
		if (rootPath != null && !rootPath.isEmpty()) {
			rootPathToBeReturned = rootPath;
		} else {
			logger.error("Root Path is empty or not found at getBasicRootPath method in CommonService");
			throw new Exception("Root Path is empty or not found at getBasicRootPath method in CommonService");
		}

		return rootPathToBeReturned;
	}
	
	public String getEmailSenderName() {
		return emailSenderName;
	}
}
