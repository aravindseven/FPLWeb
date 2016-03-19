package com.fpl.financialplanner;

public class FplCompanyConfig {
	
	private Long id;
	private Long financialPlannerId;
	private Long companyId;
	
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
	 * @return the financialPlannerId
	 */
	public Long getFinancialPlannerId() {
		return financialPlannerId;
	}
	
	/**
	 * @param financialPlannerId the financialPlannerId to set
	 */
	public void setFinancialPlannerId(final Long financialPlannerId) {
		this.financialPlannerId = financialPlannerId;
	}
	
	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}
	
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(final Long companyId) {
		this.companyId = companyId;
	}
}
