package com.fpl.core.profile.financialplanner;

import com.fpl.common.AbstractTransformer;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.profile.financialplanner.FinancialPlannerPV;

public class FinancialPlannerFinder implements IFinancialPlannerFinder {

    private IFinancialPlannerDAO financialPlannerDAO;
    private AbstractTransformer<FinancialPlanner, FinancialPlannerPV> transformer; 
	
	@Override
	public FinancialPlannerPV getFinancialPlannerByLoginId(final Long loginId) {
		FinancialPlannerPV plannerPV = null;
		final FinancialPlanner planner = financialPlannerDAO.getFinancialPlannerByLoginId(loginId);
		if(planner != null) {
			plannerPV = transformer.transform(planner);
		}
		return plannerPV;
	}

	public Long getMaxId()
	{
	 return financialPlannerDAO.getMaxId();	
	}
	
	
	
	@Override
	public FinancialPlannerPV getFinancialPlannerById(Long fplId) {
		
		FinancialPlannerPV plannerPV = null;
		final FinancialPlanner planner = financialPlannerDAO.get(fplId);
		if(planner != null) {
			plannerPV = transformer.transform(planner);
		}
		return plannerPV;
	}

	/**
	 * @param financialPlannerDAO the financialPlannerDAO to set
	 */
	public void setFinancialPlannerDAO(final IFinancialPlannerDAO financialPlannerDAO) {
		this.financialPlannerDAO = financialPlannerDAO;
	}

	/**
	 * @param transformer the transformer to set
	 */
	public void setTransformer(
			final AbstractTransformer<FinancialPlanner, FinancialPlannerPV> transformer) {
		this.transformer = transformer;
	}
}
