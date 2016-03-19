package com.fpl.profile.customer;

import java.util.Date;

import com.fpl.login.UserLoginInfo;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.personal.PersonalDataPV;

public class CustomerPV {

	private PersonalDataPV primaryPersonalData;
	private PersonalDataPV secondaryPersonalData;
	private AddressPV addressPV;
	private AddressPV permanentAddressPV;
	private String terms;
	private String validationDate;
	private ProfileStatus status;
	private ProfileState state; 
	private UserLoginInfo loginInfo;
	private Date registrationDate;
	private String customerId;
	private String customerIdText;
	private String confPreference;
	private String alternativeemailid;
	
	
	
	/**
	 * @return the primaryPersonalData
	 */
	public PersonalDataPV getPrimaryPersonalData() {
		return primaryPersonalData;
	}

	/**
	 * @param primaryPersonalData the primaryPersonalData to set
	 */
	public void setPrimaryPersonalData(final PersonalDataPV primaryPersonalData) {
		this.primaryPersonalData = primaryPersonalData;
	}

	/**
	 * @return the secondaryPersonalData
	 */
	public PersonalDataPV getSecondaryPersonalData() {
		return secondaryPersonalData;
	}

	/**
	 * @param secondaryPersonalData the secondaryPersonalData to set
	 */
	public void setSecondaryPersonalData(final PersonalDataPV secondaryPersonalData) {
		this.secondaryPersonalData = secondaryPersonalData;
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
	 * @return the terms
	 */
	public String getTerms() {
		return terms;
	}
	
	/**
	 * @param terms the terms to set
	 */
	public void setTerms(final String terms) {
		this.terms = terms;
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
	public ProfileStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final ProfileStatus status) {
		this.status = status;
	}

	/**
	 * @return the state
	 */
	public ProfileState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(final ProfileState state) {
		this.state = state;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerIdText() {
		return customerIdText;
	}

	public void setCustomerIdText(String customerIdText) {
		this.customerIdText = customerIdText;
	}

	public String getConfPreference() {
		return confPreference;
	}

	public void setConfPreference(String confPreference) {
		this.confPreference = confPreference;
	}

	public String getAlternativeemailid() {
		return alternativeemailid;
	}

	public void setAlternativeemailid(String alternativeemailid) {
		this.alternativeemailid = alternativeemailid;
	}
	
	
	
}
