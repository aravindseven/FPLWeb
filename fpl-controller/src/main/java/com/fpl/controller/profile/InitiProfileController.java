package com.fpl.controller.profile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.domain.Domain;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.UserType;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.profile.admin.AdminProfilePV;
import com.fpl.profile.customer.CustomerPV;
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.services.profile.IProfileFetcher;
import com.fpl.util.JsonUtil;

public class InitiProfileController extends FPLAbstractController {

	private Map<String, IProfileFetcher> fetcherMap;
	private IDomainDAO domainDAO;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView(FPLPageName.PROFILE.getPageName());
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		final ProfileControllerSupport support = new ProfileControllerSupport();
		if(loginInfo.getUserType().equals(UserType.CUST_CORPORATE) || loginInfo.getUserType().equals(UserType.CUST_INDIVIDUAL)) {
			final IProfileFetcher  fetcher = fetcherMap.get(loginInfo.getUserType().getUser());
			CustomerPV customerPV = fetcher.getProfilePageView(loginInfo.getLoginCredentialid());
			if(customerPV == null) {
				customerPV = support.getCustomerPV(loginInfo);
			}
			System.out.println("============================");
			final Map jsonMap = new HashMap();
			jsonMap.put("UserLoginInfo", loginInfo);
			jsonMap.put("CustomerPV", customerPV);
			System.out.println(JsonUtil.toJsonString(jsonMap));
			System.out.println("============================");
			model.addObject("customerParam", customerPV);
		} else if(loginInfo.getUserType().equals(UserType.FP_CORPORATE) || loginInfo.getUserType().equals(UserType.FP_INDIVIDUAL)) {
			final Collection<Domain> domains = domainDAO.getAllEntities();
			final IProfileFetcher  fetcher = fetcherMap.get(loginInfo.getUserType().getUser());
			FinancialPlannerPV plannerPV = fetcher.getProfilePageView(loginInfo.getLoginCredentialid());
			if(plannerPV == null) {
				plannerPV = support.getFinancialPlannerPV(loginInfo);
			}
			plannerPV.setDomains(domains);
			System.out.println("============================");
			final Map jsonMap = new HashMap();
			jsonMap.put("UserLoginInfo", loginInfo);
			jsonMap.put("FinancialPlannerPV", plannerPV);
			System.out.println(JsonUtil.toJsonString(jsonMap));
			System.out.println("============================");
			model.addObject("financialPlannerParam", plannerPV);
		} else if(loginInfo.getUserType().equals(UserType.FP_ADMIN)) {
			final IProfileFetcher  fetcher = fetcherMap.get(loginInfo.getUserType().getUser());
			AdminProfilePV adminProfilePV = fetcher.getProfilePageView(loginInfo.getLoginCredentialid());
			if(adminProfilePV == null) {
				adminProfilePV = support.getAdminParam(loginInfo);
			}
			model.addObject("adminParam", adminProfilePV);
		}
		return model;
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
