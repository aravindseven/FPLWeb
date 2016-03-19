package com.fpl.policy;

import com.fpl.errormsg.IErrorMessage;

public enum PolicyErrorMessage implements IErrorMessage {
	
	INVALID_REQUEST_NUMBER("Invalid Request Number!");
	
	private String errorMessage;
	private PolicyErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

}
