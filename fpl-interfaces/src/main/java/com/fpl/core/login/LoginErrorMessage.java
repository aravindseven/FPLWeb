package com.fpl.core.login;

import com.fpl.errormsg.IErrorMessage;

public enum LoginErrorMessage implements IErrorMessage {
	
	INVALID_CREDENTIAL("Invalid Credential !"),
	NOT_ACTIVATED("Account is not active yet !"),
	LOCKED("Account has been locked, please contact administrator !"),
	INVALID_EMAIL("Please enter valid {0} id !"),
	USERNAME_PASSWORD_DIFFE("Password should not be same as Primary email !"),
	PASSWORD_SMALL("Password should be minimum 8 characters !"),
	INVALID_RECOVERY_DATA("Oops! Incorrect Input."),
	USER_ALREADY_EXIST("User name already exists"),
	OTP_EXPIRED("This {0} has expired or invalid. Please try again"),
	PASSWORD_SAME("Last 3 passwords cannot be re used."),
	INVALID_PASSWORD("Passwords must be Alphanumeric characters with 1 upper case and 1 special symbols"),
	TWO_PASSWORD_DIFFER("Password and Confirm password does not match !"), 
	AlREADY_LOGGEDIN("Your Account is already logged in some other session");
	
	private String errorMessage;
	private LoginErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}
}
