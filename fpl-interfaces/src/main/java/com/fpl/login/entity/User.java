package com.fpl.login.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

public class User {

	private Long id;
	private String userType;
	
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
