package com.tecart.pqp.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StringUtils {
	public String convertException(Exception e) {
		StringWriter stack = new StringWriter();
		e.printStackTrace(new PrintWriter(stack));
		return stack.toString();
	}

	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isEmpty(String s) {
		if (s == null || s.length() == 0)
			return true;

		return false;
	}
}
