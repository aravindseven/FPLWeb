package com.fpl.core.request.financialplanner;

import java.util.Date;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.request.RespondRequestPV;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.persistence.status.IStatusDAO;
import com.fpl.request.RequestStatusReference;
import com.fpl.status.Status;
import com.fpl.util.StringUtil;

public class FPLRespondReqStatusRefTransformer extends AbstractTransformer<RespondRequestPV, RequestStatusReference> {

	private IFinancialPlannerDAO financialPlannerDAO;
	private IStatusDAO statusDAO;
	
	@Override
	public RequestStatusReference transform(final RespondRequestPV respondRequest) {
		System.out.println("\n\n" + respondRequest);
		final RequestStatusReference requestStatusRef = new RequestStatusReference();
		FinancialPlanner financialPlanner = null;
		if(StringUtil.isNotEmpty(respondRequest.getFplId())) {
			financialPlanner = financialPlannerDAO.get(Long.valueOf(respondRequest.getFplId()));
		} else {
			financialPlanner = financialPlannerDAO.getFinancialPlannerByLoginId(Long.valueOf(respondRequest.getLoginId()));
		}
		final Status status = statusDAO.getStatusByDisc(respondRequest.getStatus());
		if(financialPlanner!=null)
		{
			requestStatusRef.setFinPlannerId(financialPlanner.getId());
		}	
		requestStatusRef.setStatusId(status.getId());
		requestStatusRef.setDate(new Date());
		requestStatusRef.setNote(respondRequest.getDescription());
		requestStatusRef.setRequestId(Long.valueOf(respondRequest.getRequestId()));
		return requestStatusRef;
	}

	/**
	 * @param financialPlannerDAO the financialPlannerDAO to set
	 */
	public void setFinancialPlannerDAO(final IFinancialPlannerDAO financialPlannerDAO) {
		this.financialPlannerDAO = financialPlannerDAO;
	}

	/**
	 * @param statusDAO the statusDAO to set
	 */
	public void setStatusDAO(final IStatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}
}
