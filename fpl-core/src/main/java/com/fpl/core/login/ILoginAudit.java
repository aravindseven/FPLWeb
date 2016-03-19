package com.fpl.core.login;

import com.fpl.login.LoginRecordTO;

public interface ILoginAudit {
	boolean Loginlog(LoginRecordTO lr);
}
