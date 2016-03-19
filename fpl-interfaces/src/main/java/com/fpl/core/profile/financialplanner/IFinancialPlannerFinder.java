package com.fpl.core.profile.financialplanner;

import com.fpl.profile.financialplanner.FinancialPlannerPV;

public interface IFinancialPlannerFinder {

	FinancialPlannerPV getFinancialPlannerByLoginId(Long loginId);
	FinancialPlannerPV getFinancialPlannerById(Long fplId);
	Long getMaxId();
}
