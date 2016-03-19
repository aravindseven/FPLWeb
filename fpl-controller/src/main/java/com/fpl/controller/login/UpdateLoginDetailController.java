package com.fpl.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.login.LoginCredentialServices;
import com.fpl.login.LoginRegisterParam;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.JsonUtil;

public class UpdateLoginDetailController extends AjaxBaseController {

	private LoginCredentialServices loginServices;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String registerInputJson = request.getParameter("settingsInput");
		System.out.println(registerInputJson);
		final LoginRegisterParam loginParam = JsonUtil.convertPojo(registerInputJson, LoginRegisterParam.class);
		final FPLServiceResponse serviceResponse = loginServices.updateLoginDetail(loginParam);
		if(serviceResponse.getStatus().equals("success")) {
			loginInfo.setAlternateMailId(loginParam.getAlternativemailId());
			loginInfo.setMobileNumber(loginParam.getMobilenumber());
			loginInfo.setSecreatAnswer(loginParam.getSecretAnswer());
		}
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_login", resMap);
	}

	/**
	 * @param loginServices the loginServices to set
	 */
	public final void setLoginServices(final LoginCredentialServices loginServices) {
		this.loginServices = loginServices;
	}
}
