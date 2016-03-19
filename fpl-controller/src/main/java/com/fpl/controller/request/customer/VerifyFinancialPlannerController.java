package com.fpl.controller.request.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.financialplanner.PartialUserView;
import com.fpl.core.request.customer.NewRequestPV;
import com.fpl.core.request.financialplanner.IRequestFPLFinder;
import com.fpl.util.JsonUtil;
import com.fpl.util.StringUtil;

public class VerifyFinancialPlannerController extends AjaxBaseController {

	private IRequestFPLFinder fplFinder;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final NewRequestPV newRequest = new NewRequestPV();
		final String postalCode = request.getParameter("postalCode");
		final String domainId = request.getParameter("domainId");
		final FPLServiceResponse serviceResponse = new FPLServiceResponse();
		if(StringUtil.isEmpty(postalCode)) {
			serviceResponse.setReason("Postal Code should not be empty!");
			serviceResponse.setStatus("failure");
		}
		if(StringUtil.isEmpty(domainId)) {
			serviceResponse.setReason("Select the Request Type!");
			serviceResponse.setStatus("failure");
		}
		if(serviceResponse.getStatus() == null) {
			newRequest.setPostalCode(postalCode);
			newRequest.setType(domainId);
			final Collection<PartialUserView> fplViews =  fplFinder.getFPListByLocation(newRequest);
			if(fplViews == null || fplViews.isEmpty()) {
				serviceResponse.setReason("No financial planner found in this postal code, \nPlease refine your search or submit the request to the admin!");
				serviceResponse.setStatus("failure");
			} else {
				serviceResponse.setReason("Found financial planner");
				serviceResponse.setStatus("success");
			}
		}
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_domain", resMap);
	}

	/**
	 * @param fplFinder the fplFinder to set
	 */
	public void setFplFinder(final IRequestFPLFinder fplFinder) {
		this.fplFinder = fplFinder;
	}
}
