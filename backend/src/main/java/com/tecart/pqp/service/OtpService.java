package com.tecart.pqp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecart.pqp.common.CommonKeyValueParameters;
import com.tecart.pqp.config.ApplicationPropertyConfig;
import com.tecart.pqp.entity.base.EmailMessage;
import com.tecart.pqp.entity.base.EmailServer;
import com.tecart.pqp.entity.base.Otp;
import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.repository.OtpRepository;
import com.tecart.pqp.utils.constants.EmailMessageConstants;
import com.tecart.pqp.utils.constants.MasterConstants;

@Service
public class OtpService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailServerService emailServerService;

	@Autowired
	private EmailService emailMessageService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ApplicationPropertyConfig applicationPropertyConfig;

	Random random = new Random();

	public void generateOtpForPasswordReset(String emailId) throws Exception {
		logger.info("Inside generateOtpForPasswordReset method in service");

		int otpLength = 0;
		String otpLengthInString = null;
		String numberThatToBeUsedWhileGeneratingOtp = "0123456789";
		String generatedOtp = "";
		User serviceRequestedUser = userService.findUserByEmailId(emailId);
		String errorMessage = "Otp length insertion scripts seems to be missing";

		if (serviceRequestedUser != null) {
			otpLengthInString = applicationPropertyConfig.getMaxLengthOfOtp();

			if (otpLengthInString == null) {
				logger.error(errorMessage);
				throw new Exception(errorMessage);
			} else {

				otpLength = Integer.valueOf(otpLengthInString);

				if (otpLength <= 0) {
					logger.error(errorMessage);
					throw new Exception(errorMessage);
				} else {
					Otp otp = new Otp();

					for (int i = 0; i < otpLength; i++) {
						generatedOtp = generatedOtp + random.nextInt(numberThatToBeUsedWhileGeneratingOtp.length());
					}

					otp.setId(MasterConstants.DEFAULT_NOT_SPECIFIED__ID);
					otp.setCauseOfGeneration(MasterConstants.OTP_CAUSE_FOR_PWD_RESET);
					otp.setValue(generatedOtp);
					otp.setUser(serviceRequestedUser);
					otp.setActive(MasterConstants.DEFAULT_ACTIVE_FALG);
					otp.setCreatedBy(serviceRequestedUser.getUserName());
					otp.setUpdatedBy(serviceRequestedUser.getUserName());

					otpRepository.saveAndFlush(otp);

					List<CommonKeyValueParameters> listOfCommonKeyValueParams = new ArrayList<>();
					listOfCommonKeyValueParams.add(new CommonKeyValueParameters(
							EmailMessageConstants.EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_NAME,
							serviceRequestedUser.getUserName()));
					listOfCommonKeyValueParams.add(new CommonKeyValueParameters(
							EmailMessageConstants.EMAIL_MSG_PARAMETER_FOR_GENERATED_OTP, otp.getValue()));
					listOfCommonKeyValueParams.add(new CommonKeyValueParameters(
							EmailMessageConstants.EMAIL_MSG_PARAMETER_FOR_VALID_TIME_IN_MIN_FOR_OTP,
							applicationPropertyConfig.getMaxValidTimeInMinOfOtp()));
					listOfCommonKeyValueParams
							.add(new CommonKeyValueParameters(EmailMessageConstants.EMAIL_MSG_PARAMETER_FOR_SENDR_NAME,
									EmailMessageConstants.DEFAULT_EMAIL_MSG_SENDER_NAME));

					String emailBody = commonService.replaceKeyByValueParamsFromStr(listOfCommonKeyValueParams,
							EmailMessageConstants.EMAIL_MSG_BODY_FOR_PASSWORD_RESET_OTP);
					String emailSubject = "OTP for Password Reset";

					EmailServer emailServer = emailServerService.findEmailServerDetailsByOrgId(MasterConstants.DEFAULT_ORG_ID);
					
					EmailMessage emailMessageToBeSent = new EmailMessage(MasterConstants.NEW_ID_TO_BE_SAVED,
							emailServer.getFromEmailName(),
							MasterConstants.EMAIL_DELIVERY_STATUS_IN_PROGRESS, emailSubject, emailBody, null, null,
							serviceRequestedUser.getId(), serviceRequestedUser.getEmailId(),
							serviceRequestedUser.getOrganization().getOfficeEmailId(), null);

					emailMessageToBeSent.setActive(MasterConstants.DEFAULT_ACTIVE_FALG);
					emailMessageToBeSent.setCreatedBy(serviceRequestedUser.getUserName());
					emailMessageToBeSent.setUpdatedBy(serviceRequestedUser.getUserName());

					emailMessageToBeSent = emailMessageService.saveEmailMessage(emailMessageToBeSent);
					emailMessageService.triggerUnsendEmail(emailServer, emailMessageToBeSent);
				}
			}
		} else {
			throw new Exception("No user found for Email Id = " + emailId);
		}

	}

	public boolean verifyOtp(Otp otp, User requestedUser) throws Exception {
		logger.info("Inside verifyOtp method in service");

		Otp originalOtp = null;
		String errorMessage = "";
		boolean isOtpValid = false;
		boolean isAnyExceptionOccured = false;
		String maxValidTimeOfOtp = null;

		maxValidTimeOfOtp = applicationPropertyConfig.getMaxValidTimeInMinOfOtp();
		System.out.println(new Date());
		originalOtp = otpRepository.findOtpByCauseOfGenerationAndUserAndValue(otp.getCauseOfGeneration(),
				requestedUser.getId(), Integer.parseInt(maxValidTimeOfOtp.trim()), new Date());
		if ((originalOtp != null) && (otp.getValue().equals(originalOtp.getValue()))) {
			isOtpValid = true;
			originalOtp.setActive(MasterConstants.DEFAULT_IN_ACTIVE_FLAG);
			originalOtp.setUpdatedBy(requestedUser.getUserName());
			originalOtp.setUpdatedTime(new Date());
			otpRepository.saveAndFlush(originalOtp);
		} else {
			errorMessage = "Invalid Otp...!";
			isAnyExceptionOccured = true;
		}

		if (isAnyExceptionOccured) {
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}

		return isOtpValid;
	}

	public Otp findOtpByCauseOfGenerationAndUserAndInActive(String causeOfGeneration, int requestedUserId,
			int maxValidTimeOfOtp, Date currentDateWithTimeStamp) {
		logger.info("Inside findOtpByCauseOfGenerationAndUserAndInActive method in service");

		return otpRepository.findOtpByCauseOfGenerationAndUserAndInActive(causeOfGeneration, requestedUserId,
				maxValidTimeOfOtp, currentDateWithTimeStamp);
	}

}
