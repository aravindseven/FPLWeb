
package com.fpl.persistence.subscription;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.subscription.audit.SubscriptionPaymentTransactionLog;

public class SubscriptionTransactionLogDAO extends HibernateDAOSupport<SubscriptionPaymentTransactionLog> implements ISubscriptionTransactionLogDAO {
    
    @Override
    protected Class<SubscriptionPaymentTransactionLog> getEntityClass() {
    	return SubscriptionPaymentTransactionLog.class;
    }

    @Override
	public Collection<SubscriptionPaymentTransactionLog> getAllEntities() {
		return getHibernateTemplate().execute(new HibernateCallback<SubscriptionPaymentTransactionLog, Collection<SubscriptionPaymentTransactionLog>>("getAllEntities") {
			@Override
			public Collection<SubscriptionPaymentTransactionLog> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				return criteria.list();
			}
		});
	}
}