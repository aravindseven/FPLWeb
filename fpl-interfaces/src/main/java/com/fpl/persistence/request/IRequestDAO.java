package com.fpl.persistence.request;

import java.util.Collection;

import com.fpl.core.request.SearchRequestParam;
import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.request.Request;

public interface IRequestDAO extends IHibernateDAOSupport<Request> {

	Collection<Request> getRequestByCustomerId(Long customerId);
	
	Collection<Request> getRequestList(SearchRequestParam requestParam);
	
	public Long getMaxId();
	
	Collection<Request> getActiveRequestByCustomerId(final Long customerId);
}
