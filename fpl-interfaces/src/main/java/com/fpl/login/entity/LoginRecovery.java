package com.fpl.login.entity;

import java.util.Date;

public class LoginRecovery {

	private Long id;
	private Long loginCredentialId;
	private Integer otp;
	private Date otpDate;
	private String Expired;
	
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
	 * @return the loginCredentialId
	 */
	public final Long getLoginCredentialId() {
		return loginCredentialId;
	}
	
	/**
	 * @param loginCredentialId the loginCredentialId to set
	 */
	public final void setLoginCredentialId(final Long loginCredentialId) {
		this.loginCredentialId = loginCredentialId;
	}
	
	/**
	 * @return the otp
	 */
	public final Integer getOtp() {
		return otp;
	}
	
	/**
	 * @param otp the otp to set
	 */
	public final void setOtp(final Integer otp) {
		this.otp = otp;
	}
	
	/**
	 * @return the otpDate
	 */
	public final Date getOtpDate() {
		return otpDate;
	}
	
	/**
	 * @param otpDate the otpDate to set
	 */
	public final void setOtpDate(final Date otpDate) {
		this.otpDate = otpDate;
	}

	public String getExpired() {
		return Expired;
	}

	public void setExpired(String expired) {
		Expired = expired;
	}
}
