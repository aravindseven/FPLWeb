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
import com.fpl.util.JsonUtil;

public class LoginRegistrationController extends AjaxBaseController {
	
	private LoginCredentialServices loginServices;
	boolean valid=true;
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String registerInputJson = request.getParameter("registerInput");
		System.out.println(registerInputJson);
		final LoginRegisterParam loginParam = JsonUtil.convertPojo(registerInputJson, LoginRegisterParam.class);
		
		if(loginParam.getUserType()==null)
		{
			loginParam.setUserType("cust_individual");
		}
		
		/*if (valid) {
			 
	          String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
	 
	          System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
	 
	          // Verify CAPTCHA.
	          valid = VerifyUtils.verify(gRecaptchaResponse);
	          if (!valid) {
	              errorString = "Captcha invalid!";
	          }
	      }*/
		//Temp  
		loginParam.setPassword("Welcome1");
		loginParam.setCountry("Singapore");
		loginParam.setSecretQuestion("What is your Mother's Maiden Name?");
		//loginParam.setUserType("cust_individual");
		//final FPLServiceResponse serviceResponse = loginServices.registerLogin(loginParam);
		final FPLServiceResponse serviceResponse = loginServices.registerLogin(loginParam, request); 
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_login", resMap);
	};

	/**
	 * @param loginServices the loginServices to set
	 */
	public final void setLoginServices(final LoginCredentialServices loginServices) {
		this.loginServices = loginServices;
	}
}
