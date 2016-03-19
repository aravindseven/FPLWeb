package com.fpl.util;

import org.apache.commons.validator.routines.EmailValidator;


public final class StringUtil {

	private StringUtil() {}
	
	public static boolean isEmpty(final String value) {
		return ((value == null) || value.isEmpty());
	}
	
	public static boolean isNotEmpty(final String value) {
		return (value != null && !value.isEmpty());
	}
	
	public static String getNotNullValue(final Object object) {
		String value = "";
		if(object != null) {
			value = object.toString();
		}
		return value;
	}
	 
	public static boolean isValidEmail(final String email, final boolean allowLocal) {
		final EmailValidator emailValidator = EmailValidator.getInstance(allowLocal);
		return emailValidator.isValid(email);
	}
}
