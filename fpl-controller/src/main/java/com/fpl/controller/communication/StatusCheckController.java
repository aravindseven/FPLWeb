package com.fpl.controller.communication;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
import com.fpl.core.communication.MessageParam;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.LoginSupport;
import com.fpl.persistence.login.LoginSupportDAO;
import com.fpl.util.JsonUtil;

public class StatusCheckController extends AjaxBaseController {
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	@Autowired
	private LoginSupportDAO logincsuppDAO;
	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map map = new HashMap();
		String partner_id=request.getParameter("chat_roompartnerid");
		String chatroom_id=request.getParameter("chat_room");
		System.out.println("messagew from user"+partner_id);
		System.out.println(request.getParameter("author_name"));
		System.out.println(chatroom_id);
		try{
			final LoginSupport logincre = logincsuppDAO.getByLoginCredentialId(Long.parseLong(partner_id));
			System.out.println(request.getParameter("partner_name"));
			String subject = "Chat With "+request.getParameter("partner_name");
			System.out.println("details for the particular user");
			System.out.println(logincre);
			System.out.println(logincre.getOnline());
			if(logincre.getOnline() == 1)
			{
				//getting chat messages based on chat room to send mail
				StringBuilder stats = communicationManager.getchatroombyid(chatroom_id,request.getParameter("author_name"),loginInfo.getLoginCredentialid(),request.getParameter("partner_name"),partner_id);
				System.out.println("ghsdggsdgsdgsdhs");
				System.out.println(stats);
				
				map.put("chatroommsges", stats);
				if(stats.length() != 0)
				{
					String ipAddress = request.getHeader("X-FORWARDED-FOR");  
					if (ipAddress == null) {  
						ipAddress = request.getRemoteAddr();  
					}
					final MessageParam messageParam = new MessageParam();
					messageParam.setLoginId(loginInfo.getLoginCredentialid());
					messageParam.setUserType(loginInfo.getUserType());
					messageParam.setMailfromid(loginInfo.getLoginCredentialid());
					messageParam.setMailtoid(partner_id);
					messageParam.setChatmessage(stats);
					messageParam.setSubject(subject);
					messageParam.setSentip(ipAddress);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
					Date now = new Date();
					String dateString=sdf.format(now);
					messageParam.setDate(dateString);
					try {
						System.out.println("called");
						Boolean stats1 = communicationManager.postMessage1(messageParam);
						map.put("mail", stats1);
					}
					catch(Exception e)
					{
						
					}
				}
				
				
				//deleting chatroom with all detailks and mail id
				Boolean stats11 = communicationManager.deletechatmessagesbyid(chatroom_id);
			}
			map.put("Status", "Success");
			
			
		} catch (final Exception e) {
			map.put("Status", "Failure");
			map.put("Result", e.getMessage());
			e.printStackTrace();
		}
		resMap.put("res", JsonUtil.toJsonString(map));
		return new ModelAndView("fplajax_domain", resMap);
	}
	
	}