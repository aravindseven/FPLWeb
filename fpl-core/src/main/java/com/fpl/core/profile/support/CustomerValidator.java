package com.fpl.core.profile.support;

import com.fpl.common.IValidator;
import com.fpl.core.profile.personal.PersonalDataValidator;
import com.fpl.profile.customer.CustomerPV;
import com.fpl.profile.personal.PersonalDataPV;

public class CustomerValidator implements IValidator<CustomerPV> {

	@Override
	public void validate(final CustomerPV customerPV) {
		validatePersonalData(customerPV.getPrimaryPersonalData());
		if(customerPV.getSecondaryPersonalData() != null) {
			validatePersonalData(customerPV.getSecondaryPersonalData());
		}
		/*if(StringUtil.isEmpty(customerPV.getPrimaryPersonalData().getDob())) {
			throw new FPLException(FPLCommonErrorMessage.INVALID, "Date of Birth");
		}*/
	}
	
	private void validatePersonalData(final PersonalDataPV personalData) {
		new PersonalDataValidator().validatePersonalData(personalData);
	}
}
