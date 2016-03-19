package com.fpl.persistence.financialplanner;

import java.util.Collection;

import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.profile.admin.SearchProfilePV;

public interface IFinancialPlannerDAO extends IHibernateDAOSupport<FinancialPlanner> {

	FinancialPlanner getFinancialPlannerByLoginId(final Long loginId);
	
	Collection<FinancialPlanner> getFinancialPlannerList(SearchProfilePV searchProfile);
	
	Long getMaxId();
}
