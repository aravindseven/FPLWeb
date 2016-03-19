package com.fpl.profile.financialplanner;

import java.util.Collection;
import java.util.Date;

import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.domain.Domain;
import com.fpl.login.UserLoginInfo;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.personal.PersonalDataPV;

public class FinancialPlannerPV {

	private PersonalDataPV personalDataPV;
	private PersonalDataPV secondaryDataPV;
	private AddressPV addressPV;
	private AddressPV permanentAddressPV;
	private String fplId;
	private String fplIdText;
	private String validationDate;
	private String status;
	private String state; 
	private String registration;
	private String  insuranceCompany;
	private String regEntity;
	private String expiryDate;
	private String coverage;
	private String specialization1;
	private String specialization2;
	private String agencyName;
	private String website;
	private UserLoginInfo loginInfo;
	private Collection<String> domainList;
	private Collection<String> companyList;
	private Collection<Domain> domains;
	private FPSubscriptionListPV subscriptionListPVs;
	private Date creationDate;
	private String alternativeemail;
	/**
	 * @return the personalDataPV
	 */
	public PersonalDataPV getPersonalDataPV() {
		return personalDataPV;
	}
	
	/**
	 * @param personalDataPV the personalDataPV to set
	 */
	public void setPersonalDataPV(final PersonalDataPV personalDataPV) {
		this.personalDataPV = personalDataPV;
	}
	
	/**
	 * @return the addressPV
	 */
	public AddressPV getAddressPV() {
		return addressPV;
	}
	
	/**
	 * @param addressPV the addressPV to set
	 */
	public void setAddressPV(final AddressPV addressPV) {
		this.addressPV = addressPV;
	}
	
	/**
	 * @return the validationDate
	 */
	public String getValidationDate() {
		return validationDate;
	}
	
	/**
	 * @param validationDate the validationDate to set
	 */
	public void setValidationDate(final String validationDate) {
		this.validationDate = validationDate;
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
	public String getRegEntity() {
		return regEntity;
	}
	
	/**
	 * @param regEntity the regEntity to set
	 */
	public void setRegEntity(final String regEntity) {
		this.regEntity = regEntity;
	}
	
	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(final String expiryDate) {
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
	 * @return the loginInfo
	 */
	public UserLoginInfo getLoginInfo() {
		return loginInfo;
	}
	
	/**
	 * @param loginInfo the loginInfo to set
	 */
	public void setLoginInfo(final UserLoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	
	/**
	 * @return the domainList
	 */
	public Collection<String> getDomainList() {
		return domainList;
	}
	
	/**
	 * @param domainList the domainList to set
	 */
	public void setDomainList(final Collection<String> domainList) {
		this.domainList = domainList;
	}
	
	/**
	 * @return the companyList
	 */
	public Collection<String> getCompanyList() {
		return companyList;
	}
	
	/**
	 * @param companyList the companyList to set
	 */
	public void setCompanyList(final Collection<String> companyList) {
		this.companyList = companyList;
	}

	/**
	 * @return the domains
	 */
	public Collection<Domain> getDomains() {
		return domains;
	}

	/**
	 * @param domains the domains to set
	 */
	public void setDomains(final Collection<Domain> domains) {
		this.domains = domains;
	}

	/**
	 * @return the permanentAddressPV
	 */
	public AddressPV getPermanentAddressPV() {
		return permanentAddressPV;
	}

	/**
	 * @param permanentAddressPV the permanentAddressPV to set
	 */
	public void setPermanentAddressPV(final AddressPV permanentAddressPV) {
		this.permanentAddressPV = permanentAddressPV;
	}
	
	

	public PersonalDataPV getSecondaryDataPV() {
		return secondaryDataPV;
	}

	public void setSecondaryDataPV(PersonalDataPV secondaryDataPV) {
		this.secondaryDataPV = secondaryDataPV;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public String getFplId() {
		return fplId;
	}

	public void setFplId(String fplId) {
		this.fplId = fplId;
	}
	

	

	public FPSubscriptionListPV getSubscriptionListPVs() {
		return subscriptionListPVs;
	}

	public void setSubscriptionListPVs(FPSubscriptionListPV subscriptionListPVs) {
		this.subscriptionListPVs = subscriptionListPVs;
	}
	

	public String getFplIdText() {
		return fplIdText;
	}

	public void setFplIdText(String fplIdText) {
		this.fplIdText = fplIdText;
	}
	
	

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FinancialPlannerPV [validationDate=" + validationDate + ", status=" + status + ", state=" + state + ", registration=" 
				+ registration + ", regEntity=" + regEntity + ", expiryDate=" + expiryDate + ", coverage=" + coverage
				+ ", specialization1=" + specialization1 + ", specialization2=" + specialization2 + ", agencyName=" + agencyName + ", website="
				+ website + ", domainList="	+ domainList + "]";
	}

	public String getAlternativeemail() {
		return alternativeemail;
	}

	public void setAlternativeemail(String alternativeemail) {
		this.alternativeemail = alternativeemail;
	}
	
	
}
