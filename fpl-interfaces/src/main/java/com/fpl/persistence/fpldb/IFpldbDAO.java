package com.fpl.persistence.fpldb;

import java.util.Collection;

import com.fpl.fpldb.Fpldb;
import com.fpl.persistence.support.IHibernateDAOSupport;

public interface IFpldbDAO extends IHibernateDAOSupport<Fpldb> {
	
	Collection<Fpldb> getAllEntities();
	
	
}
