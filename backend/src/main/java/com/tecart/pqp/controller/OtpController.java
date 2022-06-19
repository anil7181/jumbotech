package com.tecart.pqp.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecart.pqp.entity.base.Otp;
import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.service.OtpService;
import com.tecart.pqp.service.UserService;
import com.tecart.pqp.utils.constants.MasterConstants;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = MasterConstants.ROOT_API_PATH)
public class OtpController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/validate/{emailId}")
	public ResponseEntity<?> validateEmailIdAndgenerateOtpForPasswordReset(@PathVariable("emailId") String emailId) throws Exception {
		logger.info("Inside validateEmailIdAndgenerateOtpForPasswordReset method in controller");
		
		boolean isLoginIdExists = false;
		String validationStatusMessage = "";
		
		otpService.generateOtpForPasswordReset(emailId);
		isLoginIdExists = true;
		validationStatusMessage = "Generated Otp is sent to registered Email Id";
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sucess", isLoginIdExists);
		result.put("message", validationStatusMessage);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@PostMapping(path = { "/users/verifyOtp" })
	public ResponseEntity<?> verifyOtp(@RequestBody Otp otp) throws Exception {
		logger.info("Inside verifyOtp method in controller");
		
		boolean isOtpVerified = false;
		String validationStatusMessage = "";
		User requestedUser = null;
		
		if(otp.getEmailId() != null && !otp.getEmailId().isEmpty()) {
			requestedUser = userService.findUserByEmailId(otp.getEmailId());
		if(requestedUser != null) {
			if(otpService.verifyOtp(otp, requestedUser)) {
				isOtpVerified = true;
				validationStatusMessage = "Otp validation successfull";
			} else {
				validationStatusMessage = "Invalid Otp";
			}
		} else {
			validationStatusMessage = "No user found for Email Id = " + otp.getEmailId();
			logger.error(validationStatusMessage);
			throw new Exception(validationStatusMessage);
		}
		} else {
			validationStatusMessage = "No user found for Email Id = " + otp.getEmailId();
			logger.error(validationStatusMessage);
			throw new Exception(validationStatusMessage);
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sucess", isOtpVerified);
		result.put("message", validationStatusMessage);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);

	}

}