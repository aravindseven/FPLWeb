package com.fpl.core.communication;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.login.UserType;

public class UserInfo {

	private Long loginId;
	private String user;
	private String firstName;
	private String lastName;
	private UserType userType;
	private String email;
	private String alternateEmail;
	private boolean online;
	private String mood_status;
	private String status;
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(final String user) {
		this.user = user;
	}

	/**
	 * @return the status
	 */
	public String getMood_status() {
		return mood_status;
	}

	/**
	 * @param status the status to set
	 */
	public void setMood_status(String mood_status) {
		this.mood_status = mood_status;
	}

	/**
	 * @return the loginId
	 */
	public Long getLoginId() {
		return loginId;
	}
	
	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(final Long loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
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
	 * @return the online
	 */
	public boolean isOnline() {
		return online;
	}

	/**
	 * @param online the online to set
	 */
	public void setOnline(final boolean online) {
		this.online = online;
	}
	
	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(final UserType userType) {
		this.userType = userType;
	}

	/**
	 * @return the alternateEmail
	 */
	public String getAlternateEmail() {
		return alternateEmail;
	}

	/**
	 * @param alternateEmail the alternateEmail to set
	 */
	public void setAlternateEmail(final String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
