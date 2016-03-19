package com.fpl.controller.communication;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.communication.ICommunicationManager;
import com.fpl.core.communication.MessageParam;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.login.UserLoginInfo;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.profile.customer.Customer;
import com.fpl.util.DateUtil;
import com.fpl.util.JsonUtil;

public class GetSearchBoxMessagesController extends AjaxBaseController {
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	@Autowired
	private IFinancialPlannerDAO financialPlannerDAO;
	@Autowired
	private ICustomerDAO customerDAO;
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		
		System.out.println("User Id"+loginInfo.getLoginCredentialid());
		System.out.println(loginInfo.getUserType().getUser());
		final Map map = new HashMap();
		try {
			System.out.println(request.getParameter("filter"));
			System.out.println(request.getParameter("search"));
			System.out.println(request.getParameter("box"));
			//Object stats = communicationManager.getsearchmail(loginInfo.getLoginCredentialid(),request.getParameter("search"),request.getParameter("filter"),request.getParameter("box"), map);
			String UserEmail = "";
			String UserName = "";
			Long UserId = 0l;
			if(loginInfo.getUserType().getUser().startsWith("cust")) {
				final Customer customer =  customerDAO.getCustomerLoginId(loginInfo.getLoginCredentialid());
				if(customer != null) {
					UserEmail = customer.getPersonalData().getEmail();
					UserId = customer.getLoginCredentialId();
					UserName = customer.getPersonalData().getName();
				} 
			} else {
				final FinancialPlanner financialPlanner = financialPlannerDAO.getFinancialPlannerByLoginId(loginInfo.getLoginCredentialid());
				if(financialPlanner != null) {
					UserEmail = financialPlanner.getPersonalData().getEmail();
					UserId =  financialPlanner.getLoginCredentialId();
					UserName = financialPlanner.getPersonalData().getName();
				} 
			}
			map.put("UserEmail", UserEmail);
			map.put("UserName",UserName);
			map.put("UserId", UserId);
			map.put("Status", "Success");
			map.put("Current_Time",DateUtil.getDate());
			//map.put("Output", stats);
			
		} catch (final Exception e) {
			map.put("Status", "Failure");
			map.put("Output", e.getMessage());
			e.printStackTrace();
		}
		resMap.put("res", JsonUtil.toJsonString(map));
		return new ModelAndView("fplajax_domain", resMap);
	}
	/**
	 * @param policyPersister the policyPersister to set
	 */
	public void setCommunicationManager(final ICommunicationManager communicationManager) {
		this.communicationManager = communicationManager;
	}
}
