package com.fpl.controller.request.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.request.RequestActivityDTO;
import com.fpl.persistence.request.IRequestStatusRefQueryDAO;
import com.fpl.util.JsonUtil;

public class GetRequestActivityHistoryController extends AjaxBaseController {
	
	private IRequestStatusRefQueryDAO requestStatusRefQueryDAO;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String requestId = request.getParameter("requestId");
		System.out.println("requestId: "+ requestId);
		final Collection<RequestActivityDTO> activityList =  requestStatusRefQueryDAO.getRequestActivityList(Long.valueOf(requestId));
		resMap.put("res", JsonUtil.toJsonString(activityList));
		return new ModelAndView("fplajax_domain", resMap);
	}

	/**
	 * @param requestStatusRefQueryDAO the requestStatusRefQueryDAO to set
	 */
	public void setRequestStatusRefQueryDAO(final IRequestStatusRefQueryDAO requestStatusRefQueryDAO) {
		this.requestStatusRefQueryDAO = requestStatusRefQueryDAO;
	}
}
