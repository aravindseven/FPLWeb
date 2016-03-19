package com.fpl.controller.request.financialplanner;

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
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.services.profile.IProfileFetcher;
import com.fpl.util.JsonUtil;
import com.fpl.util.StringUtil;

public class FPLRejectRequestController extends AjaxBaseController {
	
	private IRequestManager requestManager;
	private IProfileFetcher fplProfileFetcher; 
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final String requestId = request.getParameter("requestId");
		final String rejectType = request.getParameter("rejectType");
		final String description = "Rejected";
		final RespondRequestPV respondRequest = new RespondRequestPV();
		
		respondRequest.setDescription(description);
		if(StringUtil.isNotEmpty(rejectType) && "CUSTOMER".equalsIgnoreCase(rejectType))
		{
			respondRequest.setFplId(request.getParameter("fplId"));
			respondRequest.setStatus("ST_08");
		}else
		{
			FinancialPlannerPV financialPlannerPV=fplProfileFetcher.getProfilePageView(loginInfo.getLoginCredentialid());
			respondRequest.setFplId(financialPlannerPV.getFplId()+"");
			respondRequest.setStatus("ST_06");
		}	
		respondRequest.setRequestId(requestId);
		System.out.println("respondRequest: "+ respondRequest);
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
		return new ModelAndView("fplajax_rejectRequest", resMap);
	}

	/**
	 * @param requestManager the requestManager to set
	 */
	public void setRequestManager(final IRequestManager requestManager) {
		this.requestManager = requestManager;
	}

	public void setFplProfileFetcher(IProfileFetcher fplProfileFetcher) {
		this.fplProfileFetcher = fplProfileFetcher;
	}
	
	
}
