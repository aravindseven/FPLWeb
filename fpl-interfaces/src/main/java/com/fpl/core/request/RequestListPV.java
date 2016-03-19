package com.fpl.core.request;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class RequestListPV {

	private String id;
	private String requestIdText;
	private String domainName;
	private String domainId;
	private String location;
	private String postalCode;
	private String description;
	private String keyword;
	private String customerName;
	private Date createdDate;
	private String updatedDate;
	private String currentStatus;
	private String statusDisDesc;
	private String fplName;
	private String customerId;
	
	/**
	 * @return the fplName
	 */
	public String getFplName() {
		return fplName;
	}

	/**
	 * @param fplName the fplName to set
	 */
	public void setFplName(final String fplName) {
		this.fplName = fplName;
	}

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
		this.requestIdText="REQ"+String.format("%010d", Integer.parseInt(id));
	}
	
	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}
	
	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(final String domainName) {
		this.domainName = domainName;
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
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
	
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * @return the updatedDate
	 */
	public String getUpdatedDate() {
		return updatedDate;
	}
	
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(final String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	/**
	 * @return the currentStatus
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}
	
	/**
	 * @param currentStatus the currentStatus to set
	 */
	public void setCurrentStatus(final String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	/**
	 * @return the statusDisDesc
	 */
	public String getStatusDisDesc() {
		return statusDisDesc;
	}

	/**
	 * @param statusDisDesc the statusDisDesc to set
	 */
	public void setStatusDisDesc(final String statusDisDesc) {
		this.statusDisDesc = statusDisDesc;
	}
	

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRequestIdText() {
		return requestIdText;
	}

	public void setRequestIdText(String requestIdText) {
		this.requestIdText = requestIdText;
	}
	
	
}
