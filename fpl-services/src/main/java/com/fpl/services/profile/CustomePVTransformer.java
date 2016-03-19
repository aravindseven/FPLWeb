package com.fpl.services.profile;

import java.lang.reflect.InvocationTargetException;

import com.fpl.FPLException;
import com.fpl.common.AbstractTransformer;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.customer.Customer;
import com.fpl.profile.customer.CustomerPV;
import com.fpl.profile.personal.PersonalData;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.util.DateUtil;
import com.fpl.util.FPLBeanUtilsAdapter;

public class CustomePVTransformer extends AbstractTransformer<Customer, CustomerPV> {

	@Override
	public CustomerPV transform(final Customer customer) {
		final CustomerPV customerPV = new CustomerPV();
		final PersonalData personalData = customer.getPersonalData();
		customerPV.setPrimaryPersonalData(getPersonalDataPV(personalData));
		final PersonalDataPV personalDataPV  = getPersonalDataPV(customer.getSecondaryPersonData());
		if(personalDataPV != null) {
			personalDataPV.setRelation(customer.getRelationship());
		}
		customerPV.setSecondaryPersonalData(personalDataPV);
		try {
			if(personalData.getAddress() != null) {
				final AddressPV addressPV = new AddressPV();
				FPLBeanUtilsAdapter.copyProperties(personalData.getAddress(), addressPV);
				customerPV.setAddressPV(addressPV);
			}
			if(personalData.getPermanentAddress() != null) {
				final AddressPV permanentAddress = new AddressPV();
				FPLBeanUtilsAdapter.copyProperties(personalData.getPermanentAddress(), permanentAddress);
				customerPV.setPermanentAddressPV(permanentAddress);
			}
		} catch (final IllegalAccessException e) {
			throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM,e.getMessage());
		} catch (final InvocationTargetException e) {
			throw new FPLException(FPLCommonErrorMessage.TECHNICAL_PROBLEM,e.getMessage());
		}
		customerPV.setStatus(ProfileStatus.valueOf(customer.getStatus().toUpperCase()));
		customerPV.setState(ProfileState.valueOf(customer.getState().toUpperCase()));
		customerPV.setValidationDate(DateUtil.getFormattedDate(customer.getUpdationDate()));
		customerPV.setCustomerId(customer.getId().toString());
		customerPV.setRegistrationDate(customer.getStartDate());
		customerPV.setConfPreference(customer.getConfPreference());
		return customerPV;
	}
	
	private PersonalDataPV getPersonalDataPV(final PersonalData personalData) {
		PersonalDataPV personalDataPV = null;
		if(personalData != null) {
			personalDataPV = new PersonalDataPV();
			personalDataPV.setDob(DateUtil.getFormattedDate(personalData.getDateOfBirth(), "yyyy-MM-dd"));
			personalDataPV.setEmail(personalData.getEmail());
			personalDataPV.setFirstName(personalData.getName());
			personalDataPV.setLastName(personalData.getLastNameOrRNumber());
			personalDataPV.setGender(personalData.getGenderOrLoc());
			personalDataPV.setIdProof(personalData.getProofDesc());
			personalDataPV.setLandLine(personalData.getLandlineNum());
			personalDataPV.setMobile(personalData.getMobileNumber());
			personalDataPV.setProofNum(personalData.getProofNum());
		}
		return personalDataPV;
	}
}
