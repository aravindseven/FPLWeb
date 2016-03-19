package com.fpl.persistence.subscription;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.fpl.country.Country;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.subscription.AdvertisementType;

public class AdvertisementTypeDAO extends HibernateDAOSupport<AdvertisementType> implements IAdvertisementTypeDAO {
    
    @Override
    protected Class<AdvertisementType> getEntityClass() {
    	return AdvertisementType.class;
    }

    @Override
	public Collection<AdvertisementType> getAllEntities() {
		return getHibernateTemplate().execute(new HibernateCallback<Country, Collection<AdvertisementType>>("getAllEntities") {
			@Override
			public Collection<AdvertisementType> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				return criteria.list();
			}
		});
	}
}
