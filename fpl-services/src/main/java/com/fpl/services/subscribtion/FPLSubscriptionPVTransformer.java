package com.fpl.services.subscribtion;

import java.util.Collection;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.subscription.FplSubscription;

public class FPLSubscriptionPVTransformer extends AbstractTransformer<FplSubscription, FPSubscriptionListPV> {

@Override
public FPSubscriptionListPV transform(FplSubscription input) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Collection<FPSubscriptionListPV> transform(
		Collection<FplSubscription> list) {
	// TODO Auto-generated method stub
	return super.transform(list);
}

}
