package com.fpl.core.profile;

import com.fpl.errormsg.IErrorMessage;

public enum ProfileErrorMessage implements IErrorMessage {
	
	CUSTOMER_NOT_CREATED("Profile yet to created, Please contact administrator !"),
	CUSTOMER_NOT_FOUND("Login information not found with mail id {0} ");
	
	private String errorMessage;
	private ProfileErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}
}
