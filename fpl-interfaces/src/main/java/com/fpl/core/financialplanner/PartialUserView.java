package com.fpl.core.financialplanner;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class PartialUserView {

	private String id;
	private String firstName;
	private String lastName;
	private String location;
	private boolean online;
	private String status;
	private int requestSent;
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
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
		this.status = String.valueOf(online);
	}
	
	
	
	public int getRequestSent() {
		return requestSent;
	}

	public void setRequestSent(int requestSent) {
		this.requestSent = requestSent;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartialUserView other = (PartialUserView) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
