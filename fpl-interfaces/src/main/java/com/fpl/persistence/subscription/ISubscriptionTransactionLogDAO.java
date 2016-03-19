package com.fpl.persistence.subscription;

import java.util.Collection;

import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.subscription.audit.SubscriptionPaymentTransactionLog;

public interface ISubscriptionTransactionLogDAO extends IHibernateDAOSupport<SubscriptionPaymentTransactionLog> {
	
	Collection<SubscriptionPaymentTransactionLog> getAllEntities();
	
	
}