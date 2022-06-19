package com.tecart.pqp.utils;

import com.tecart.pqp.utils.constants.MasterConstants;

/**
 * This class contains several methods for working with Date and Timestamp
 * values.
 * 
 * @author Modinsha
 */
public class LangUtils {
	// Assign String method to validate the String is not null and return the
	// value.
	public String assignString(String paramString) {
		if (paramString != null && !paramString.equals(MasterConstants.STRING_NULL_VALUE)) {
			if (paramString.trim().length() == 0) {
				paramString = MasterConstants.STRING_EMPTY_VALUE;
			}
		} else {
			paramString = MasterConstants.STRING_EMPTY_VALUE;
		}
		return paramString;
	}

	// Method to validate the number values and return the number.
	public int assignNumber(String paramString) {
		if (paramString != null && !paramString.equals(MasterConstants.STRING_NULL_VALUE)) {
			if (paramString.trim().length() > 0) {
				try {
					return Integer.parseInt(paramString.trim());
				} catch (Exception e) {
					return MasterConstants.NUMBER_ZERO;
				}
			} else {
				return MasterConstants.NUMBER_ZERO;
			}
		} else {
			return MasterConstants.NUMBER_ZERO;
		}
	}

	// To check the String is null or empty to return the Boolean result
	public Boolean validateIsNotEmpty(String paramString) {
		if (paramString != null && !paramString.equals(MasterConstants.STRING_EMPTY_VALUE)) {
			if (paramString.trim().length() > 0) {
				return MasterConstants.BOOLEAN_TRUE;
			} else {
				return MasterConstants.BOOLEAN_FALSE;
			}
		} else {
			return MasterConstants.BOOLEAN_FALSE;
		}
	}

	// Method to validate the number values and return the number.
	public Double assignDoubleValue(String paramString) {
		if (paramString != null && !paramString.equals(MasterConstants.STRING_EMPTY_VALUE)) {
			if (paramString.trim().length() > 0) {
				try {
					return Double.parseDouble(paramString.trim());
				} catch (Exception e) {
					return MasterConstants.NUMBER_DOUBLE;
				}
			} else {
				return MasterConstants.NUMBER_DOUBLE;
			}
		} else {
			return MasterConstants.NUMBER_DOUBLE;
		}
	}
}