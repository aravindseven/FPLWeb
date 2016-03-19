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
import com.fpl.login.UserLoginInfo;
import com.fpl.util.DateUtil;
import com.fpl.util.JsonUtil;

public class GetUserSettings extends AjaxBaseController {
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		
		System.out.println("User Id"+loginInfo.getLoginCredentialid());
		final Map map = new HashMap();
		try {
			Object stats = communicationManager.getSettings(loginInfo.getLoginCredentialid());
			map.put("Status", "Success");
			map.put("Output", stats);
			map.put("Current_Time",DateUtil.getDate());
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
