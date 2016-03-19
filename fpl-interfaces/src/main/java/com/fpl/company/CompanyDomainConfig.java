package com.fpl.company;

import com.fpl.domain.Domain;

public class CompanyDomainConfig {
	
	private Long id;
	private Long companyId;
	private Long domainId;
	private Domain domain;
	
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
	 * @return the domain
	 */
	public Domain getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(final Domain domain) {
		this.domain = domain;
	}
}
