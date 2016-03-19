package com.fpl.core.subsciption.fpl;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SubscriptionMasterPV {

	private Long id;
	private String type;
	private Long rate;
	private String note;
	private Date startDate;
	private Date endDate;
	private Date creationDate;
	private Long planFrequency;
	private Long countryId;
	private Integer status;
	
	private CountryPV countryPV;
	
	
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

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
	 * @return the rate
	 */
	public Long getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(final Long rate) {
		this.rate = rate;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(final String note) {
		this.note = note;
	}
	
	
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getPlanFrequency() {
		return planFrequency;
	}

	public void setPlanFrequency(Long planFrequency) {
		this.planFrequency = planFrequency;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

	public CountryPV getCountryPV() {
		return countryPV;
	}

	public void setCountryPV(CountryPV countryPV) {
		this.countryPV = countryPV;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
