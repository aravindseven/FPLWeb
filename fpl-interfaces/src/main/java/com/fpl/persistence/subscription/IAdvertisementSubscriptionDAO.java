package com.fpl.persistence.subscription;

import java.util.Collection;

import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.subscription.AdvertisementSubscription;

public interface IAdvertisementSubscriptionDAO extends IHibernateDAOSupport<AdvertisementSubscription> {
	
	Collection<AdvertisementSubscription> getAllEntities();
}


