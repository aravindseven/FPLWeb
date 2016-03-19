package com.fpl.profile.admin;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ProfileSearchResultPV {

	private String id;
	private String name;
	private String dob;
	private String email;
	private String mobileNumber;
	private String userType;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}
	
	/**
	 * @param dob the dob to set
	 */
	public void setDob(final String dob) {
		this.dob = dob;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}
	
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(final String userType) {
		this.userType = userType;
	}
	
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(final String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
