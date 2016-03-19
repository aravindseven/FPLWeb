package com.fpl.persistence.customer;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CustomerSearchParam {

	private String type;
	private String location;
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
