package com.fpl.controller.request.customer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.request.IRequestManager;
import com.fpl.core.request.RespondRequestPV;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.JsonUtil;

public class RequestConnectController extends AjaxBaseController {

	private IRequestManager requestManager;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final String requestId = request.getParameter("requestId");
		final String fplId = request.getParameter("fplId");
		final String description = "Connected";
		final RespondRequestPV respondRequest = new RespondRequestPV();
		respondRequest.setLoginId(loginInfo.getLoginCredentialid()+"");
		respondRequest.setFplId(fplId);
		respondRequest.setDescription(description);
		respondRequest.setStatus("ST_07");
		respondRequest.setRequestId(requestId);
		final FPLServiceResponse serviceResponse = new FPLServiceResponse();
		try {
			requestManager.respondRequest(respondRequest);
			serviceResponse.setStatus("success");
			serviceResponse.setReason("Your Respond is send to the customer !");
		} catch(final Exception e) {
			e.printStackTrace();
			serviceResponse.setStatus("failure");
			serviceResponse.setReason(e.getMessage());
		}
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_respondRequest", resMap);
	}

	/**
	 * @param requestManager the requestManager to set
	 */
	public void setRequestManager(final IRequestManager requestManager) {
		this.requestManager = requestManager;
	}
}
