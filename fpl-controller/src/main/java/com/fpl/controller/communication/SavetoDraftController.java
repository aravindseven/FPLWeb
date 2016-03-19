package com.fpl.controller.communication;

import java.text.SimpleDateFormat;
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
import com.fpl.core.communication.MessageParam;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.DateUtil;
import com.fpl.util.JsonUtil;

public class SavetoDraftController extends AjaxBaseController {
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
			ipAddress = request.getRemoteAddr();  
		}
		final MessageParam messageParam = new MessageParam();
		messageParam.setLoginId(loginInfo.getLoginCredentialid());
		messageParam.setUserType(loginInfo.getUserType());
		messageParam.setMailfromid(loginInfo.getLoginCredentialid());
		messageParam.setMsgid(request.getParameter("msgid"));
		messageParam.setMailtoid(request.getParameter("recipient"));
		messageParam.setMessage(request.getParameter("content"));
		messageParam.setSubject(request.getParameter("subject"));
		messageParam.setSentip(ipAddress);
		messageParam.setDate(DateUtil.getDateTime());
		final Map map = new HashMap();
		try {
			String stats = communicationManager.savetodraft(messageParam);
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
	
	/**
	 * @param policyPersister the policyPersister to set
	 */
	public void setCommunicationManager(final ICommunicationManager communicationManager) {
		this.communicationManager = communicationManager;
	}
}

