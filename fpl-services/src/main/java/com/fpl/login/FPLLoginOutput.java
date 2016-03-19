package com.fpl.login;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FPLLoginOutput {

	private String user;
	private String errorMsg;
	private boolean success;
	private String session;
	private UserLoginInfo userLoginInfo;
	private String Link;
	
	/**
	 * @return the user
	 */
	public final String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public final void setUser(final String user) {
		this.user = user;
	}

	/**
	 * @return the errorMsg
	 */
	public final String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public final void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the success
	 */
	public final boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public final void setSuccess(final boolean success) {
		this.success = success;
	}

	/**
	 * @return the session
	 */
	public final String getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public final void setSession(final String session) {
		this.session = session;
	}
	
	/**
	 * @return the userLoginInfo
	 */
	public UserLoginInfo getUserLoginInfo() {
		return userLoginInfo;
	}

	/**
	 * @param userLoginInfo the userLoginInfo to set
	 */
	public void setUserLoginInfo(final UserLoginInfo userLoginInfo) {
		this.userLoginInfo = userLoginInfo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}
}
