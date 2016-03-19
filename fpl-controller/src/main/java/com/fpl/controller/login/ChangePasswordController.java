package com.fpl.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.login.LoginCredentialServices;
import com.fpl.login.PasswordResetParam;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.JsonUtil;

public class ChangePasswordController extends AjaxBaseController {

	private LoginCredentialServices loginServices;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final PasswordResetParam param = new PasswordResetParam();
		param.setEmail(loginInfo.getEmail());
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String newPassword = request.getParameter("newPassword");
		System.out.println(newPassword);
		param.setNewPassword(newPassword);
		final FPLServiceResponse serviceResponse = loginServices.changePassword(param);
		if(serviceResponse.getStatus().equals("success")) {
			loginInfo.setPassword(newPassword);
		}
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_passwordRecovery", resMap);
	}

	/**
	 * @param loginServices the loginServices to set
	 */
	public void setLoginServices(final LoginCredentialServices loginServices) {
		this.loginServices = loginServices;
	}
}
