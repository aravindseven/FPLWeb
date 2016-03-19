package com.fpl.controller.communication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.communication.ICommunicationManager;
import com.fpl.core.communication.UserInfo;
import com.fpl.core.communication.UserSearchParam;
import com.fpl.core.communication.UserUtil;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.UserType;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.profile.customer.Customer;
import com.fpl.util.JsonUtil;

public class ChatroomsController extends AjaxBaseController {
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	@Autowired
	private IFinancialPlannerDAO financialPlannerDAO;
	@Autowired
	private ICustomerDAO customerDAO;
	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		UserUtil.loginInfo=loginInfo;
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map map = new HashMap();
		//Object stats = null ;
		ArrayList<String> chatrooms = new ArrayList<String>();
		String user=loginInfo.getLoginCredentialid().toString();
				//System.out.println("user"+user);
				System.out.println("user"+user);
		try {
			final UserSearchParam searchParam = new UserSearchParam();
			if(loginInfo.getUserType().equals(UserType.CUST_CORPORATE) || loginInfo.getUserType().equals(UserType.CUST_INDIVIDUAL)) {
				searchParam.setUserTypeCollection(Arrays.asList(UserType.FP_CORPORATE, UserType.FP_INDIVIDUAL));
			} else if(loginInfo.getUserType().equals(UserType.FP_CORPORATE) || loginInfo.getUserType().equals(UserType.FP_INDIVIDUAL)) {
				searchParam.setUserTypeCollection(Arrays.asList(UserType.CUST_CORPORATE, UserType.CUST_INDIVIDUAL));
			}
			
			final Collection<UserInfo> userInfos = communicationManager.getUserList(searchParam);
			Collection<Object> stats = (Collection<Object>) communicationManager.getroomswithmessages(loginInfo.getLoginCredentialid());
			String UserName = "";
			if(loginInfo.getUserType().getUser().startsWith("cust")) {
				final Customer customer =  customerDAO.getCustomerLoginId(loginInfo.getLoginCredentialid());
				
				if(customer != null) {	
					UserName = customer.getPersonalData().getName();
				} 
			} else {
				final FinancialPlanner financialPlanner = financialPlannerDAO.getFinancialPlannerByLoginId(loginInfo.getLoginCredentialid());
				if(financialPlanner != null) {
					UserName = financialPlanner.getPersonalData().getName();
				} 
			}
			
			map.put("Status", "Success");
			map.put("Result", stats);
			map.put("Online", userInfos);
			map.put("Authorname", UserName);
			map.put("Output", stats);
			map.put("Author", user);
		} catch (final Exception e) {
			map.put("Status", "Failure");
			map.put("Result", e.getMessage());
			e.printStackTrace();
		}
		resMap.put("res", JsonUtil.toJsonString(map));
		return new ModelAndView("fplajax_domain", resMap);
	}
	}