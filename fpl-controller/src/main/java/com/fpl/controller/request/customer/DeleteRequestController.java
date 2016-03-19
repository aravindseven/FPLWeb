package com.fpl.controller.request.customer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.persistence.request.IRequestDAO;
import com.fpl.request.Request;
import com.fpl.util.JsonUtil;

public class DeleteRequestController extends AjaxBaseController {

	private IRequestDAO requestDAO;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String requestId = request.getParameter("requestId");
		System.out.println("requestId: "+ requestId);
		final FPLServiceResponse serviceResponse = new FPLServiceResponse();
		try {
			final Request fplRequest =  requestDAO.get(Long.valueOf(requestId));
			requestDAO.delete(fplRequest, true);
			serviceResponse.setStatus("success");
			serviceResponse.setReason("Request deleted successfully !");
		} catch(final Exception e) {
			serviceResponse.setStatus("failure");
			serviceResponse.setReason(e.getMessage());
		}
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_domain", resMap);
	}

	/**
	 * @param requestDAO the requestDAO to set
	 */
	public void setRequestDAO(final IRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}
}
