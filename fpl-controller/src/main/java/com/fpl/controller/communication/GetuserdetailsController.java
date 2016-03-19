package com.fpl.controller.communication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.communication.ICommunicationManager;
import com.fpl.core.communication.UserInfo;
import com.fpl.core.communication.UserSearchParam;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.UserType;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.profile.customer.Customer;
import com.fpl.util.DateUtil;
import com.fpl.util.JsonUtil;

public class GetuserdetailsController extends AjaxBaseController {
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
		final Map map = new HashMap();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		Date now = new Date();
		String msgdate=sdf.format(now);
		try{
			map.put("Status", "Success");
			map.put("Current_Time",msgdate);
			map.put("first_name", loginInfo.getFirstname());
			map.put("last_name", loginInfo.getLastname());
			map.put("id",loginInfo.getLoginCredentialid());
			map.put("user_type", loginInfo.getUserTypeId());
			
			
			
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
