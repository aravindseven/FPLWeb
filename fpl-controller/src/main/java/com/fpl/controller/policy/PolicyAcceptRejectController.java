package com.fpl.controller.policy;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.FPLException;
import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.policy.IPolicyPersister;
import com.fpl.util.JsonUtil;

public class PolicyAcceptRejectController extends AjaxBaseController {
	
	private IPolicyPersister policyPersister;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String policyId = request.getParameter("policyId");
		final String status = request.getParameter("status");
		FPLServiceResponse serviceResponse = null;
		try {
			serviceResponse = new FPLServiceResponse();
			policyPersister.updatePolicy(policyId, status);
			serviceResponse.setStatus("Success");
			serviceResponse.setReason("Policy Update Sucessfully !!");
		} catch (final FPLException e) {
			e.printStackTrace();
			serviceResponse.setStatus("Failure");
			serviceResponse.setReason(e.getMessage());
		}
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_domain", resMap);
	}

	/**
	 * @param policyPersister the policyPersister to set
	 */
	public void setPolicyPersister(final IPolicyPersister policyPersister) {
		this.policyPersister = policyPersister;
	}
}
