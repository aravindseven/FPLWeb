package com.fpl.login;

import com.fpl.core.login.ILoginAudit;

public class LoginAudit {
	private ILoginAudit Auditprocess;
	public boolean Loginlog(LoginRecordTO lr) {
		// TODO Auto-generated method stub
		try {
			final boolean loglogin = Auditprocess.Loginlog(lr);
			System.out.println("entered into login services");
			
		} catch (final Exception e) {
			e.printStackTrace(); 
			
		}
		
		return true;
	}
	
	
	public void setLoginAuditprocess(final ILoginAudit Auditprocess) {
		this.Auditprocess = Auditprocess;
	}
}
