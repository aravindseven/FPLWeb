package com.fpl.core.communication;

import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.login.UserType;

public class UserSearchParam {

	private Collection<UserType> userTypeCollection;
	private Boolean online;
	
	/**
	 * @return the userTypeCollection
	 */
	public Collection<UserType> getUserTypeCollection() {
		return userTypeCollection;
	}

	/**
	 * @param userTypeCollection the userTypeCollection to set
	 */
	public void setUserTypeCollection(final Collection<UserType> userTypeCollection) {
		this.userTypeCollection = userTypeCollection;
	}

	/**
	 * @return the online
	 */
	public Boolean isOnline() {
		return online;
	}

	/**
	 * @param online the online to set
	 */
	public void setOnline(final Boolean online) {
		this.online = online;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
