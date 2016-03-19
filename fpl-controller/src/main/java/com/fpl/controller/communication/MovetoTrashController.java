package com.fpl.controller.communication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.communication.ICommunicationManager;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.JsonUtil;

public class MovetoTrashController extends AjaxBaseController{
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final Map<String, String> resMap =  new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		Date now = new Date();
		String dateString=sdf.format(now);
		
		final Map map = new HashMap();
		String mids=request.getParameter("mailList");
		String from=request.getParameter("from");		
				System.out.println("getting mids from request"+request.getParameter("mailList"));
				System.out.println("getting FROM value from request"+request.getParameter("from"));
				String userid=loginInfo.getLoginCredentialid().toString();
				System.out.println("getting userid from trashcontroller"+userid);
		try {
			Boolean stats = communicationManager.movetotrash(dateString,mids,userid,from);
			map.put("Status", "Success");
			map.put("Result", stats);
		} catch (final Exception e) {
			map.put("Status", "Failure");
			map.put("Result", e.getMessage());
			e.printStackTrace();
		}
		resMap.put("res", JsonUtil.toJsonString(map));
		return new ModelAndView("fplajax_domain", resMap);
		
	}

}
