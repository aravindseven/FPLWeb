package com.fpl.persistence.subscription;

import java.util.Collection;

import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.subscription.AdvertisementType;

public interface IAdvertisementTypeDAO extends IHibernateDAOSupport<AdvertisementType> {
	
	Collection<AdvertisementType> getAllEntities();
}


