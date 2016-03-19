package com.fpl.persistence.login;

import com.fpl.login.entity.LoginSupport;
import com.fpl.persistence.support.IHibernateDAOSupport;

public interface ILoginSupportDAO extends IHibernateDAOSupport<LoginSupport> {
	
	LoginSupport getByLoginCredentialId(Long loginCredentialid);
}
