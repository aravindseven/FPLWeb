package com.fpl.controller.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.login.FPLLoginOutput;
import com.fpl.login.LoginAudit;
import com.fpl.login.LoginCredentialServices;
import com.fpl.login.LoginInputParam;
import com.fpl.login.LoginRecordTO;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.User;
import com.fpl.util.JsonUtil;

import net.sf.uadetector.service.UADetectorServiceFactory;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgent;
import net.sf.uadetector.UserAgentStringParser;




public class LoginFplController extends AjaxBaseController {
	
	private LoginAudit auditServices;
	private LoginCredentialServices loginServices;
	private LoginRecordTO loginrecord;
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		System.out.println("login details ");
		System.out.println(request.getParameter("loginInput"));
		final String loginInputJson = request.getParameter("loginInput");
		System.out.println(request.getCookies());
		System.out.println(loginInputJson);
		
		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
		ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
		System.out.println("agent"+agent.getName()+"os"+agent.getOperatingSystem().getName()+"test"+agent.getTypeName()+",,,"+agent.getDeviceCategory());
		final LoginInputParam loginParam = JsonUtil.convertPojo(loginInputJson, LoginInputParam.class);
		System.out.println(agent.getTypeName());
		System.out.println("seesionid from ios " +request.getSession().getId());
		loginParam.setSessionId(request.getSession().getId());
		loginParam.setDeviceType(agent.getTypeName());
		final Map<String, Object> map = loginServices.getLoginInfo(loginParam);
		final FPLLoginOutput loginOutput = (FPLLoginOutput) map.get("FPLLoginOutput");
		final UserLoginInfo loginInfo = (UserLoginInfo) map.get("UserLoginInfo");
		if(loginInfo != null) {
			put(request, USER_LOGIN_KEY, loginInfo);
		}
		/*if(!loginInfo.getLoginCredentialid().equals(Long.parseLong("6"))){
         remove("7CBFA532E8C4C2BE653B77A503280DEF",USER_LOGIN_KEY);
			System.out.println("called1S");
		}*/
		
		 Date dt=new Date();
		 String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		   if (ipAddress == null) {  
			   ipAddress = request.getRemoteAddr();  
		   }
		   
		   if(loginOutput.isSuccess()){
			   System.out.println("loginsucesssessionkeycheck");
			   loginrecord.setLogincrediantialid(loginInfo.getLoginCredentialid());
			   loginrecord.setChatstatus("Online");
			   loginrecord.setResultreason("Success");
			   loginrecord.setLoginresult(""+loginOutput.isSuccess());
			   loginrecord.setLogindate(dt.toString());
			   
		   }else{
			   if(loginInfo != null){
				   loginrecord.setLogincrediantialid(loginInfo.getLoginCredentialid());
			   }else{
				   loginrecord.setLogincrediantialid(Long.parseLong("0"));
			   }
			   loginrecord.setChatstatus("null");
			   loginrecord.setResultreason(loginOutput.getErrorMsg());
			   loginrecord.setLoginresult(""+loginOutput.isSuccess());
			   loginrecord.setLogindate(dt.toString());
		   }
			loginrecord.setSessionId(request.getSession().getId());
			loginrecord.setUserId(loginParam.getPrimaryEmailId());
			loginrecord.setDevicetype("Monitor");
			loginrecord.setDeviceid("1234");
			loginrecord.setIpaddress(ipAddress);
			loginrecord.setLogoutdate("null");
			
		
			final boolean loginput = auditServices.Loginlog(loginrecord);
			loginOutput.setUserLoginInfo(null);
		resMap.put("res", JsonUtil.toJsonString(loginOutput));
		return new ModelAndView("fplajax_login", resMap);
	};
 
	/**
	 * @param loginServices the loginServices to set
	 */
	public final void setLoginServices(final LoginCredentialServices loginServices) {
		this.loginServices = loginServices;

	}
	public final void setloginrecord(final LoginRecordTO loginrecord) {
		this.loginrecord = loginrecord;
	}
	public final void setAuditServices(final LoginAudit AuditServices) {
		this.auditServices = AuditServices;
	}
}


