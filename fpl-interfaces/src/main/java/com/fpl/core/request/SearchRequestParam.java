package com.fpl.core.request;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SearchRequestParam {

	private String id;
	private String requestId;
	private String fromDate;
	private String toDate;
	private String type;
	private String status;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
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
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(final String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(final String toDate) {
		this.toDate = toDate;
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
