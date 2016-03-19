package com.fpl.controller.profile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.communication.UserInfo;
import com.fpl.core.policy.IPolicyFinder;
import com.fpl.login.UserLoginInfo;
import com.fpl.policy.PolicySearchParam;
import com.fpl.util.JsonUtil;

public class GetMyFplOrCustomerController extends AjaxBaseController {

	private IPolicyFinder policyFinder;

	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request,final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final PolicySearchParam searchParam = new PolicySearchParam();
		searchParam.setLoginId(loginInfo.getLoginCredentialid());
		searchParam.setUserType(loginInfo.getUserType());
		final Map map = new HashMap();
		try {
			final Collection<UserInfo> userInfos = policyFinder.getMyUser(searchParam);
			map.put("Status", "Success");
			map.put("Result", userInfos);
		} catch (final Exception e) {
			map.put("Status", "Failure");
			map.put("Result", e.getMessage());
			e.printStackTrace();
		}
		resMap.put("res", JsonUtil.toJsonString(map));
		return new ModelAndView("fplajax_domain", resMap);
	}
	
	/**
	 * @param policyFinder the policyFinder to set
	 */
	public void setPolicyFinder(final IPolicyFinder policyFinder) {
		this.policyFinder = policyFinder;
	}
}
