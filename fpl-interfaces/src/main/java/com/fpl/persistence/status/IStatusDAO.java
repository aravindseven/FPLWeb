package com.fpl.persistence.status;

import java.util.Collection;

import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.status.Status;

public interface IStatusDAO extends IHibernateDAOSupport<Status> {

	Status getStatusByDisc(String disDescription);
	
	Collection<Status> getStatusList(Long statusMasterId);
}
