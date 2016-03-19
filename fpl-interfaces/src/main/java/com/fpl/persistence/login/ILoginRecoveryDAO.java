package com.fpl.persistence.login;

import com.fpl.login.entity.LoginRecovery;
import com.fpl.persistence.support.IHibernateDAOSupport;

public interface ILoginRecoveryDAO extends IHibernateDAOSupport<LoginRecovery> {
	
	LoginRecovery getLoginRecovery(Integer otp);
	LoginRecovery getLoginRecovery(Long loginCredentialId); 
}
