package com.fpl.controller.communication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.communication.ICommunicationManager;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.login.UserLoginInfo;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.profile.customer.Customer;
import com.fpl.util.JsonUtil;

public class CreateChatRoomsController extends AjaxBaseController {
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
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map map = new HashMap();
		ArrayList<String> chatrooms = new ArrayList<String>();
		int flag = 0;
		String pingeduser=request.getParameter("pingeduser");
		String to_user = request.getParameter("to_user");
				System.out.println("pingeduser"+pingeduser);
		try {
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
			String room =(loginInfo.getLoginCredentialid()).toString()+"-"+pingeduser; //+pingeduser;
			String roomreverse = pingeduser+"-"+loginInfo.getLoginCredentialid().toString(); //+pingeduser;
			
			Collection<String> str =communicationManager.getrooms(loginInfo.getLoginCredentialid());
			Iterator<String> itr= str.iterator();
			while(itr.hasNext())
			{
				System.out.print("inside while loop");
				String testwith_room = itr.next();
				String[] s = testwith_room.split(",");
				String chatroomtest = s[0];
				chatrooms.add(chatroomtest);
			}
			if(str.size() == 0)
			{
				flag = 0;
			}
			else
			{
				for(int i=0;i<chatrooms.size();i++)
				{
					String dbroom = chatrooms.get(i);
					if(dbroom.equalsIgnoreCase(room)  || dbroom.equalsIgnoreCase(roomreverse))
					{
						flag = 1;
						break;
					}
				}
				
			}
			if(flag == 0)
			{
				System.out.println("room is  not present");
				Boolean stats = communicationManager.createchatrooms(room,UserName,to_user);
				map.put("Status", "Success");
			}
			map.put("Result", room);
			map.put("Author", loginInfo.getLoginCredentialid().toString());
			map.put("Authorname",UserName);
			map.put("chatrooms",chatrooms);
		} catch (final Exception e) {
			map.put("Status", "Failure");
			map.put("Result", e.getMessage());
			e.printStackTrace();
		}
		resMap.put("res", JsonUtil.toJsonString(map));
		return new ModelAndView("fplajax_domain", resMap);
	}
	}