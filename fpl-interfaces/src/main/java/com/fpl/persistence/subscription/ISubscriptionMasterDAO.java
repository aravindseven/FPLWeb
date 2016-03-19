package com.fpl.persistence.subscription;

import java.util.Collection;

import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.subscription.SubscriptionMaster;

public interface ISubscriptionMasterDAO extends IHibernateDAOSupport<SubscriptionMaster> {
		
		Collection<SubscriptionMaster> getAllEntities();
		
		
}

