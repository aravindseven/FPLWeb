package com.fpl.core.subsciption;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.fpl.core.subsciption.fpl.CountryPV;
import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.core.subsciption.fpl.SubscriptionMasterPV;
import com.fpl.subscription.FplSubscription;
import com.fpl.subscription.PaymentTransaction;
import com.fpl.subscription.SubscriptionMaster;
import com.fpl.subscription.SubscriptionStatusType;
import com.fpl.subscription.TransactionStatusType;

public class SubscriptionPVTransformer {

	
	public FPSubscriptionListPV transform(FplSubscription subscription)
	{
		final FPSubscriptionListPV listPV = new FPSubscriptionListPV();
		if(subscription!=null && subscription.getId()!=null && subscription.getId()>0)
		{
		listPV.setId(subscription.getId().toString());
		listPV.setFplId(subscription.getFplId().toString());
		listPV.setContactName(subscription.getCustomerName());
		listPV.setStatus(subscription.getActivityFlag());
		listPV.setStartDate(subscription.getStartDate());
		listPV.setEndDate(subscription.getEndDate());
		listPV.setSubMasterId(subscription.getSubscriptionMasterId().toString());
		listPV.setSubscriptionAmount(subscription.getSubscriptionAmount());
		listPV.setSubscriptionTaxAmount(subscription.getSubscriptionTaxAmount());
		listPV.setSubscriptionTotalAmount(subscription.getSubscriptionTotalAmount());
		listPV.setStatusDesc(SubscriptionStatusType.valueOf(subscription.getActivityFlag()).getStatus());
		
		
		listPV.setIdText("SUB"+String.format("%010d",subscription.getId()));
		listPV.setInvoiceNumber("INV"+String.format("%010d",subscription.getId()));
		
		SubscriptionMasterPV masterPV=new SubscriptionMasterPV();
		SubscriptionMaster master=subscription.getSubscriptionMaster();
		if(master!=null)
		{
			
			try {
				masterPV.setType(master.getType());
				masterPV.setPlanFrequency(master.getPlanFrequency());
				masterPV.setRate(master.getRate());

				if(master.getCountry()!=null)
				{
					CountryPV countryPV=new CountryPV();
					BeanUtils.copyProperties(countryPV, master.getCountry());
					masterPV.setCountryPV(countryPV);
				}
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}	
		
		listPV.setSubscriptionMasterPV(masterPV);
		PaymentTransaction paymentTransaction=subscription.getPaymentTransaction();
		
		if(paymentTransaction!=null)
		{
			listPV.setToken(paymentTransaction.getTransactionToken());
			listPV.setTransactionRef(paymentTransaction.getTransactionRef());
			listPV.setValidationDate(paymentTransaction.getTransactionDate());
			listPV.setTransactionPayerId(paymentTransaction.getTransactionPayerId());
			listPV.setMode(paymentTransaction.getMode());
			listPV.setPaymentStatus(paymentTransaction.getStatus());
			listPV.setPaymentStatusDesc(TransactionStatusType.valueOf(paymentTransaction.getStatus()).getStatus());
		}
		}
		return listPV;

	}

}
