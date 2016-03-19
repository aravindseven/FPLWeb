package com.fpl.core.paymentgateway;

import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;

public interface IPaymentGateway {
	
	
	public FPSubscriptionListPV getCheckoutRef(FPSubscriptionListPV fpSubscriptionListPV);
	public FPSubscriptionListPV getCheckoutToken(FPSubscriptionListPV fpSubscriptionListPV);
	public void cancelTransaction(final FPSubscriptionListPV persistPV);

}
