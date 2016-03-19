package com.fpl.core.subsciption;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.subsciption.advertisement.AdvSubscriptionListPV;
import com.fpl.core.subsciption.advertisement.AdvSubscriptionPersistPV;
import com.fpl.core.subsciption.advertisement.IAdSubscriptionManager;
import com.fpl.persistence.subscription.IAdvertisementSubscriptionDAO;
import com.fpl.subscription.AdvertisementSubscription;

@Component
public class AdSubscriptionManager implements IAdSubscriptionManager {

	@Autowired
	@Qualifier("fpl.subscrption.AdvertisementSubscriptionDAO")
	private IAdvertisementSubscriptionDAO subscriptionDAO;
	
	@Override
	public Collection<AdvSubscriptionListPV> getAllAdSubscription() {
		final Collection<AdvertisementSubscription> subscriptions = subscriptionDAO.getAllEntities();
		return new AbstractTransformer<AdvertisementSubscription, AdvSubscriptionListPV>() {
			@Override
			public AdvSubscriptionListPV transform(final AdvertisementSubscription subscription) {
				final AdvSubscriptionListPV listPV = new AdvSubscriptionListPV();
				listPV.setContactName(subscription.getTarget());
				listPV.setName(subscription.getAdvertisementType().getType());
				listPV.setStatus(subscription.getWorkStatus());
				listPV.setId(subscription.getId()+"");
				return listPV;
			}
		}.transform(subscriptions);
	}

	@Override
	public Date persist(final AdvSubscriptionPersistPV persistPV) {
		return null;
	}
}
