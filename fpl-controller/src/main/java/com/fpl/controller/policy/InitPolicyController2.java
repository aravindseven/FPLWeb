package com.fpl.controller.policy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.core.policy.IPolicyFinder;
import com.fpl.core.policy.PolicyListPageView;
import com.fpl.login.UserLoginInfo;
import com.fpl.policy.PolicySearchParam;
import com.fpl.util.JsonUtil;

public class InitPolicyController2 extends AjaxBaseController  {
	//FPLAbstractController
	private IPolicyFinder policyFinder;
	
	/**
	 * @param policyFinder the policyFinder to set
	 */
	public void setPolicyFinder(final IPolicyFinder policyFinder) {
		this.policyFinder = policyFinder;
	}

	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map map = new HashMap();
		final ModelAndView model = new ModelAndView(FPLPageName.POLICY.getPageName());
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		final PolicySearchParam searchParam = new PolicySearchParam();
		searchParam.setLoginId(loginInfo.getLoginCredentialid());
		searchParam.setUserType(loginInfo.getUserType());
		final Collection<PolicyListPageView> policyList = policyFinder.getPolicyList(searchParam);
		map.put("policydetail",policyList);
		resMap.put("res", JsonUtil.toJsonString(map));
		model.addObject("PolicyList", policyList);
		model.addObject("PolicyDisplayCriteria", "Search");		
		return new ModelAndView("fplajax_domain", resMap);
		
	}
}