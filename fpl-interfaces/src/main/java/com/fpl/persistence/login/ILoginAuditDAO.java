package com.fpl.persistence.login;

import java.util.ArrayList;
import java.util.HashMap;

import com.fpl.login.LoginRecordTO;

public interface ILoginAuditDAO  {
	boolean Loginlog(LoginRecordTO lr);
	boolean sessionlog(long userid,String sessionid);
	boolean sessionlog(long userid,String sessionid,int attempts);
	HashMap<String, Integer> getSessionlog(long userid, String sessionid);
	
}
