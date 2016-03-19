package com.fpl.financialplanner;

import java.util.Date;
import java.util.Set;

import com.fpl.profile.personal.PersonalData;

public class FinancialPlanner {
	
	private Long id;
	private Long userTypeId;
	private Long loginCredentialId;
	private String registration;
	private Date regEntity;
	private Date expiryDate;
	private String coverage;
	private String specialization1;
	private String specialization2;
	private String agencyName;
	private String website;
	private String  insuranceCompany;
	private Date date;
	private Date updationDate;
	private String status;
	private String state;
	private Set<FplDomainConfig> fplDomainCfgs;
	private PersonalData personalData;
	private PersonalData secondaryPersonData;
	
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
	 * @return the userTypeId
	 */
	public Long getUserTypeId() {
		return userTypeId;
	}
	
	/**
	 * @param userTypeId the userTypeId to set
	 */
	public void setUserTypeId(final Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	/**
	 * @return the loginCredentialId
	 */
	public Long getLoginCredentialId() {
		return loginCredentialId;
	}
	
	/**
	 * @param loginCredentialId the loginCredentialId to set
	 */
	public void setLoginCredentialId(final Long loginCredentialId) {
		this.loginCredentialId = loginCredentialId;
	}
	
	/**
	 * @return the registration
	 */
	public String getRegistration() {
		return registration;
	}
	
	/**
	 * @param registration the registration to set
	 */
	public void setRegistration(final String registration) {
		this.registration = registration;
	}
	
	/**
	 * @return the regEntity
	 */
	public Date getRegEntity() {
		return regEntity;
	}
	
	/**
	 * @param regEntity the regEntity to set
	 */
	public void setRegEntity(final Date regEntity) {
		this.regEntity = regEntity;
	}
	
	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	/**
	 * @return the coverage
	 */
	public String getCoverage() {
		return coverage;
	}
	
	/**
	 * @param coverage the coverage to set
	 */
	public void setCoverage(final String coverage) {
		this.coverage = coverage;
	}
	
	/**
	 * @return the specialization1
	 */
	public String getSpecialization1() {
		return specialization1;
	}
	
	/**
	 * @param specialization1 the specialization1 to set
	 */
	public void setSpecialization1(final String specialization1) {
		this.specialization1 = specialization1;
	}
	
	/**
	 * @return the specialization2
	 */
	public String getSpecialization2() {
		return specialization2;
	}
	
	/**
	 * @param specialization2 the specialization2 to set
	 */
	public void setSpecialization2(final String specialization2) {
		this.specialization2 = specialization2;
	}
	
	/**
	 * @return the agencyName
	 */
	public String getAgencyName() {
		return agencyName;
	}
	
	/**
	 * @param agencyName the agencyName to set
	 */
	public void setAgencyName(final String agencyName) {
		this.agencyName = agencyName;
	}
	
	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * @param website the website to set
	 */
	public void setWebsite(final String website) {
		this.website = website;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(final Date date) {
		this.date = date;
	}
	
	/**
	 * @return the updationDate
	 */
	public Date getUpdationDate() {
		return updationDate;
	}
	
	/**
	 * @param updationDate the updationDate to set
	 */
	public void setUpdationDate(final Date updationDate) {
		this.updationDate = updationDate;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}
	
	/**
	 * @return the fplDomainCfgs
	 */
	public Set<FplDomainConfig> getFplDomainCfgs() {
		return fplDomainCfgs;
	}
	
	/**
	 * @param fplDomainCfgs the fplDomainCfgs to set
	 */
	public void setFplDomainCfgs(final Set<FplDomainConfig> fplDomainCfgs) {
		this.fplDomainCfgs = fplDomainCfgs;
	}

	/**
	 * @return the personalData
	 */
	public PersonalData getPersonalData() {
		return personalData;
	}

	/**
	 * @param personalData the personalData to set
	 */
	public void setPersonalData(final PersonalData personalData) {
		this.personalData = personalData;
	}

	/**
	 * @return the personalData
	 */
	public PersonalData getSecondaryPersonData() {
		return secondaryPersonData;
	}

	/**
	 * @param personalData the personalData to set
	 */
	public void setSecondaryPersonData(PersonalData secondaryPersonData) {
		this.secondaryPersonData = secondaryPersonData;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	
	
	
	
}
