package com.fpl.core.paymentgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fpl.core.subsciption.SubscriptionPVTransformer;
import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.persistence.subscription.IFplSubscriptionDAO;
import com.fpl.persistence.subscription.ISubscriptionTransactionLogDAO;
import com.fpl.subscription.FplSubscription;
import com.fpl.subscription.SubscriptionStatusType;
import com.fpl.subscription.TransactionStatusType;
import com.fpl.subscription.audit.SubscriptionPaymentTransactionLog;
import com.fpl.util.StringUtil;

public abstract class AbstractPaymentGateway implements IPaymentGateway{
	
	@Autowired
	@Qualifier("fpl.subscrption.FplSubscriptionDAO")
	protected IFplSubscriptionDAO subscriptionDAO;
	
	
	@Autowired
	@Qualifier("fpl.subscrption.SubscriptionTransactionLogDAO")
	protected ISubscriptionTransactionLogDAO transactionLogDAO;

	
	public void cancelTransaction(final FPSubscriptionListPV persistPV) {
		
		FplSubscription subscription=subscriptionDAO.get(Long.parseLong(persistPV.getId()));
		subscription.setActivityFlag(SubscriptionStatusType.IN.name());
		subscription.getPaymentTransaction().setStatus(TransactionStatusType.UC.name());
		subscription.getPaymentTransaction().setNote(TransactionStatusType.UC.getStatus());
		subscriptionDAO.saveOrUpdate(subscription, true);
		
		SubscriptionPVTransformer subPVTransformer=new SubscriptionPVTransformer();
		
		SubscriptionPaymentTransactionLog log=buildLog(subPVTransformer.transform(subscription));
		log.setTransactionToken(persistPV.getToken());
		log.setNote("Failed::::Payment Transcation Cancelled");
		transactionLogDAO.save(log, true);
	}

	protected SubscriptionPaymentTransactionLog buildLog(FPSubscriptionListPV fpSubscriptionListPV)
	{
		SubscriptionPaymentTransactionLog log=new SubscriptionPaymentTransactionLog();
		if(StringUtil.isNotEmpty(fpSubscriptionListPV.getFplId()))
		{
			log.setFplId(Long.parseLong(fpSubscriptionListPV.getFplId()));
		}	
		log.setSubId(Long.parseLong(fpSubscriptionListPV.getId()));
		log.setSubMasterId(Long.parseLong(fpSubscriptionListPV.getSubMasterId()));
		log.setCurrency("SGD");
		
		return log;
	}

}
