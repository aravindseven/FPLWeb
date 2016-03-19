package com.fpl.core.profile.financialplanner;

import com.fpl.core.profile.IProfileModifier;
import com.fpl.profile.financialplanner.FinancialPlannerPV;

public interface IFinancialPlannerPersister extends IProfileModifier {
 
    void persistCustomer(FinancialPlannerPV financialPlannerPV);
}


