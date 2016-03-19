package com.fpl.core.login;

import com.fpl.login.LoginRecordTO;
import com.fpl.persistence.login.ILoginAuditDAO;


public class LoginAuditImpl implements ILoginAudit{
	
	private ILoginAuditDAO loginAuditDAO;
	
	@Override
	public boolean Loginlog(LoginRecordTO lr) {
		// TODO Auto-generated method stub
		
		System.out.println("loginprocessIMPL");
		
		
		loginAuditDAO.Loginlog(lr);
		return false;
	}
	public final void setLoginAuditDAO(final ILoginAuditDAO loginAuditDAO) {
		this.loginAuditDAO = loginAuditDAO;
	}
}
