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

public class UpdateUserStatusController extends AjaxBaseController{
	private LoginCredentialServices loginServices;
	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		loginInfo.setLoginCredentialid(Long.parseLong("6"));
		final String recoveryJson = request.getParameter("Status");
		System.out.println(recoveryJson);
		final FPLServiceResponse serviceResponse = loginServices.UpdateStatus(recoveryJson, loginInfo);
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_passwordRecovery", resMap);
	}
	public final void setLoginServices(final LoginCredentialServices loginServices) {
		this.loginServices = loginServices;
	}
}
