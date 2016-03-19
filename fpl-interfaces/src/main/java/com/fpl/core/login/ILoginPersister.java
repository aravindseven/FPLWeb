package com.fpl.core.login;

import javax.servlet.http.HttpServletRequest;

import com.fpl.login.LoginRegisterParam;
import com.fpl.login.UserLoginInfo;

public interface ILoginPersister {

	void registerLogin(LoginRegisterParam loginRegister);
	
	void registerLogin(LoginRegisterParam loginRegister, HttpServletRequest request);
	
	void uploadProfileImage(HttpServletRequest request);
	
	void activateUser(final String otp);
	
	void activateUser(final String otp, final String password);
	
	void resetPassword(final String otp, final String password);
	
	void changePassword(final String email, final String password);
	
	void updateLoginDetail(LoginRegisterParam loginRegister);
	
	void verifySecondaryEmail(final String otp);
	
	void updateStatus(final String status,final UserLoginInfo info);
}
