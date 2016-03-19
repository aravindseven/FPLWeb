package com.fpl.persistence.login;

import com.fpl.login.ILoginInputParam;
import com.fpl.login.ILoginRecoveryInput;
import com.fpl.login.IUserRecoverInput;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.LoginCredential;
import com.fpl.persistence.support.IHibernateDAOSupport;

public interface ILoginCredentialDAO extends IHibernateDAOSupport<LoginCredential> {

	UserLoginInfo getLoginInfo(ILoginInputParam loginInputParam);
	
	//LoginCredential getLoginCredential(final ILoginRecoveryInput loginRecovery);
	
	LoginCredential getLoginCredentialByMail(String userName); 
	LoginCredential getLoginCredential(final IUserRecoverInput loginRecovery);

	LoginCredential getLoginCredentialbyAltemail(IUserRecoverInput loginRecovery);
}
