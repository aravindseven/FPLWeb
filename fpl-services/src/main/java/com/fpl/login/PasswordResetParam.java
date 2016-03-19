package com.fpl.login;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PasswordResetParam {

	private String email;
	private String otp;
	private String newPassword;
	private String conformPassword;
	
	/**
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}
	
	/**
	 * @param otp the otp to set
	 */
	public void setOtp(final String otp) {
		this.otp = otp;
	}
	
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(final String newPassword) {
		this.newPassword = newPassword;
	}
	
	/**
	 * @return the conformPassword
	 */
	public String getConformPassword() {
		return conformPassword;
	}
	
	/**
	 * @param conformPassword the conformPassword to set
	 */
	public void setConformPassword(final String conformPassword) {
		this.conformPassword = conformPassword;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
