package com.fpl.core.request.support;

import com.fpl.core.request.RequestListPV;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.persistence.financialplanner.FinancialPlannerDAO;
import com.fpl.persistence.request.IRequestDAO;
import com.fpl.request.Request;
import com.fpl.util.DateUtil;

public abstract class AbstractRequestFinder {

	protected IRequestDAO requestDAO;
	protected FinancialPlannerDAO financialPlannerDAO;
	
	public RequestListPV getRequestInfo(final Long requestId) {
		final Request request = requestDAO.get(requestId);
		final RequestListPV requestList = new RequestListPV();
		requestList.setCreatedDate(request.getCreatedDate());
		requestList.setCurrentStatus(request.getStatus().getDescription());
		requestList.setDescription(request.getDescription());
		requestList.setDomainName(request.getDomain().getName());
		requestList.setId(request.getId()+"");
		requestList.setKeyword(request.getKeyword());
		requestList.setDomainId(request.getDomainId().toString());
		requestList.setLocation(request.getLocation());
		requestList.setPostalCode(request.getPostalCode());
		requestList.setUpdatedDate(DateUtil.getFormattedDate(request.getUpdatedDate()));
		requestList.setCustomerId(request.getCustomerId().toString());
		if(request.getFinPlannerId() != null) {
			final FinancialPlanner financialPlanner = financialPlannerDAO.get(request.getFinPlannerId());
			requestList.setFplName(financialPlanner.getPersonalData().getName());
		}
		return requestList;
	}
	
	/**
	 * @param financialPlannerDAO the financialPlannerDAO to set
	 */
	public void setFinancialPlannerDAO(final FinancialPlannerDAO financialPlannerDAO) {
		this.financialPlannerDAO = financialPlannerDAO;
	}

	/**
	 * @param requestDAO the requestDAO to set
	 */
	public void setRequestDAO(final IRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}
}
