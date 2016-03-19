package com.fpl.controller.communication;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
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
import com.fpl.login.UserLoginInfo;
import com.fpl.login.UserType;
import com.fpl.util.JsonUtil;

public class GetUserListController extends AjaxBaseController {
	
	@Autowired
	@Qualifier("fpl.communication.communicationManager")
	private ICommunicationManager communicationManager;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final UserSearchParam searchParam = new UserSearchParam();
		if(loginInfo.getUserType().equals(UserType.CUST_CORPORATE) || loginInfo.getUserType().equals(UserType.CUST_INDIVIDUAL)) {
			searchParam.setUserTypeCollection(Arrays.asList(UserType.FP_CORPORATE, UserType.FP_INDIVIDUAL));
		} else if(loginInfo.getUserType().equals(UserType.FP_CORPORATE) || loginInfo.getUserType().equals(UserType.FP_INDIVIDUAL)) {
			searchParam.setUserTypeCollection(Arrays.asList(UserType.CUST_CORPORATE, UserType.CUST_INDIVIDUAL));
		}
		final Collection<UserInfo> userInfos = communicationManager.getUserList(searchParam);
		resMap.put("res", JsonUtil.convertJSONSingleStr(userInfos));
		return new ModelAndView("fplajax_GetUserList",resMap);
	}
}
