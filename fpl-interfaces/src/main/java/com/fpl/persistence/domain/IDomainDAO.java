package com.fpl.persistence.domain;

import java.util.Collection;

import com.fpl.domain.Domain;
import com.fpl.persistence.support.IHibernateDAOSupport;

public interface IDomainDAO extends IHibernateDAOSupport<Domain> {

	Collection<Domain> getDomainByName(Collection<String> names);
	
	Collection<Domain> getAllEntities();
}
