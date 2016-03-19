package com.fpl.errormsg;

public enum FPLCommonErrorMessage implements IErrorMessage {
	
	PLEASE_ENTER("Please enter {0} !"),
	PLEASE_SELECT("Please select {0} !"),
	NO_RECORD_FOUND("No Record Found !"),
	INVALID(" {0} "),
	TECHNICAL_PROBLEM("Technical Problem occured : {0} ");
	
	private String errorMessage;
	private FPLCommonErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}
}