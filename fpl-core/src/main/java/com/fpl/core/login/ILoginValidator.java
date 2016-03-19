package com.fpl.core.login;

import com.fpl.login.FPLLoginOutput;
import com.fpl.login.ILoginInputParam;
import com.fpl.login.LoginRecordTO;
import com.fpl.login.UserLoginInfo;


public interface ILoginValidator {

	void validteCredential(UserLoginInfo loginInfo, ILoginInputParam loginParam,FPLLoginOutput loginOutput);

	//void validteCredential(UserLoginInfo loginInfo, ILoginInputParam loginParam, LoginRecordTO login);
	
	//void validateLoginRecovery(ILoginRecoveryInput loginRecovery);
}
