package com.fpl.core.policy;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PolicyListPageView {

	private Long id;
	private String policyNumber;
	private String requestId;
	private String policyDate;
	private String type;
	private String status;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @return the policyNumber
	 */
	public String getPolicyNumber() {
		return policyNumber;
	}

	/**
	 * @param policyNumber the policyNumber to set
	 */
	public void setPolicyNumber(final String policyNumber) {
		this.policyNumber = policyNumber;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
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
	 * @return the policyDate
	 */
	public String getPolicyDate() {
		return policyDate;
	}

	/**
	 * @param policyDate the policyDate to set
	 */
	public void setPolicyDate(final String policyDate) {
		this.policyDate = policyDate;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
