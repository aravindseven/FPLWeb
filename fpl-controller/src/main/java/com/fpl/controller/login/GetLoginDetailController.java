package com.fpl.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.login.LoginRegisterParam;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.JsonUtil;

public class GetLoginDetailController extends AjaxBaseController {

	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final Map<String, String> resMap =  new HashMap<String, String>();
		final LoginRegisterParam loginParam  = new LoginRegisterParam();
		loginParam.setAlternativemailId(loginInfo.getAlternateMailId());
		loginParam.setMobilenumber(loginInfo.getMobileNumber());
		loginParam.setPrimaryEmailId(loginInfo.getEmail());
		loginParam.setSecretAnswer(loginInfo.getSecreatAnswer());
		loginParam.setUserType(loginInfo.getUserType().getUser());
		loginParam.setAlternateMailVerified(loginInfo.isSecEmailVerified());
		resMap.put("res", JsonUtil.toJsonString(loginParam));
		return new ModelAndView("fplajax_login", resMap);
	}
}
