package com.fpl.controller.request.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.controller.support.UserSessionKey;
import com.fpl.core.request.customer.IRequestCreator;
import com.fpl.core.request.customer.NewRequestPV;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.JsonUtil;

public class SaveRequestController extends AjaxBaseController {
	
	private IRequestCreator requestCreator;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		
		final Map<String, String> resMap =  new HashMap<String, String>();
		
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		
		final String requestJson = request.getParameter("requestJson");
		System.out.println(requestJson);
	     NewRequestPV newRequest = JsonUtil.convertPojo(requestJson, NewRequestPV.class);

		
		final String fplId = request.getParameter("fplId");
		final String requestCreationType = request.getParameter("reqSaveOption");
		final List<String> fplList = new ArrayList<String>();
		int fplIdValue=0;		
		
		try {
			fplIdValue=Integer.parseInt(fplId);
		} catch (NumberFormatException e) {
		}
		if(fplIdValue>0)
		{
			fplList.add(fplId);
		}
		
		newRequest.setFplList(fplList);
		newRequest.setLoginId(loginInfo.getLoginCredentialid()+"");
		if("SendFP".equals(requestCreationType)) {
			newRequest.setSendToFpl(true);
		} else {
			newRequest.setSendToFpl(false);
		}
		newRequest=requestCreator.saveRequest(newRequest);
		remove(request, UserSessionKey.NEW_REQUEST_PV);
		resMap.put("res", JsonUtil.toJsonString(newRequest));
		return new ModelAndView("fplajax_GetFPLList", resMap);
	}

	/**
	 * @param requestCreator the requestCreator to set
	 */
	public void setRequestCreator(final IRequestCreator requestCreator) {
		this.requestCreator = requestCreator;
	}
}