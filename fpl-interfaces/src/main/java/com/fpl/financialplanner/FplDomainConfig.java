package com.fpl.financialplanner;

import com.fpl.domain.Domain;

public class FplDomainConfig {
	
	private Long id;
	private Long financialPlannerId;
	private Long domainId;
	private Domain domainInfo;
	
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
	 * @return the domainId
	 */
	public Long getDomainId() {
		return domainId;
	}
	
	/**
	 * @param domainId the domainId to set
	 */
	public void setDomainId(final Long domainId) {
		this.domainId = domainId;
	}

	/**
	 * @return the domainInfo
	 */
	public Domain getDomainInfo() {
		return domainInfo;
	}

	/**
	 * @param domainInfo the domainInfo to set
	 */
	public void setDomainInfo(final Domain domainInfo) {
		this.domainInfo = domainInfo;
	}
}
