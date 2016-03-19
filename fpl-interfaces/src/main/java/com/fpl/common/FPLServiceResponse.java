package com.fpl.common;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FPLServiceResponse {

	private String status;
	private String reason;
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	
	/**
	 * @param reason the reason to set
	 */
	public void setReason(final String reason) {
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
