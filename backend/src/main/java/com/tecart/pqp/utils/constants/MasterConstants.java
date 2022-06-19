package com.tecart.pqp.utils.constants;

public class MasterConstants {

	private MasterConstants() {

	}

	// ROOT API PATH
	public static final String ROOT_API_PATH = "/api/v1";

	// AUTHORITY NAME FOR COMMON APIS
	public static final String COMMON_API = "COMMON_API";
	public static final String SUPPER_ADMIN_API = "SUPPER_ADMIN_API";
	public static final String NORMAL_API = "NORMAL_API";
	public static final String ADMIN_API = "ADMIN_API";

	// DEFAULT CONSTANT VALUES
	public static final Integer DEFAULT_ORG_ID = 1;

	// COMMON USED STRINGS
	public static final String SORT_DIRECTION_ASC = "asc";
	public static final String SORT_DIRECTION_DESC = "desc";

	// DEFAULT VALUES
	public static final String DEFAULT_RECORDS_PER_PAGE_VALUE = "100";
	public static final String DEFAULT_PAGE_NO_VALUE = "0";
	public static final String DEFAULT_SORT_KEY = "updatedTime";
	public static final String DEFAULT_SORT_DIR = "desc";
	public static final boolean BOOLEAN_TRUE = true;
	public static final boolean BOOLEAN_FALSE = false;
	public static final String DEFAULT_SORT_KEY_FOR_MENU = "menuId";

	// COMMON USED MAGIC NUMBERS
	public static final String STRING_NULL_VALUE = "null";
	public static final String STRING_EMPTY_VALUE = "";
	public static final int NUMBER_ZERO = 0;
	public static final int NUMBER_ONE = 1;
	public static final Double NUMBER_DOUBLE = 0.0;
	public static final Double NUMBER_DOUBLE_ONE = 1.0;

	public static final int LEAST_COUNT_OF_RECORD_NEEDED_TO_COMPARE = 2;
	public static final int NOT_SPECIFIED_ORG_ID = 0;
	public static final int FIRST_RECORD_OF_LIST = 0;

	public static final int NOT_SPECIFIED_BRANCH_ID = 0;
	public static final int NOT_SPECIFIED_STUDENT_ID = 0;

	public static final int DEFAULT_NOT_SPECIFIED__ID = 0;
	public static final int AS_PARENT_FOR_REGISTRATION = -1;

	// USER TYPE
	public static final String USER_TYPE_SIIX_USER = "SIIX-USER";
	public static final String USER_TYPE_SUPPLIER = "SUPPLIER";

	public static final String COMPANY_NAME = "Asvish Technology Partner (OPC) Pvt.Ltd.";

	// EMAIL TEMPLATES
	public static final String EMAIL_TEMPLATE_NAME_REGISTRATION = "REGISTRATION";
	public static final String EMAIL_TEMPLATE_NAME_PWD_RESET = "PWD RESET";

	public static final String EMAIL_TEMPLATE_TYPE = "EMAIL";

	// OTP PARAMETERS
	public static final String OTP_CAUSE_FOR_PWD_RESET = "PWD_RESET";

	// ACTIVE STATUS
	public static final int DEFAULT_INT_PENDING_STATUS = 0;
	public static final int DEFAULT_INT_ACTIVE__STATUS = 1;
	public static final int DEFAULT_INT_IN_ACTIVE_STATUS = 2;

	public static final String DEFAULT_STR_PENDING_STATUS = "PENDING";
	public static final String DEFAULT_STR_ACTIVE__STATUS = "ACTIVE";
	public static final String DEFAULT_STR_IN_ACTIVE_STATUS = "IN-ACTIVE";

	// BOOLEAN
	public static final boolean DEFAULT_YES = true;
	public static final boolean DEFAULT_NO = false;

	// INT USER TYPE
	public static final int DEFAULT_INT_NORMAL_USER = 1;
	public static final int DEFAULT_INT_SUPER_ADMIN = 2;
	public static final int DEFAULT_INT_ADMIN = 3;

}
