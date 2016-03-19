package com.fpl.core.profile.support;

import java.util.Date;

import com.fpl.FPLException;
import com.fpl.common.AbstractTransformer;
import com.fpl.core.profile.ProfileErrorMessage;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.login.entity.LoginCredential;
import com.fpl.persistence.login.ILoginCredentialDAO;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.customer.Customer;
import com.fpl.profile.customer.CustomerPV;
import com.fpl.profile.personal.PersonalData;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.util.DateUtil;
import com.fpl.util.StringUtil;

public class CustomerTransformer extends AbstractTransformer<CustomerPV, Customer> {
	
	private ILoginCredentialDAO loginCredentialDAO;
	
	@Override
	public Customer transform(final CustomerPV customerParam) {
		final Customer customer = new Customer();
		final PersonalData personalData = getPersonData(customerParam.getPrimaryPersonalData());
		final LoginCredential loginCredential = loginCredentialDAO.getLoginCredentialByMail(personalData.getEmail());
		if(loginCredential == null) {
			throw new FPLException(ProfileErrorMessage.CUSTOMER_NOT_FOUND, personalData.getEmail());
		}else{
			// insertalternativeemailid in credentialtable
			//final String email = Encryption.encrypt(customerParam.getAlternativeemailid(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
			final LoginCredential loginCredential1=loginCredentialDAO.get(loginCredential.getId());
			loginCredential1.setAlternateMailId(customerParam.getAlternativeemailid());
			loginCredentialDAO.update(loginCredential1, true);
		}
		final AddressPV address = customerParam.getAddressPV();
		System.out.println(address);
		if(address != null && isNotEmpty(address)) {
			System.out.println("inside address......");
			personalData.setAddress(address);
		}
		final AddressPV permanentAddress = customerParam.getPermanentAddressPV();
		if(permanentAddress != null && isNotEmpty(permanentAddress)) {
			personalData.setPermanentAddress(permanentAddress);
		}
		
		customer.setPersonalData(personalData);
		final PersonalData secondaryPersonData = getPersonData(customerParam.getSecondaryPersonalData());
		customer.setSecondaryPersonData(secondaryPersonData);
		if(customerParam.getSecondaryPersonalData() != null) {
			customer.setRelationship(customerParam.getSecondaryPersonalData().getRelation());
		}
		customer.setStartDate(new Date());
		customer.setStatus(customerParam.getStatus().getStatus());
		customer.setState(customerParam.getState().getValue());
		customer.setLoginCredentialId(loginCredential.getId());
		customer.setUserTypeId(loginCredential.getUserTypeId());
		customer.setConfPreference(customerParam.getConfPreference());
		return customer;
	}
	
	private PersonalData getPersonData(final PersonalDataPV personalDataParam) {
		PersonalData personalData = null;
		if(personalDataParam != null) {
			personalData = new PersonalData();
			final Date dateOfBirth = DateUtil.getFormattedDate(personalDataParam.getDob(), "yyyy-MM-dd");
			personalData.setDateOfBirth(dateOfBirth);
			personalData.setEmail(personalDataParam.getEmail());
			personalData.setGenderOrLoc(personalDataParam.getGender());
			personalData.setLandlineNum(personalDataParam.getLandLine());
			personalData.setLastNameOrRNumber(personalDataParam.getLastName());
			personalData.setMobileNumber(personalDataParam.getMobile());
			personalData.setName(personalDataParam.getFirstName());
			personalData.setProofDesc(personalDataParam.getIdProof());
			personalData.setProofNum(personalDataParam.getProofNum());
			
		}
		return personalData;
	}
	
	private boolean isNotEmpty(final AddressPV address) {
		if(StringUtil.isNotEmpty(address.getBlock())) {
			return true;
		}
		if(StringUtil.isNotEmpty(address.getBuildingName())) {
			return true;
		}
		if(StringUtil.isNotEmpty(address.getStreet())) {
			return true;
		}
		if(StringUtil.isNotEmpty(address.getCity())) {
			return true;
		}
		if(StringUtil.isNotEmpty(address.getState())) {
			return true;
		}
		if(StringUtil.isNotEmpty(address.getCountry())) {
			return true;
		}
		return false;
	}
	/**
	 * @param credentialDAO the credentialDAO to set
	 */
	public void setCredentialDAO(final ILoginCredentialDAO credentialDAO) {
		this.loginCredentialDAO = credentialDAO;
	}
}
