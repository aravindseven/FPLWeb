package com.fpl.core.login;

import javax.servlet.http.HttpServletRequest;

import com.fpl.login.LoginRegisterParam;
import com.fpl.login.entity.LoginCredential;
import com.fpl.login.entity.LoginRecovery;
import com.fpl.profile.personal.PersonalData;

public interface ILoginPersisterSupport {

	LoginCredential getLoginCredential(final LoginRegisterParam loginRegister);
	
	void sendRegistration(final LoginCredential loginCredential);
	
	void sendRegistration(final LoginCredential loginCredential, HttpServletRequest request);
	
	LoginRecovery getLoginRecovery(final String otp);
	
	void deleteLoginRecovery(final LoginRecovery loginRecovery);
	void UpdateLoginRecovery(final LoginRecovery loginRecovery);
	
    PersonalData getpersonaldata(final LoginRegisterParam loginRegister);
}
