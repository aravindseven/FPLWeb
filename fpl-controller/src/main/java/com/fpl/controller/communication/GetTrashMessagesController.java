package com.fpl.controller.communication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class GetTrashMessagesController extends AjaxBaseController {
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
		final Map map = new HashMap();
		final ArrayList rec_id = new ArrayList<String>();
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
		
		try {
			String search = request.getParameter("search");
			String filter=request.getParameter("filter");
			System.out.println(filter);
			System.out.println(request.getParameter("pageno"));
			Object stats = null ;
			if(search==null || search.isEmpty())
			{
				stats = communicationManager.getTrashbox(loginInfo.getLoginCredentialid(),Integer.parseInt(request.getParameter("pageno")),filter);
				map.put("Output", stats);
			}
			else
			{
				filter = "all";
				final UserSearchParam searchParam = new UserSearchParam();
				if(loginInfo.getUserType().equals(UserType.CUST_CORPORATE) || loginInfo.getUserType().equals(UserType.CUST_INDIVIDUAL)) {
					searchParam.setUserTypeCollection(Arrays.asList(UserType.FP_CORPORATE, UserType.FP_INDIVIDUAL));
				} else if(loginInfo.getUserType().equals(UserType.FP_CORPORATE) || loginInfo.getUserType().equals(UserType.FP_INDIVIDUAL)) {
					searchParam.setUserTypeCollection(Arrays.asList(UserType.CUST_CORPORATE, UserType.CUST_INDIVIDUAL));
				}
				final Collection<UserInfo> userInfos = communicationManager.getUserList(searchParam);
				System.out.println(userInfos.size());
			
				for(final Iterator<UserInfo> iterator =  userInfos.iterator(); iterator.hasNext();) {
					UserInfo u = iterator.next();
					search = search.toLowerCase(); 
					String name = u.getFirstName();
					System.out.println(name);
					name = name.toLowerCase();
					String mail = u.getEmail();
					System.out.println(mail);
					mail = mail.toLowerCase();
					System.out.println(" login iof the user"+u.getLoginId());
					System.out.println(" name of the login user and theyy coinasioufif a "+u.getFirstName());
					if (name.contains(search) || mail.contains(search)) {
						System.out.println("contains success called a");
						rec_id.add(u.getLoginId());
					}
				}
				if(("TRASH").equalsIgnoreCase(request.getParameter("box")))
				{
					stats = communicationManager.getsearchmail(loginInfo.getLoginCredentialid(),request.getParameter("search"),request.getParameter("filter"),request.getParameter("box"),rec_id);
					map.put("Output", stats);
				}
					stats = communicationManager.getsearchmail(loginInfo.getLoginCredentialid(),request.getParameter("search"),request.getParameter("filter"),"inbox",rec_id);
					map.put("inbox", stats);
					stats = communicationManager.getsearchmail(loginInfo.getLoginCredentialid(),request.getParameter("search"),request.getParameter("filter"),"archive",rec_id);
					map.put("archive", stats);
					stats = communicationManager.getsearchmail(loginInfo.getLoginCredentialid(),request.getParameter("search"),request.getParameter("filter"),"sent",rec_id);
					map.put("sent", stats);
					stats = communicationManager.getsearchmail(loginInfo.getLoginCredentialid(),request.getParameter("search"),request.getParameter("filter"),"draft",rec_id);
					map.put("draft", stats);
				
				}
			
			map.put("Status", "Success");
			map.put("Output", stats);
			map.put("Current_Time",DateUtil.getDate());
			map.put("UserEmail", UserEmail);
			map.put("UserName",UserName);
			map.put("UserId", UserId);
		} catch (final Exception e) {
			map.put("Status", "Failure");
			map.put("Output", e.getMessage());
			e.printStackTrace();
		}
		resMap.put("res", JsonUtil.toJsonString(map));
		return new ModelAndView("fplajax_domain", resMap);
		//return new AbstractMessageReading().executeInternal(request, response, "[Gmail]/Trash");
	}
}
