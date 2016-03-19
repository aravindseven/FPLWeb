package com.fpl.core.request.financialplanner;

import java.util.Collection;

import com.fpl.core.financialplanner.PartialUserView;
import com.fpl.core.request.IRequestFinder;
import com.fpl.core.request.customer.NewRequestPV;

public interface IRequestFPLFinder extends IRequestFinder {

	Collection<PartialUserView> getFPListByLocation(NewRequestPV newRequest);
}
