package com.fpl.persistence.country;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fpl.country.Country;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;

public class CountryDAO extends HibernateDAOSupport<Country> implements ICountryDAO {
    
    @Override
    protected Class<Country> getEntityClass() {
    	return Country.class;
    }

	@Override
	public Collection<Country> getAllEntities() {
		return getHibernateTemplate().execute(new HibernateCallback<Country, Collection<Country>>("getAllEntities") {
			@Override
			public Collection<Country> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				return criteria.list();
			}
		});
	}

	@Override
	public Country getCountryByName(final String name) {
		return getHibernateTemplate().execute(new HibernateCallback<Country, Country>("getCountryByName") {
			@Override
			public Country doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.add(Restrictions.eq("name", name));
				return (Country) criteria.uniqueResult();
			}
		});
	}
}
