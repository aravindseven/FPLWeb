package com.fpl.core.profile.company;

import com.fpl.FPLException;
import com.fpl.common.IValidator;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.profile.company.CompanyPV;
import com.fpl.util.NumberUtil;
import com.fpl.util.StringUtil;

public class CompanyValidator implements IValidator<CompanyPV> {

	@Override
	public void validate(final CompanyPV companyPV) {
		if(StringUtil.isEmpty(companyPV.getPersoanNumber1())) {
			throw new FPLException(FPLCommonErrorMessage.PLEASE_ENTER, "PersoanNumber1");
		}
		if(StringUtil.isEmpty(companyPV.getPersoanNumber1())) {
			throw new FPLException(FPLCommonErrorMessage.PLEASE_ENTER, "PersoanNumber2");
		}
		if(StringUtil.isEmpty(companyPV.getLandLine())) {
			throw new FPLException(FPLCommonErrorMessage.PLEASE_ENTER, "LandLine");
		}
		if(!NumberUtil.isValidNumber(companyPV.getPersoanNumber1())) {
			throw new FPLException(FPLCommonErrorMessage.INVALID, "PersoanNumber1");
		}
		if(!NumberUtil.isValidNumber(companyPV.getPersoanNumber1())) {
			throw new FPLException(FPLCommonErrorMessage.INVALID, "PersoanNumber2");
		}
		if(!NumberUtil.isValidNumber(companyPV.getLandLine())) {
			throw new FPLException(FPLCommonErrorMessage.INVALID, "LandLine");
		}
	}
}
