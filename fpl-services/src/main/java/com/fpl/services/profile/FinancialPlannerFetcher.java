package com.fpl.services.profile;

import com.fpl.core.profile.financialplanner.IFinancialPlannerFinder;
import com.fpl.profile.financialplanner.FinancialPlannerPV;

public class FinancialPlannerFetcher implements IProfileFetcher {

	private IFinancialPlannerFinder financialPlannerFinder;
	
	@Override
	public FinancialPlannerPV getProfilePageView(final Long loginId) {
		return financialPlannerFinder.getFinancialPlannerByLoginId(loginId);
	}
	
	@Override
	public Long getMaxId() {
		
		return financialPlannerFinder.getMaxId();
	}

	/**
	 * @param financialPlannerFinder the financialPlannerFinder to set
	 */
	public void setFinancialPlannerFinder(final IFinancialPlannerFinder financialPlannerFinder) {
		this.financialPlannerFinder = financialPlannerFinder;
	}
}
