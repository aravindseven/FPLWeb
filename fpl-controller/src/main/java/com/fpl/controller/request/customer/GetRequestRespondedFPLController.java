package com.fpl.controller.request.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.request.customer.CustomerDashboardPV;
import com.fpl.core.request.financialplanner.FPLDashboardPV;
import com.fpl.core.request.financialplanner.IFPLRequestManager;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.UserType;
import com.fpl.util.JsonUtil;

public class GetRequestRespondedFPLController extends AjaxBaseController {
	
	private IFPLRequestManager requestManager;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);

		if(loginInfo.getUserType().equals(UserType.CUST_CORPORATE) || loginInfo.getUserType().equals(UserType.CUST_INDIVIDUAL)) {

			List<CustomerDashboardPV> list=requestManager.fetchCustomerDashboardDetails(loginInfo.getLoginCredentialid());

			resMap.put("res", JsonUtil.toJsonString(list));

		} else if(loginInfo.getUserType().equals(UserType.FP_CORPORATE) || loginInfo.getUserType().equals(UserType.FP_INDIVIDUAL)) {

			List<FPLDashboardPV> list=requestManager.fetchFPLDashboardDetails(loginInfo.getLoginCredentialid());

			resMap.put("res", JsonUtil.toJsonString(list));

		}
		
		return new ModelAndView("fplajax_domain", resMap);
	}

	/**
	 * @param requestManager the requestManager to set
	 */
	public void setRequestManager(final IFPLRequestManager requestManager) {
		this.requestManager = requestManager;
	}
}
