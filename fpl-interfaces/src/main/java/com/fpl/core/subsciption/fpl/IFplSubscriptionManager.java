package com.fpl.core.subsciption.fpl;

import java.util.Collection;


import com.fpl.fpldb.Fpldb;
import com.fpl.profile.admin.SearchProfilePV;

public interface IFplSubscriptionManager {

	Collection<FPSubscriptionListPV> getAllFplSubscription();
	
	Collection<FPSubscriptionListPV> getAllFplSubscription(final SearchProfilePV searchProfilePV );
	
	
	FPSubscriptionListPV persist(FPSubscriptionListPV persistPV);
	
	void persistToken(FPSubscriptionListPV persistPV);
	
	FPSubscriptionListPV getFplSubscription(final long fplId);
	
	Collection<SubscriptionMasterPV> getSubscriptionDetails();
	
	FPSubscriptionListPV getFplSubscriptionById(final long subId);
	
	FPSubscriptionListPV getPayPalCheckoutToken(FPSubscriptionListPV fpSubscriptionListPV);

	FPSubscriptionListPV getPayPalCheckoutRef(FPSubscriptionListPV fpSubscriptionListPV);

	FPSubscriptionListPV getEWayCheckoutToken(FPSubscriptionListPV fpSubscriptionListPV);

	FPSubscriptionListPV getEWayCheckoutRef(FPSubscriptionListPV fpSubscriptionListPV);

	void persistTransactionDetails(FPSubscriptionListPV persistPV);
	
	void cancelTransaction(final FPSubscriptionListPV persistPV);
	
	Long getSubscriptionMaxId();
	
	Fpldb getFPLDB();
	
	void logAdminFPStatusUpdate(FPSubscriptionListPV oldFpSubscriptionListPV,FPSubscriptionListPV newFpSubscriptionListPV,String loginId);
}
