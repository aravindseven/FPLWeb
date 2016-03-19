package com.fpl.profile.admin;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.login.UserLoginInfo;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.personal.PersonalDataPV;

public class AdminProfilePV {

	private PersonalDataPV personalDataPV;
	private AddressPV addressPV;
	private String validationDate;
	private String status;
	private String state; 
	private UserLoginInfo loginInfo;
	
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
