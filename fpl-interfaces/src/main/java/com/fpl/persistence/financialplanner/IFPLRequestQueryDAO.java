package com.fpl.persistence.financialplanner;

import java.util.Collection;
import java.util.List;

import com.fpl.core.financialplanner.PartialUserView;
import com.fpl.core.request.SearchRequestParam;
import com.fpl.core.request.customer.NewRequestPV;
import com.fpl.request.RequestFplConfig;

public interface IFPLRequestQueryDAO {

	List<PartialUserView> getFPListByLocation(final NewRequestPV newRequest);

	Collection<RequestFplConfig> getRequestFplConfigList(final SearchRequestParam searchRequest);
	
	Collection<PartialUserView> getFPLCollection(FPLSearchParam searchParam);
}
