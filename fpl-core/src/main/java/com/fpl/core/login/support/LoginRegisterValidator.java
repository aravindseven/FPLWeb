package com.fpl.core.login.support;

import org.apache.commons.lang.StringUtils;

import com.fpl.FPLException;
import com.fpl.common.IValidator;
import com.fpl.core.login.LoginErrorMessage;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.login.LoginRegisterParam;
import com.fpl.util.NumberUtil;
import com.fpl.util.StringUtil;

public class LoginRegisterValidator implements IValidator<LoginRegisterParam> {
	
	private LoginRegisterParam loginRegister;
	
	@Override
	public void validate(final LoginRegisterParam loginRegister) {
		this.loginRegister = loginRegister;
		validateEmail();
		validatePassword();
		//validateMobileNumber();
		validateSecretAnswer();
	}
	
	private void validateSecretAnswer() {
		if(StringUtil.isEmpty(loginRegister.getSecretAnswer())) {
			throw new FPLException(FPLCommonErrorMessage.PLEASE_ENTER, "Mother maiden Name");
		}
	}

	private void validateEmail() {
		if(StringUtil.isEmpty(loginRegister.getPrimaryEmailId())) {
			throw new FPLException(FPLCommonErrorMessage.PLEASE_ENTER, "Mail id");
		}
		/*if(StringUtil.isEmpty(loginRegister.getAlternateMailId())) {
			throw new FPLException(FPLCommonErrorMessage.PLEASE_ENTER, "Alternate Mail id");
		}*/
		/*if(StringUtil.isValidEmail(loginRegister.getPrimaryEmailId(), true)) {
			throw new FPLException(LoginErrorMessage.INVALID_EMAIL, "Mail");
		}
		if(StringUtil.isValidEmail(loginRegister.getAlternateMailId(), true)) {
			throw new FPLException(LoginErrorMessage.INVALID_EMAIL, "Alternate Mail");
		}*/
	}
	
	private void validatePassword() {
		if(StringUtil.isEmpty(loginRegister.getPassword())) {
			throw new FPLException(FPLCommonErrorMessage.PLEASE_ENTER, "Password");
		}
		if(loginRegister.getPassword().equals(loginRegister.getPrimaryEmailId())) {
			throw new FPLException(LoginErrorMessage.USERNAME_PASSWORD_DIFFE);
		}
		if(loginRegister.getPassword().length() < 8) {
			throw new FPLException(LoginErrorMessage.PASSWORD_SMALL);
		}
		/*if(StringUtil.isValidPassword(loginRegister.getPassword())) {
			throw new FPLException(LoginErrorMessage.INVALID_PASSWORD);
		}*/
	}
	
	/*private void validateMobileNumber() {
		if(!StringUtils.isEmpty(loginRegister.getMobileNumber()) && !NumberUtil.isValidNumber(loginRegister.getMobileNumber())) {
			throw new FPLException(FPLCommonErrorMessage.INVALID, "Mobile Number");
		}
	}*/
}
