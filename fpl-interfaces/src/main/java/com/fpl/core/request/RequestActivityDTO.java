package com.fpl.core.request;

import org.apache.commons.lang.builder.ToStringBuilder;

public class RequestActivityDTO {

	private String fplId;
	private String fpName;
	private String location;
	private String date;
	private String fplRespondedDate;
	private String updatedDate;
	private String status;
	private String statusDis;
	private String currentStatus;
	private String contactNumber;
	private int daysDiff;
	
	/**
	 * @return the statusDis
	 */
	public String getStatusDis() {
		return statusDis;
	}

	/**
	 * @param statusDis the statusDis to set
	 */
	public void setStatusDis(final String statusDis) {
		this.statusDis = statusDis;
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
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(final String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the fpName
	 */
	public String getFpName() {
		return fpName;
	}
	
	/**
	 * @param fpName the fpName to set
	 */
	public void setFpName(final String fpName) {
		this.fpName = fpName;
	}
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(final String date) {
		this.date = date;
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
	 * @return the fplRespondedDate
	 */
	public String getFplRespondedDate() {
		return fplRespondedDate;
	}

	/**
	 * @param fplRespondedDate the fplRespondedDate to set
	 */
	public void setFplRespondedDate(final String fplRespondedDate) {
		this.fplRespondedDate = fplRespondedDate;
	}

	/**
	 * @return the daysDiff
	 */
	public int getDaysDiff() {
		return daysDiff;
	}

	/**
	 * @param daysDiff the daysDiff to set
	 */
	public void setDaysDiff(final int daysDiff) {
		this.daysDiff = daysDiff;
	}
	
	
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
