package com.fpl.persistence.fpldb;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.fpl.country.Country;
import com.fpl.fpldb.Fpldb;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;

public class FpldbDAO extends HibernateDAOSupport<Fpldb> implements IFpldbDAO {
    
    @Override
    protected Class<Fpldb> getEntityClass() {
    	return Fpldb.class;
    }

	@Override
	public Collection<Fpldb> getAllEntities() {
		return getHibernateTemplate().execute(new HibernateCallback<Country, Collection<Fpldb>>("getAllEntities") {
			@Override
			public Collection<Fpldb> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				return criteria.list();
			}
		});
	}


}
