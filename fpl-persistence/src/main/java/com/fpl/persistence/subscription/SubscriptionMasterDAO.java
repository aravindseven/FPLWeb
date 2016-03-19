package com.fpl.persistence.subscription;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.subscription.SubscriptionMaster;

public class SubscriptionMasterDAO extends HibernateDAOSupport<SubscriptionMaster> implements ISubscriptionMasterDAO {
    
    @Override
    protected Class<SubscriptionMaster> getEntityClass() {
    	return SubscriptionMaster.class;
    }

    @Override
	public Collection<SubscriptionMaster> getAllEntities() {
		return getHibernateTemplate().execute(new HibernateCallback<SubscriptionMaster, Collection<SubscriptionMaster>>("getAllEntities") {
			@Override
			public Collection<SubscriptionMaster> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				return criteria.list();
			}
		});
	}
}
