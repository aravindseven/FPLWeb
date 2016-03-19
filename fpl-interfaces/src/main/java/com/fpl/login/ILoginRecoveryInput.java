package com.fpl.login;

public interface ILoginRecoveryInput {

	/**
	 * @return the primaryEmailId
	 */
	String getPrimaryEmailId();
	
	/**
	 * @return the mobileNumber
	 */
	String getMobileNumber();
	
	/**
	 * @return the altEmail
	 */
	String getAltEmail();
	
	/**
	 * @return the secretQuestion
	 */
	String getSecretQuestion();
	
	/**
	 * @param primaryEmailId the primaryEmailId to set
	 */
	void setPrimaryEmailId(final String primaryEmailId);
	
	/**
	 * @param altEmail the altEmail to set
	 */
	void setAltEmail(final String altEmail);

}
