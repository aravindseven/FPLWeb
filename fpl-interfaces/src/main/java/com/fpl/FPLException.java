package com.fpl;

import java.text.MessageFormat;

import com.fpl.errormsg.IErrorMessage;

@SuppressWarnings("serial")
public class FPLException extends RuntimeException {

	private final IErrorMessage errorMsg;
	private String[] errorParams;
	private Throwable cause;
	
	public FPLException(final IErrorMessage errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public FPLException(final IErrorMessage errorMsg, final String... errorParams) {
		this.errorMsg = errorMsg;
		this.errorParams = errorParams;
	}
	
	public FPLException(final IErrorMessage errorMsg, final String errorParams) {
		this(errorMsg, new String[] {errorParams});
	}
	
	public FPLException(final IErrorMessage errorMsg, final Throwable cause) {
		this.errorMsg = errorMsg;
		this.cause = cause;
	}
	
	public FPLException(final IErrorMessage errorMsg, final String errorParams, final Throwable cause) {
		this(errorMsg, errorParams);
		this.cause = cause;
	}
	
	public FPLException(final IErrorMessage errorMsg, final String[] errorParams, final Throwable cause) {
		this(errorMsg, errorParams);
		this.cause = cause;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getCause()
	 */
	@Override
	public Throwable getCause() {
		return cause;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		final String errorMessage = errorMsg.getErrorMessage();
		return MessageFormat.format(errorMessage, errorParams);
	}
}
