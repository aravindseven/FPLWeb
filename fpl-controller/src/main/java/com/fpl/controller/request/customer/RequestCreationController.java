package com.fpl.controller.request.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.controller.support.UserSessionKey;
import com.fpl.core.financialplanner.PartialUserView;
import com.fpl.core.request.customer.NewRequestPV;
import com.fpl.core.request.financialplanner.IRequestFPLFinder;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.JsonUtil;

public class RequestCreationController extends AjaxBaseController {
	
	private IRequestFPLFinder fplFinder;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final String requestJson = request.getParameter("requestJson");
		System.out.println(requestJson);
		final NewRequestPV newRequest = JsonUtil.convertPojo(requestJson, NewRequestPV.class);
		newRequest.setLoginId(loginInfo.getLoginCredentialid()+"");
		put(request, UserSessionKey.NEW_REQUEST_PV, newRequest);
		final Collection<PartialUserView> partialFPLViews =  fplFinder.getFPListByLocation(newRequest);
		resMap.put("res", JsonUtil.toJsonString(partialFPLViews));
		return new ModelAndView("fplajax_login", resMap);
	}

	/**
	 * @param fplFinder the fplFinder to set
	 */
	public void setFplFinder(final IRequestFPLFinder fplFinder) {
		this.fplFinder = fplFinder;
	}
}