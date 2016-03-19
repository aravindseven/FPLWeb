package com.fpl.login;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UserLoginInfo {

	private Long loginCredentialid;
	private Long userTypeId;
	private String email;
	private String alternateMailId;
	private String mobileNumber;
	private String password;
	private String firstname;
	private String lastname;
	private UserType userType;
	private String country;
	private String secreatAnswer;
	private boolean secEmailVerified;
	private String profileUrl;
	private String status;
	
	/**
	 * @return the loginCredentialid
	 */
	public final Long getLoginCredentialid() {
		return loginCredentialid;
	}
	
	/**
	 * @param loginCredentialid the loginCredentialid to set
	 */
	public final void setLoginCredentialid(final Long loginCredentialid) {
		this.loginCredentialid = loginCredentialid;
	}
	
	/**
	 * @return the userType
	 */
	public final UserType getUserType() {
		return userType;
	}
	
	/**
	 * @param userType the userType to set
	 */
	public final void setUserType(final UserType userType) {
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
	 * @return the userTypeId
	 */
	public Long getUserTypeId() {
		return userTypeId;
	}

	/**
	 * @param userTypeId the userTypeId to set
	 */
	public void setUserTypeId(final Long userTypeId) {
		this.userTypeId = userTypeId;
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
	 * @return the alternateMailId
	 */
	public String getAlternateMailId() {
		return alternateMailId;
	}

	/**
	 * @param alternateMailId the alternateMailId to set
	 */
	public void setAlternateMailId(final String alternateMailId) {
		this.alternateMailId = alternateMailId;
	}

	/**
	 * @return the secreatAnswer
	 */
	public String getSecreatAnswer() {
		return secreatAnswer;
	}

	/**
	 * @param secreatAnswer the secreatAnswer to set
	 */
	public void setSecreatAnswer(final String secreatAnswer) {
		this.secreatAnswer = secreatAnswer;
	}
	
	/**
	 * @return the secEmailVerified
	 */
	public boolean isSecEmailVerified() {
		return secEmailVerified;
	}

	/**
	 * @param secEmailVerified the secEmailVerified to set
	 */
	public void setSecEmailVerified(final boolean secEmailVerified) {
		this.secEmailVerified = secEmailVerified;
	}
	
	

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
