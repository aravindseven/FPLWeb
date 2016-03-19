package com.fpl.controller.profile;

import java.util.Collection;
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
import com.fpl.domain.Domain;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.UserType;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.profile.admin.AdminProfilePV;
import com.fpl.profile.customer.CustomerPV;
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.services.profile.IProfileFetcher;
import com.fpl.subscription.SubscriptionStatusType;
import com.fpl.util.JsonUtil;
import com.fpl.util.StringUtil;

public class InitiAjaxProfileController extends AjaxBaseController {

	private Map<String, IProfileFetcher> fetcherMap;
	private IDomainDAO domainDAO;
	@Autowired
	private IFplSubscriptionManager manager;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		final ProfileControllerSupport support = new ProfileControllerSupport();
		if(loginInfo.getUserType().equals(UserType.CUST_CORPORATE) || loginInfo.getUserType().equals(UserType.CUST_INDIVIDUAL)) {
			final IProfileFetcher  fetcher = fetcherMap.get(loginInfo.getUserType().getUser());
			CustomerPV customerPV = fetcher.getProfilePageView(loginInfo.getLoginCredentialid());
			if(customerPV == null) {
				customerPV = support.getCustomerPV(loginInfo);
				customerPV.setCustomerIdText("CUS"+String.format("%010d", fetcher.getMaxId()+1));
				customerPV.setRegistrationDate(new Date());

			}else
			{
				customerPV.setCustomerIdText("CUS"+String.format("%010d", Integer.parseInt(customerPV.getCustomerId())));
			}	
			final Map jsonMap = new HashMap();
			jsonMap.put("UserLoginInfo", loginInfo);
			jsonMap.put("CustomerPV", customerPV);
			resMap.put("res", JsonUtil.toJsonString(jsonMap));
		} else if(loginInfo.getUserType().equals(UserType.FP_CORPORATE) || loginInfo.getUserType().equals(UserType.FP_INDIVIDUAL)) {
			final Collection<Domain> domains = domainDAO.getAllEntities();
			final IProfileFetcher  fetcher = fetcherMap.get(loginInfo.getUserType().getUser());
			FinancialPlannerPV plannerPV = fetcher.getProfilePageView(loginInfo.getLoginCredentialid());
			
			if(plannerPV == null) {
				plannerPV = support.getFinancialPlannerPV(loginInfo);
				if(loginInfo.getUserType().equals(UserType.FP_CORPORATE)) {
					plannerPV.setFplIdText("FPC"+String.format("%010d", fetcher.getMaxId()+1));
				}else if(loginInfo.getUserType().equals(UserType.FP_INDIVIDUAL)) {
					plannerPV.setFplIdText("FPI"+String.format("%010d", fetcher.getMaxId()+1));
				}
				plannerPV.setCreationDate(new Date());
				
			}else
			{
				if(loginInfo.getUserType().equals(UserType.FP_CORPORATE)) {
					plannerPV.setFplIdText("FPC"+String.format("%010d", Integer.parseInt(plannerPV.getFplId())));
				}else if(loginInfo.getUserType().equals(UserType.FP_INDIVIDUAL)) {
					plannerPV.setFplIdText("FPI"+String.format("%010d", Integer.parseInt(plannerPV.getFplId())));
				}
			
			}
			
			FPSubscriptionListPV subscriptionListPVs=null;
			if(StringUtil.isNotEmpty(plannerPV.getFplId()))
			{
				subscriptionListPVs=manager.getFplSubscription(Long.parseLong(plannerPV.getFplId()));
			}	
			if(subscriptionListPVs==null  || StringUtil.isEmpty(subscriptionListPVs.getFplId()))
			{
				subscriptionListPVs=new FPSubscriptionListPV();
				subscriptionListPVs.setStatus(SubscriptionStatusType.IP.name());
				subscriptionListPVs.setValidationDate(new Date());
				
			}else if(subscriptionListPVs.getValidationDate()==null)
			{
				subscriptionListPVs.setValidationDate(new Date());
			}
			plannerPV.setSubscriptionListPVs(subscriptionListPVs);
			
			plannerPV.setDomains(domains);
			final Map jsonMap = new HashMap();
			jsonMap.put("UserLoginInfo", loginInfo);
			jsonMap.put("FinancialPlannerPV", plannerPV);
			resMap.put("res", JsonUtil.toJsonString(jsonMap));
		} else if(loginInfo.getUserType().equals(UserType.FP_ADMIN)) {
			final IProfileFetcher  fetcher = fetcherMap.get(loginInfo.getUserType().getUser());
			AdminProfilePV adminProfilePV = fetcher.getProfilePageView(loginInfo.getLoginCredentialid());
			if(adminProfilePV == null) {
				adminProfilePV = support.getAdminParam(loginInfo);
			}
			final Map jsonMap = new HashMap();
			jsonMap.put("UserLoginInfo", loginInfo);
			jsonMap.put("AdminProfilePV", adminProfilePV);
			resMap.put("res", JsonUtil.toJsonString(jsonMap));
		}
		return new ModelAndView("fplajax_login", resMap);
	}

	/**
	 * @param fetcherMap the fetcherMap to set
	 */
	public void setFetcherMap(final Map<String, IProfileFetcher> fetcherMap) {
		this.fetcherMap = fetcherMap;
	}

	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}
}
