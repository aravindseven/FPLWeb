package com.fpl.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.login.LoginCredentialServices;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.JsonUtil;

public class InitSecEmailVerficationController extends AjaxBaseController {

	private LoginCredentialServices credentialServices;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final Map<String, String> resMap =  new HashMap<String, String>();
		final FPLServiceResponse serviceResponse = credentialServices.SendSecEmailVerfication(loginInfo.getLoginCredentialid(), request);
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_passwordRecovery", resMap);
	}
	
	/**
	 * @param credentialServices the credentialServices to set
	 */
	public void setCredentialServices(final LoginCredentialServices credentialServices) {
		this.credentialServices = credentialServices;
	}
}
