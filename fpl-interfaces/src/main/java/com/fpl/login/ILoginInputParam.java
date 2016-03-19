package com.fpl.login;

public interface ILoginInputParam {

	/**
	 * @return the email
	 */
	String getPrimaryEmailId();

	/**
	 * @return the sessionId
	 */
	String getSessionId();

	/**
	 * @return the password
	 */
	String getPassword();
	
	/**
	 * @return the country
	 */
	String getCountry();
	
	String getDeviceType();

	/**
	 * @param emailId the emailId to set
	 */
	void setPrimaryEmailId(final String primaryEmailId);

	/**
	 * @param password the password to set
	 */
	void setPassword(final String password);
	
	/**
	 * @param country the country to set
	 */
	void setCountry(final String country);
	
	void setDeviceType(final String devicetype);
}
