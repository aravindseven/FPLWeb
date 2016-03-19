package com.fpl.core.subsciption.advertisement;

import java.util.Collection;
import java.util.Date;

public interface IAdSubscriptionManager {

	Collection<AdvSubscriptionListPV> getAllAdSubscription();
	
	Date persist(AdvSubscriptionPersistPV persistPV);
}
