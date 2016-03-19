package com.fpl.persistence.subscription;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.subscription.AdvertisementSubscription;

public class AdvertisementSubscriptionDAO extends HibernateDAOSupport<AdvertisementSubscription> implements IAdvertisementSubscriptionDAO {
    
    @Override
    protected Class<AdvertisementSubscription> getEntityClass() {
    	return AdvertisementSubscription.class;
    }

    @Override
	public Collection<AdvertisementSubscription> getAllEntities() {
		return getHibernateTemplate().execute(new HibernateCallback<AdvertisementSubscription, Collection<AdvertisementSubscription>>("getAllEntities") {
			@Override
			public Collection<AdvertisementSubscription> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				return criteria.list();
			}
		});
	}
}
