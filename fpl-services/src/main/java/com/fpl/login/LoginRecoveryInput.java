package com.fpl.login;

import org.apache.commons.lang.builder.ToStringBuilder;

public class LoginRecoveryInput implements ILoginRecoveryInput {

	private String primaryEmailId;
	private String mobileNumber;
	private String altEmail;
	private String secretQuestion;
	
	/**
	 * @return the primaryEmailId
	 */
	@Override
	public String getPrimaryEmailId() {
		return primaryEmailId;
	}

	/**
	 * @param primaryEmailId the primaryEmailId to set
	 */
	public void setPrimaryEmailId(final String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}

	/**
	 * @return the mobileNumber
	 */
	@Override
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(final String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the altEmail
	 */
	@Override
	public String getAltEmail() {
		return altEmail;
	}

	/**
	 * @param altEmail the altEmail to set
	 */
	public void setAltEmail(final String altEmail) {
		this.altEmail = altEmail;
	}

	/**
	 * @return the secretQuestion
	 */
	@Override
	public String getSecretQuestion() {
		return secretQuestion;
	}

	/**
	 * @param secretQuestion the secretQuestion to set
	 */
	public void setSecretQuestion(final String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
