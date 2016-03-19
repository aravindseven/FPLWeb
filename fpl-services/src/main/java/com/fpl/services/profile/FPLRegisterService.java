package com.fpl.services.profile;

import com.fpl.common.FPLServiceResponse;
import com.fpl.core.profile.financialplanner.IFinancialPlannerPersister;
import com.fpl.profile.financialplanner.FinancialPlannerPV;

public class FPLRegisterService implements IFPLRegisterService {
	
	private IFinancialPlannerPersister persister;
	
	@Override
	public FPLServiceResponse insertFinancialPlanner(final FinancialPlannerPV plannerPV) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			persister.persistCustomer(plannerPV);
			response.setReason("FinancialPlanner updated successfully !");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}

	public void setPersister(final IFinancialPlannerPersister persister) {
		this.persister = persister;
	}
}
