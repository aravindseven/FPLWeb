package com.fpl.login;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class LoginRegisterParam {

	private String primaryEmailId;
	private String firstname;
	private String alternativemailId;
	private String mobilenumber;
	private String password;
	private String lastname;
	private String userType;
	private String secretAnswer;
	private String country;
	private String secretQuestion;
	private boolean alternateMailVerified;
	private Date expireDate;
	
	/**
	 * @return the alternateMailVerified
	 */
	public boolean isAlternateMailVerified() {
		return alternateMailVerified;
	}

	/**
	 * @param alternateMailVerified the alternateMailVerified to set
	 */
	public void setAlternateMailVerified(final boolean alternateMailVerified) {
		this.alternateMailVerified = alternateMailVerified;
	}

	/**
	 * @return the secretQuestion
	 */
	public final String getSecretQuestion() {
		return secretQuestion;
	}
	
	/**
	 * @param secretQuestion the secretQuestion to set
	 */
	public final void setSecretQuestion(final String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	
	/**
	 * @return the userType
	 */
	public final String getUserType() {
		return userType;
	}
	
	/**
	 * @param userType the userType to set
	 */
	public final void setUserType(final String userType) {
		this.userType = userType;
	}
	
	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}
	
	/**
	 * @return the mailId
	 */
	public final String getPrimaryEmailId() {
		return primaryEmailId;
	}
	
	/**
	 * @param mailId the mailId to set
	 */
	public final void setPrimaryEmailId(final String mailId) {
		this.primaryEmailId = mailId;
	}
	
	/**
	 * @return the firstname
	 */
	public final String getFirstname() {
		return firstname;
	}
	
	/**
	 * @param alternateMailId the alternateMailId to set
	 */
	public final void setFirstname(final String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * @return the secretAnswer
	 */
	public final String getSecretAnswer() {
		return secretAnswer;
	}
	
	/**
	 * @param secretAnswer the secretAnswer to set
	 */
	public final void setSecretAnswer(final String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}
	
	/**
	 * @return the country
	 */
	public final String getCountry() {
		return country;
	}
	
	/**
	 * @param country the country to set
	 */
	public final void setCountry(final String country) {
		this.country = country;
	}
	
	
	
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getAlternativemailId() {
		return alternativemailId;
	}

	public void setAlternativemailId(String alternativemailId) {
		this.alternativemailId = alternativemailId;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
