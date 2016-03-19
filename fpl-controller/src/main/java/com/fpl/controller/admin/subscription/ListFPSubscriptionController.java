package com.fpl.controller.admin.subscription;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.profile.financialplanner.IFinancialPlannerFinder;
import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.core.subsciption.fpl.IFplSubscriptionManager;
import com.fpl.login.UserLoginInfo;
import com.fpl.profile.admin.SearchProfilePV;
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.util.JsonUtil;
import com.fpl.util.StringUtil;

public class ListFPSubscriptionController extends AjaxBaseController {
	
	@Autowired
	private IFplSubscriptionManager manager;
	
	@Autowired
	@Qualifier("fpl.profile.fplFinder")
	private IFinancialPlannerFinder financialPlannerFinder;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		
		
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		String action=request.getParameter("action");
		Collection<FPSubscriptionListPV> fpSubscriptionListPVs=null;
		
		
		if(StringUtil.isNotEmpty(action) && "FETCHFPLBYID".equalsIgnoreCase(action))
		{
			SearchProfilePV searchProfilePV=new SearchProfilePV();
			FinancialPlannerPV plannerPV=financialPlannerFinder.getFinancialPlannerByLoginId(loginInfo.getLoginCredentialid());
			searchProfilePV.setFplId(Long.parseLong(plannerPV.getFplId()));
			fpSubscriptionListPVs=manager.getAllFplSubscription(searchProfilePV);
		}else
		{
		
		final String searchProfileJson = request.getParameter("searchProfileJson");
		System.out.println(searchProfileJson);
		final SearchProfilePV searchProfilePV = JsonUtil.convertPojo(searchProfileJson, SearchProfilePV.class);

		   fpSubscriptionListPVs=manager.getAllFplSubscription(searchProfilePV);
		
		}	
			final Map jsonMap = new HashMap();
			jsonMap.put("UserLoginInfo", loginInfo);
			jsonMap.put("FPSubscriptionListPVs", fpSubscriptionListPVs);
			
			resMap.put("res",  JsonUtil.toJsonString(jsonMap));	
		
		return new ModelAndView("fplajax_GetAdvertisementType",resMap);
	}
}
