package com.fpl.core.profile.personal;

import com.fpl.FPLException;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.util.NumberUtil;

public class PersonalDataValidator {

	public void validatePersonalData(final PersonalDataPV personalData) {
		final boolean isValid = NumberUtil.isValidNumber(personalData.getMobile());
		if(!isValid) {
			throw new FPLException(FPLCommonErrorMessage.INVALID, "Invalid mobile number");
		}
	}
}
