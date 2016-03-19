package com.fpl.core.login;

import javax.servlet.http.HttpServletRequest;

import com.fpl.login.FPLLoginOutput;
import com.fpl.login.ILoginInputParam;
import com.fpl.login.ILoginRecoveryInput;
import com.fpl.login.IUserRecoverInput;
import com.fpl.login.LoginRecordTO;
import com.fpl.login.UserLoginInfo;


public interface ILoginProcessor {

	UserLoginInfo getLoginInfo(ILoginInputParam loginParam,FPLLoginOutput loginOutput);
	
	boolean Loginlog(LoginRecordTO lr);
	
	//void recoverPassword(final ILoginRecoveryInput loginRecovery);
	
	//void recoverPassword(final ILoginRecoveryInput loginRecovery, HttpServletRequest request);
	
	//void recoverUserName(final ILoginRecoveryInput loginRecovery);
	
	//void recoverUserName(final ILoginRecoveryInput loginRecovery, HttpServletRequest request);
	
	boolean isValidOTP(final String otp, final String date);
	
	void SendSecEmailVerfication(final Long loginId);
	
	void SendSecEmailVerfication(final Long loginId, HttpServletRequest request);

	void recoverUserName(final IUserRecoverInput loginRecovery, HttpServletRequest request);

	void recoverPassword(final IUserRecoverInput loginRecovery, HttpServletRequest request);

	void recoverPassword(final IUserRecoverInput loginRecovery);

	void recoverUserName(final IUserRecoverInput loginRecovery);



	

	
}
