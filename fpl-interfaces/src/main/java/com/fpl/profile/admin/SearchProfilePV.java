package com.fpl.profile.admin;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SearchProfilePV {

	private String name;
	private String email;
	private String mobileNum;
	private Long subScriptionId;
	private Long fplId;
	
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
	 * @return the mobileNum
	 */
	public String getMobileNum() {
		return mobileNum;
	}
	
	/**
	 * @param mobileNum the mobileNum to set
	 */
	public void setMobileNum(final String mobileNum) {
		this.mobileNum = mobileNum;
	}
	
	
	
	public Long getSubScriptionId() {
		return subScriptionId;
	}

	public void setSubScriptionId(Long subScriptionId) {
		this.subScriptionId = subScriptionId;
	}
	
	public Long getFplId() {
		return fplId;
	}

	public void setFplId(Long fplId) {
		this.fplId = fplId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
