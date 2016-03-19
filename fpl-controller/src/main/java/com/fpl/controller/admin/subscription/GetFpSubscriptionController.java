package com.fpl.controller.admin.subscription;

import java.util.Collection;
import java.util.Date;
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
import com.fpl.core.subsciption.fpl.SubscriptionMasterPV;
import com.fpl.login.UserLoginInfo;
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.util.JsonUtil;
import com.fpl.util.StringUtil;

public class GetFpSubscriptionController extends AjaxBaseController {
	
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
		String subId=request.getParameter("subId");
		FinancialPlannerPV plannerPV=null;
		FPSubscriptionListPV fpSubscriptionListPV=null;
		
		if(StringUtil.isNotEmpty(action) && "VIEW".equalsIgnoreCase(action))
		{
			fpSubscriptionListPV=manager.getFplSubscriptionById(Long.parseLong(subId));
			plannerPV=financialPlannerFinder.getFinancialPlannerById(Long.parseLong(fpSubscriptionListPV.getFplId()));
			
		}else
		{
		
			plannerPV=financialPlannerFinder.getFinancialPlannerByLoginId(loginInfo.getLoginCredentialid());
			fpSubscriptionListPV=manager.getFplSubscription(Long.parseLong(plannerPV.getFplId()));
		}	
			
			Collection<SubscriptionMasterPV> subscriptionMasterPVs= manager.getSubscriptionDetails();
			
			if(fpSubscriptionListPV.getId()==null || "".equalsIgnoreCase(fpSubscriptionListPV.getId()))
			{
				
				
				fpSubscriptionListPV.setIdText("SUB"+String.format("%010d", manager.getSubscriptionMaxId().intValue()));
				fpSubscriptionListPV.setValidationDate(new Date());
			
				fpSubscriptionListPV.setFplId(plannerPV.getFplId());
				fpSubscriptionListPV.setContactName(plannerPV.getPersonalDataPV().getFirstName());
				fpSubscriptionListPV.setName(loginInfo.getEmail());
			}else
			{
					
				if(fpSubscriptionListPV.getValidationDate()==null)
				{
					fpSubscriptionListPV.setValidationDate(new Date());
				}
			}
			
			
				fpSubscriptionListPV.setMasterPVs(subscriptionMasterPVs);
			
			
			plannerPV.setSubscriptionListPVs(fpSubscriptionListPV);
			
			final Map jsonMap = new HashMap();
			jsonMap.put("UserLoginInfo", loginInfo);
			jsonMap.put("FinancialPlannerPV", plannerPV);
			jsonMap.put("SubscriptionMasterPVs", subscriptionMasterPVs);
			
			resMap.put("res",  JsonUtil.toJsonString(jsonMap));	
		
		return new ModelAndView("fplajax_GetAdvertisementType",resMap);
	}
}
