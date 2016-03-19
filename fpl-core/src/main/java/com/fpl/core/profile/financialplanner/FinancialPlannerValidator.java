package com.fpl.core.profile.financialplanner;

import com.fpl.FPLException;
import com.fpl.common.IValidator;
import com.fpl.core.profile.personal.PersonalDataValidator;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.util.StringUtil;

public class FinancialPlannerValidator implements IValidator<FinancialPlannerPV> {

	@Override
	public void validate(final FinancialPlannerPV plannerPV) {
		validatePersonalData(plannerPV.getPersonalDataPV());
//		if(StringUtil.isEmpty(plannerPV.getPersonalDataPV().getDob())) {
//			throw new FPLException(FPLCommonErrorMessage.INVALID, "Date of Birth");
//		}
//		if(StringUtil.isEmpty(plannerPV.getRegEntity())) {
//			throw new FPLException(FPLCommonErrorMessage.INVALID, "RegEntity");
//		}
//		if(StringUtil.isEmpty(plannerPV.getExpiryDate())) {
//			throw new FPLException(FPLCommonErrorMessage.INVALID, "Expiry Date");
//		}
	}
	
	private void validatePersonalData(final PersonalDataPV personalData) {
		new PersonalDataValidator().validatePersonalData(personalData);
	}
}
