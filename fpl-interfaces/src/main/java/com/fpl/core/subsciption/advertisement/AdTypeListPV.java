package com.fpl.core.subsciption.advertisement;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AdTypeListPV {

	private String id;
	private String typeName;
	private String subRate;
	private String startDate;
	private String endDate;
	
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
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(final String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the subRate
	 */
	public String getSubRate() {
		return subRate;
	}

	/**
	 * @param subRate the subRate to set
	 */
	public void setSubRate(final String subRate) {
		this.subRate = subRate;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
