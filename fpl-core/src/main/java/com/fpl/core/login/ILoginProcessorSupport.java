package com.fpl.core.login;

import javax.servlet.http.HttpServletRequest;

import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.LoginCredential;

public interface ILoginProcessorSupport { 
 
	void processRecoverPassword(LoginCredential loginCredential);
	
	void processRecoverPassword(LoginCredential loginCredential, HttpServletRequest request);
	
	void processLoginReset(final UserLoginInfo info );
	
	void processRecoverUserName(final LoginCredential loginCredential);
	
	void processRecoverUserName(final LoginCredential loginCredential, HttpServletRequest request);
	
	boolean isValidOTP(final String otp, final String date);
	
	void SendSecEmailVerfication(final LoginCredential loginCredential);
	
	void SendSecEmailVerfication(final LoginCredential loginCredential, HttpServletRequest request);
}
