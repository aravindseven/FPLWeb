package com.fpl.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.login.LoginCredentialServices;
import com.fpl.login.LoginRecoveryInput;
import com.fpl.login.UserRecoveryInput;
import com.fpl.util.JsonUtil;

public class PasswordRecoveryController extends AjaxBaseController {
	
	private LoginCredentialServices loginServices;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String recoveryJson = request.getParameter("recoveryInput");
		System.out.println(recoveryJson);
		final UserRecoveryInput loginParam = JsonUtil.convertPojo(recoveryJson, UserRecoveryInput.class);
		System.out.println(""+loginParam.getFirstname()+loginParam.getLastname()+loginParam.getAltEmail()+loginParam.getSecretQuestion());

		final FPLServiceResponse serviceResponse = loginServices.recoverPassword(loginParam, request);
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_passwordRecovery", resMap);
	};
	
	/**
	 * @param loginServices the loginServices to set
	 */
	public final void setLoginServices(final LoginCredentialServices loginServices) {
		this.loginServices = loginServices;
	}
}
