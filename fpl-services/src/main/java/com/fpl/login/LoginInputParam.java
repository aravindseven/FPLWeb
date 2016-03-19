package com.fpl.login;

import org.apache.commons.lang.builder.ToStringBuilder;

public class LoginInputParam implements ILoginInputParam {

	private String primaryEmailId;
	private String password;
	private String country;
	private String sessionId;
    private String deviceType;
    
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the emailId
	 */
	@Override
	public final String getPrimaryEmailId() {
		return primaryEmailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public final void setPrimaryEmailId(final String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}

	/**
	 * @return the sessionId
	 */
	@Override
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(final String sessionId) {
		System.out.println("se3t seiion called in idsafhdfsa");
		System.out.println(sessionId);
		this.sessionId = sessionId;
		System.out.println(this.sessionId);
	}

	/**
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}
	
	/**
	 * @return the country
	 */
	@Override
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
