package com.fpl.services.profile;

import com.fpl.common.FPLServiceResponse;
import com.fpl.profile.financialplanner.FinancialPlannerPV;

public interface IFPLRegisterService {

	FPLServiceResponse insertFinancialPlanner(FinancialPlannerPV plannerPV);
}
