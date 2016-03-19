package com.fpl.controller.admin.subscription;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.core.subsciption.fpl.IFplSubscriptionManager;
import com.fpl.core.subsciption.fpl.SubscriptionMasterPV;
import com.fpl.country.CountryUtils;
import com.fpl.login.UserLoginInfo;
import com.fpl.subscription.SubscriptionStatusType;
import com.fpl.subscription.TransactionStatusType;
import com.fpl.util.JsonUtil;

public class PayFpSubsriptionController extends AjaxBaseController {
	
	@Autowired
	private IFplSubscriptionManager manager;
	
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);

		
		final String paymentStatus = request.getParameter("paymentStatus");
		System.out.println(paymentStatus);
		
		final String financialPlannerSubJson = request.getParameter("financialPlannerSubscribeJson");
		System.out.println(financialPlannerSubJson);
		FPSubscriptionListPV fpSubscriptionListPV = JsonUtil.convertPojo(financialPlannerSubJson, FPSubscriptionListPV.class);
		final String fpSubscribeMasterJson = request.getParameter("fpSubscribeMasterJson");
		System.out.println(fpSubscribeMasterJson);
		final SubscriptionMasterPV masterPV= JsonUtil.convertPojo(fpSubscribeMasterJson, SubscriptionMasterPV.class);
		fpSubscriptionListPV.setSubscriptionMasterPV(masterPV);
		
		String action=request.getParameter("ACTION");
		
		
		
		
		
		if(action!=null && "GEN_TOKEN".equalsIgnoreCase(action))
		{
			fpSubscriptionListPV.setStatus(SubscriptionStatusType.IP.name());
			fpSubscriptionListPV.setPaymentStatus(TransactionStatusType.RD.name());
			fpSubscriptionListPV=manager.persist(fpSubscriptionListPV);
			if(fpSubscriptionListPV.getMode()!=null && "PAYPAL".equalsIgnoreCase(fpSubscriptionListPV.getMode()))
			{	
				fpSubscriptionListPV= manager.getPayPalCheckoutToken(fpSubscriptionListPV);
			}else
			{
				fpSubscriptionListPV= manager.getEWayCheckoutToken(fpSubscriptionListPV);
			}
			
			manager.persistToken(fpSubscriptionListPV);
			
			final Map jsonMap = new HashMap();
			jsonMap.put("TOKEN", fpSubscriptionListPV);
			resMap.put("res", JsonUtil.toJsonString(jsonMap));
			
		}else if(action!=null && ("GEN_SAVE".equalsIgnoreCase(action) || "SAVE_CHECK".equalsIgnoreCase(action)))
		{
			if("SAVE_CHECK".equalsIgnoreCase(action))
			{
				fpSubscriptionListPV.setStatus(SubscriptionStatusType.CI.name());
				fpSubscriptionListPV.setPaymentStatus(TransactionStatusType.CI.name());
				fpSubscriptionListPV.setValidationDate(new Date());
			
			}else if("GEN_SAVE".equalsIgnoreCase(action))
			{
				
				FPSubscriptionListPV savedListPV=null;
				if(fpSubscriptionListPV.getId()!=null)
				{
					savedListPV=manager.getFplSubscriptionById(Long.parseLong(fpSubscriptionListPV.getId()));
				}	
				if(savedListPV==null || (savedListPV.getStatus()!=null && !savedListPV.getStatus().equalsIgnoreCase(SubscriptionStatusType.CI.name())))
				{
					fpSubscriptionListPV.setStatus(SubscriptionStatusType.IP.name());
					fpSubscriptionListPV.setPaymentStatus(TransactionStatusType.RD.name());
				}	
			}
			
			fpSubscriptionListPV=manager.persist(fpSubscriptionListPV);
			
			if("SAVE_CHECK".equalsIgnoreCase(action))
			{
				manager.persistTransactionDetails(fpSubscriptionListPV);
			}
			final Map jsonMap = new HashMap();
			jsonMap.put("TOKEN", fpSubscriptionListPV);
			resMap.put("res", JsonUtil.toJsonString(jsonMap));
		}
		
		if(action!=null &&  "UPDATE_STATUS".equalsIgnoreCase(action))
		{
		
			final Map jsonMap = new HashMap();
			fpSubscriptionListPV.setPaymentStatus(paymentStatus);
			
		
			FPSubscriptionListPV savedListPV=null;
			if(fpSubscriptionListPV.getId()!=null)
			{
				savedListPV=manager.getFplSubscriptionById(Long.parseLong(fpSubscriptionListPV.getId()));
			}	
			if(savedListPV!=null)
			{
				FPSubscriptionListPV activeorIPListPV=manager.getFplSubscription(Long.parseLong(savedListPV.getFplId()));
				
				
					
					if(SubscriptionStatusType.AC.name().equalsIgnoreCase(fpSubscriptionListPV.getPaymentStatus()) || TransactionStatusType.PR.name().equalsIgnoreCase(fpSubscriptionListPV.getPaymentStatus()))
					{
						if(activeorIPListPV.getId()==null || (activeorIPListPV.getId()!=null && activeorIPListPV.getId()==savedListPV.getId()))
						{
							savedListPV.setStatus(SubscriptionStatusType.AC.name());
							savedListPV.setPaymentStatus(TransactionStatusType.PR.name());
							manager.logAdminFPStatusUpdate(savedListPV,fpSubscriptionListPV,loginInfo.getEmail());
							
							fpSubscriptionListPV=savedListPV;
							fpSubscriptionListPV=manager.persist(fpSubscriptionListPV);
							
						}else
						{
							jsonMap.put("MSG", "ALready an Active/In progress Sunscription exists, can not update status");
						}
					}else
					{
						if(SubscriptionStatusType.AI.name().equalsIgnoreCase(fpSubscriptionListPV.getPaymentStatus()))
						{
							savedListPV.setStatus(SubscriptionStatusType.AI.name());
							savedListPV.setPaymentStatus(SubscriptionStatusType.AI.name());
						}else
						{
							savedListPV.setStatus(SubscriptionStatusType.IN.name());
							savedListPV.setPaymentStatus(fpSubscriptionListPV.getPaymentStatus());
						}
						manager.logAdminFPStatusUpdate(savedListPV,fpSubscriptionListPV,loginInfo.getEmail());
						
						fpSubscriptionListPV=savedListPV;
						fpSubscriptionListPV=manager.persist(fpSubscriptionListPV);
					}	
					
					jsonMap.put("TOKEN", fpSubscriptionListPV);
					resMap.put("res", JsonUtil.toJsonString(jsonMap));		
			}	
		}
		
		
		return new ModelAndView("fplajax_login", resMap);
	}
	
}

