package com.fpl.login.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class LoginCredential {

	private Long id;                 		
	private Long secretQuestionId;             	
	private Long userTypeId;              	
	private String password;
	private Date date;
	private String mailId;            	
	private String alternateMailId;
	private Long mobileNumber;
	private String secretAnswer;
	private String country;
	private User user;
	private SecretQuestion secretQuestion;
	
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public final void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * @return the secretQuestionId
	 */
	public final Long getSecretQuestionId() {
		return secretQuestionId;
	}
	
	/**
	 * @param secretQuestionId the secretQuestionId to set
	 */
	public final void setSecretQuestionId(final Long secretQuestionId) {
		this.secretQuestionId = secretQuestionId;
	}
	
	/**
	 * @return the userTypeId
	 */
	public final Long getUserTypeId() {
		return userTypeId;
	}
	
	/**
	 * @param userTypeId the userTypeId to set
	 */
	public final void setUserTypeId(final Long userTypeId) {
		this.userTypeId = userTypeId;
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
	 * @return the date
	 */
	public final Date getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public final void setDate(final Date date) {
		this.date = date;
	}
	
	/**
	 * @return the mailId
	 */
	public final String getMailId() {
		return mailId;
	}
	
	/**
	 * @param mailId the mailId to set
	 */
	public final void setMailId(final String mailId) {
		this.mailId = mailId;
	}
	
	/**
	 * @return the alternateMailId
	 */
	public final String getAlternateMailId() {
		return alternateMailId;
	}
	
	/**
	 * @param alternateMailId the alternateMailId to set
	 */
	public final void setAlternateMailId(final String alternateMailId) {
		this.alternateMailId = alternateMailId;
	}
	
	/**
	 * @return the mobileNumber
	 */
	public final Long getMobileNumber() {
		return mobileNumber;
	}
	
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public final void setMobileNumber(final Long mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	
	/**
	 * @return the user
	 */
	public final User getUser() {
		return user;
	}
	
	/**
	 * @param user the user to set
	 */
	public final void setUser(final User user) {
		this.user = user;
	}
	
	/**
	 * @return the secretQuestion
	 */
	public final SecretQuestion getSecretQuestion() {
		return secretQuestion;
	}
	
	/**
	 * @param secretQuestion the secretQuestion to set
	 */
	public final void setSecretQuestion(final SecretQuestion secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
