package com.fpl.core.request;

import java.util.Collection;

public interface IRequestFinder {

	Collection<RequestListPV> getRequestList(Long loginId);
	
	RequestListPV getRequestInfo(Long requestId);
	
	Collection<RequestListPV> getRequestList(SearchRequestParam searchRequest);
}
