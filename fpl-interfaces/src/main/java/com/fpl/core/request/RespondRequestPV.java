package com.fpl.core.request;

import org.apache.commons.lang.builder.ToStringBuilder;

public class RespondRequestPV {

	private String loginId;
	private String requestId;
	private String fplId;
	private String status;
	private String description;
	
	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(final String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}
	
	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(final String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the fplId
	 */
	public String getFplId() {
		return fplId;
	}

	/**
	 * @param fplId the fplId to set
	 */
	public void setFplId(final String fplId) {
		this.fplId = fplId;
	}

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
