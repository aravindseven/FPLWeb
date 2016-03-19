package com.fpl.persistence.request;

import java.util.Collection;

import com.fpl.core.request.RequestActivityDTO;
import com.fpl.core.request.financialplanner.FPLDashboardPV;

public interface IRequestStatusRefQueryDAO {
	
	Collection<RequestActivityDTO> getRequestActivityList(Long requestId);
	
	Collection<RequestActivityDTO> getRespondedFPL(Long requestId);
	
	Collection<RequestActivityDTO> getRequestDashboardDetails(final Long requestId);
	
	Collection<FPLDashboardPV> getRequestDashboardCustomerDetails(final Long fplId);
}
