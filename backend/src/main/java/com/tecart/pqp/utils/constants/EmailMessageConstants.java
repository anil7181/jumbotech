package com.tecart.pqp.utils.constants;

public class EmailMessageConstants {
	
	private EmailMessageConstants() {

	}

	// PARAMETERS
	public static final String EMAIL_MSG_PARAMETER_FOR_TOTAL_REC_COUNT = "${Total_Rec_Count}";
	public static final String EMAIL_MSG_PARAMETER_FOR_PROCESS_NAME = "${Process_Name}";
	public static final String EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_NAME = "${Contact_Name}";
	public static final String EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_SALUTATION = "${Contact_Salutation}";
	public static final String EMAIL_MSG_PARAMETER_FOR_SENDR_NAME = "${Sender_Name}";
	public static final String EMAIL_MSG_PARAMETER_FOR_SENDR_DESIGNATION = "${Sender_Desiganation}";
	public static final String EMAIL_MSG_PARAMETER_FOR_CURRENT_DATE = "${Current_Date}";
	public static final String EMAIL_MSG_PARAMETER_FOR_ERROR_MSG = "${Error_Message}";
	public static final String EMAIL_MSG_PARAMETER_FOR_MSG_INTERFACE_DIRECTION = "${Message_Direction}";
	public static final String EMAIL_MSG_PARAMETER_FOR_MSG_OR_INTERFACE_TRANSMISSION = "${Message_Or_Interface_Transmission}";
	public static final String EMAIL_MSG_PARAMETER_FOR_GENERATED_OTP = "${Generated_Otp}";
	public static final String EMAIL_MSG_PARAMETER_FOR_VALID_TIME_IN_MIN_FOR_OTP = "${Valid_Time}";
	public static final String EMAIL_MSG_PARAMETER_FOR_MESSAGE_DIRECTION = "${Message_Direction}";
	public static final String EMAIL_MSG_PARAMETER_FOR_MESSAGE_STATUS_TYPE = "${Message_Status_Type}";
	public static final String EMAIL_MSG_PARAMETER_FOR_ENTITY_NAME = "${Entity_Name}";

	// DEFAULT CONSTS
	public static final String DEFAULT_EMAIL_MSG_SENDER_NAME = "SIIX Global EDI Team";
	public static final String DEFAULT_EMAIL_MSG_CONTACT_NAME = "Team";

	// DEFAULT EMAIL BODY
	public static final String EMAIL_MSG_BODY_FOR_APPROVAL_INTIMATION = "Dear Team,<br/><br/> "
			+ EMAIL_MSG_PARAMETER_FOR_TOTAL_REC_COUNT + " " + EMAIL_MSG_PARAMETER_FOR_MESSAGE_STATUS_TYPE + " "
			+ EMAIL_MSG_PARAMETER_FOR_MESSAGE_DIRECTION + " <strong>" + EMAIL_MSG_PARAMETER_FOR_PROCESS_NAME
			+ "</strong> record(s) awaiting for approval" + "<br/><br/>Thanks,<br/>"
			+ EMAIL_MSG_PARAMETER_FOR_ENTITY_NAME;

	public static final String EMAIL_MSG_BODY_FOR_MSG_TRANSMISSION_ERROR_INTIMATION = "Dear "
			+ EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_SALUTATION + "" + EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_NAME
			+ ",<br/><br/> " + EMAIL_MSG_PARAMETER_FOR_PROCESS_NAME + " "
			+ EMAIL_MSG_PARAMETER_FOR_MSG_INTERFACE_DIRECTION + " "
			+ EMAIL_MSG_PARAMETER_FOR_MSG_OR_INTERFACE_TRANSMISSION + " failed on "
			+ EMAIL_MSG_PARAMETER_FOR_CURRENT_DATE + " due to - " + EMAIL_MSG_PARAMETER_FOR_ERROR_MSG
			+ "<br/><br/>Thanks,<br/>" + EMAIL_MSG_PARAMETER_FOR_SENDR_NAME;

	public static final String EMAIL_MSG_BODY_FOR_PASSWORD_RESET_OTP = "Dear "
			+ EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_NAME + ",<br/><br/> "
			+ "Your OTP for Resetting Password is <strong>" + EMAIL_MSG_PARAMETER_FOR_GENERATED_OTP + " </strong>"
			+ "and its valid for " + EMAIL_MSG_PARAMETER_FOR_VALID_TIME_IN_MIN_FOR_OTP + " minutes.<br/><br/>"
			+ "<strong>Please Note:</strong><br/>"
			+ "<p style='color:red;'>Please do not share/forward this email or Otp with any one for security purpose.<br/>"
			+ "This is an automated email notification. Responses to this email are not monitored.<p/>"
			+ "<br/><br/>Thanks,<br/>" + EMAIL_MSG_PARAMETER_FOR_SENDR_NAME;

	public static final String EMAIL_MSG_BODY_FOR_ACCOUNT_LOCK = "Dear " + EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_NAME
			+ ",<br/><br/>"
			+ "Your account has been locked due to max attempt of login with wrong credentials. You may unclock your account by resetting or clicking on forget password button from Login Page"
			+ "<br/><br/>Thanks,<br/>" + EMAIL_MSG_PARAMETER_FOR_SENDR_NAME;

	public static final String EMAIL_MSG_BODY_FOR_CANCELLATION = "Dear "
			+ EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_SALUTATION + "" + EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_NAME
			+ ",<br/><br/> " + "Please find the attached Purchase Order Cancellation Report" + "<br/><br/>Thanks,<br/>"
			+ EMAIL_MSG_PARAMETER_FOR_SENDR_NAME;

	public static final String EMAIL_MSG_BODY_FOR_CANCELLATION_WITH_PLAIN_TEXT = "Dear "
			+ EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_SALUTATION + "" + EMAIL_MSG_PARAMETER_FOR_CONTACT_PERSON_NAME
			+ ",\r\n\r\n" + "Please find the attached Purchase Order Cancellation Report.\r\n" + "Thanks,\r\n"
			+ EMAIL_MSG_PARAMETER_FOR_SENDR_NAME;

}
