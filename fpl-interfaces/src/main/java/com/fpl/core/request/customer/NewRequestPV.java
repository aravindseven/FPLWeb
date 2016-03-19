package com.fpl.core.request.customer;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class NewRequestPV {

	private String requestId;
	private String requestIdText;
	private Date creationDate;
	private String loginId;
	private String type;
	private String typeDescription;
	private String description;
	private String postalCode;
	private String location;
	private String keyword;
	private String urgency;
	private String followUp;
	private String country;
	private Collection<String> fplList;
	private boolean sendToFpl;
	private Float longitude;
	private Float latitude;
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

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
	 * @return the urgency
	 */
	public String getUrgency() {
		return urgency;
	}

	/**
	 * @param urgency the urgency to set
	 */
	public void setUrgency(final String urgency) {
		this.urgency = urgency;
	}

	/**
	 * @return the followUp
	 */
	public String getFollowUp() {
		return followUp;
	}

	/**
	 * @param followUp the followUp to set
	 */
	public void setFollowUp(final String followUp) {
		this.followUp = followUp;
	}
	
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}
	
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the fplList
	 */
	public Collection<String> getFplList() {
		return fplList;
	}

	/**
	 * @param fplList the fplList to set
	 */
	public void setFplList(final Collection<String> fplList) {
		this.fplList = fplList;
	}

	/**
	 * @return the sendToFpl
	 */
	public boolean isSendToFpl() {
		return sendToFpl;
	}

	/**
	 * @param sendToFpl the sendToFpl to set
	 */
	public void setSendToFpl(final boolean sendToFpl) {
		this.sendToFpl = sendToFpl;
	}
	
	

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
		this.requestIdText="REQ"+String.format("%010d", Integer.parseInt(requestId));
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	

	public String getRequestIdText() {
		
		if(requestId==null)
		{
			requestId="0";
		}
		
		return "REQ"+String.format("%04d", Integer.parseInt(requestId));
	}

	public void setRequestIdText(String requestIdText) {
		this.requestIdText = requestIdText;
	}

	
	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
