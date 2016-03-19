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
import com.fpl.core.communication.Usersettings;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.JsonUtil;

public class UserSettingsController  extends AjaxBaseController {
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	/* (non-Javadoc)
	 * @see com.fpl.controller.support.AjaxBaseController#executeAjaxCall(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final Usersettings settingsParam = new Usersettings();
		settingsParam.setMB_USER_ID(loginInfo.getLoginCredentialid().toString());
		settingsParam.setMS_USER_TYPE(loginInfo.getUserType());
		settingsParam.setMS_PROFILE_PIC(request.getParameter("profilepic"));
		settingsParam.setMS_ARCHIVE_DURATION(request.getParameter("archiveduration"));
		settingsParam.setMS_AUTO_RES(request.getParameter("autorespone"));
		settingsParam.setMS_AUTO_RES_MESS(request.getParameter("autoresponsemessage"));
		settingsParam.setMS_NOTIFICATION(request.getParameter("notification"));
		settingsParam.setMS_STARS(request.getParameter("stars"));
		final Map map = new HashMap();
		try {
			Boolean stats = communicationManager.Settings(settingsParam);
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
