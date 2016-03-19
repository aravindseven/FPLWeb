package com.fpl.persistence.subscription;

import java.util.Collection;

import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.subscription.FplSubscription;

public interface IFplSubscriptionDAO extends IHibernateDAOSupport<FplSubscription> {
	
	Collection<FplSubscription> getAllEntities();
	Collection<FplSubscription> getFPLEntities(final long fplId);
	FplSubscription getActiveOrInProgressFPLEntities(final long fplId);
	Long getMaxId();
	
}


