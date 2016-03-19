package com.fpl.persistence.request;

import java.util.Collection;

import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.request.RequestFplConfig;

public interface IRequestFplConfigDAO extends IHibernateDAOSupport<RequestFplConfig> {

	Collection<RequestFplConfig> getRequestFplConfigList(final Long fplId);
	
	RequestFplConfig getByRequestFPLId(final Long requestId, final Long fplId);
}
