package com.fpl.core.request.financialplanner;

import java.util.Collection;
import java.util.List;

import com.fpl.core.request.IRequestManager;
import com.fpl.core.request.RequestActivityDTO;
import com.fpl.core.request.customer.CustomerDashboardPV;

public interface IFPLRequestManager extends IRequestManager {

	Collection<RequestActivityDTO> getRespondedFPL(Long requestId);
	List<CustomerDashboardPV> fetchCustomerDashboardDetails(final Long customerId);
	List<FPLDashboardPV> fetchFPLDashboardDetails(final Long loginId);
}
