package com.fpl.core.subsciption.advertisement;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AdvSubscriptionListPV {

	private String id;
	private String Name;
	private String ContactName;
	private String Status;
	
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
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		Name = name;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return ContactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(final String contactName) {
		ContactName = contactName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return Status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		Status = status;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
