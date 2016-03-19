package com.fpl.controller.profile;

import java.util.Date;

import com.fpl.login.UserLoginInfo;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.admin.AdminProfilePV;
import com.fpl.profile.customer.CustomerPV;
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.util.DateUtil;

public class ProfileControllerSupport {

	public CustomerPV getCustomerPV(final UserLoginInfo loginInfo) {
		final CustomerPV customerPV = new CustomerPV();
		customerPV.setValidationDate(DateUtil.getFormattedDate(new Date(), "dd/MM/yyyy"));
		customerPV.setState(ProfileState.DEACTIVE);
		customerPV.setStatus(ProfileStatus.PENDING);
		final AddressPV addressPV = new AddressPV();
		addressPV.setCountry(loginInfo.getCountry());
		customerPV.setPrimaryPersonalData(getPersonalDataPV(loginInfo));
		customerPV.setAddressPV(addressPV);
		customerPV.setLoginInfo(loginInfo);
		
		return customerPV;
	}
	
	public FinancialPlannerPV getFinancialPlannerPV(final UserLoginInfo loginInfo) {
		final FinancialPlannerPV plannerPV = new FinancialPlannerPV();
		plannerPV.setValidationDate(DateUtil.getFormattedDate(new Date(), "dd/MM/yyyy"));
		plannerPV.setState(ProfileState.DEACTIVE.getValue());
		plannerPV.setStatus(ProfileStatus.PENDING.getStatus());
		final AddressPV addressPV = new AddressPV();
		addressPV.setCountry(loginInfo.getCountry());
		plannerPV.setPersonalDataPV(getPersonalDataPV(loginInfo));
		plannerPV.setAddressPV(addressPV);
		plannerPV.setLoginInfo(loginInfo);
		plannerPV.setCreationDate(new Date());
		return plannerPV;
	}
	
	public AdminProfilePV getAdminParam(final UserLoginInfo loginInfo) {
		final AdminProfilePV profilePV = new AdminProfilePV();
		profilePV.setValidationDate(DateUtil.getFormattedDate(new Date(), "dd/MM/yyyy"));
		profilePV.setState(ProfileState.DEACTIVE.getValue());
		profilePV.setStatus(ProfileStatus.PENDING.getStatus());
		final AddressPV addressPV = new AddressPV();
		addressPV.setCountry(loginInfo.getCountry());
		profilePV.setPersonalDataPV(getPersonalDataPV(loginInfo));
		profilePV.setAddressPV(addressPV);
		profilePV.setLoginInfo(loginInfo);
		return profilePV;
	}
	
	private PersonalDataPV getPersonalDataPV(final UserLoginInfo loginInfo) {
		final PersonalDataPV personalDataPV = new PersonalDataPV();
		personalDataPV.setEmail(loginInfo.getEmail());
		personalDataPV.setMobile(loginInfo.getMobileNumber());
		return personalDataPV;
	}
}
