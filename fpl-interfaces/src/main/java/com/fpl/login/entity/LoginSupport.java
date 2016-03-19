package com.fpl.login.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class LoginSupport {

	private Long id;
	private Long loginCredentialId;
	private Integer attempt;
	private Integer locked;
	private Integer active;
	private String passwordOne;
	private String passwordTwo;
	private String passwordThree;
	private Integer secEmailVerified;
	private Integer online;
	private String imageUrl;
	private Date expireDate;
	private String browser_Active;
	private String mobile_Active;
	private String non_consecutive_on;
	private String status;
	private String Sessionid;

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
	 * @return the attempT
	 */
	public final Integer getAttempt() {
		return attempt;
	}
	
	/**
	 * @param attempt the attempT to set
	 */
	public final void setAttempt(final Integer attempt) {
		this.attempt = attempt;
	}
	
	/**
	 * @return the locked
	 */
	public final Integer getLocked() {
		return locked;
	}
	
	/**
	 * @param locked the locked to set
	 */
	public final void setLocked(final Integer locked) {
		this.locked = locked;
	}
	
	/**
	 * @return the active
	 */
	public final Integer getActive() {
		return active;
	}
	
	/**
	 * @param active the active to set
	 */
	public final void setActive(final Integer active) {
		this.active = active;
	}
	
	/**
	 * @return the passwordOne
	 */
	public final String getPasswordOne() {
		return passwordOne;
	}
	
	/**
	 * @param passwordOne the passwordOne to set
	 */
	public final void setPasswordOne(final String passwordOne) {
		this.passwordOne = passwordOne;
	}
	
	/**
	 * @return the passwordTwo
	 */
	public final String getPasswordTwo() {
		return passwordTwo;
	}
	
	/**
	 * @param passwordTwo the passwordTwo to set
	 */
	public final void setPasswordTwo(final String passwordTwo) {
		this.passwordTwo = passwordTwo;
	}
	
	/**
	 * @return the passwordThree
	 */
	public final String getPasswordThree() {
		return passwordThree;
	}
	
	/**
	 * @param passwordThree the passwordThree to set
	 */
	public final void setPasswordThree(final String passwordThree) {
		this.passwordThree = passwordThree;
	}
	
	/**
	 * @return the secEmailVerified
	 */
	public Integer getSecEmailVerified() {
		return secEmailVerified;
	}

	/**
	 * @param secEmailVerified the secEmailVerified to set
	 */
	public void setSecEmailVerified(final Integer secEmailVerified) {
		this.secEmailVerified = secEmailVerified;
	}
	
	/**
	 * @return the online
	 */
	public Integer getOnline() {
		return online;
	}

	/**
	 * @param online the online to set
	 */
	public void setOnline(final Integer online) {
		this.online = online;
	}
	
	

	

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public String getBrowser_Active() {
		return browser_Active;
	}

	public void setBrowser_Active(String browser_Active) {
		this.browser_Active = browser_Active;
	}

	public String getMobile_Active() {
		return mobile_Active;
	}

	public void setMobile_Active(String mobile_Active) {
		this.mobile_Active = mobile_Active;
	}
	
	public String getNon_consecutive_on() {
		return non_consecutive_on;
	}

	public void setNon_consecutive_on(String non_consecutive_on) {
		this.non_consecutive_on = non_consecutive_on;
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

	public String getSessionid() {
		return Sessionid;
	}

	public void setSessionid(String sessionid) {
		Sessionid = sessionid;
	}
}
