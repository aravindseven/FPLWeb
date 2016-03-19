package com.fpl.core.subsciption;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.paymentgateway.EWayGateway;
import com.fpl.core.paymentgateway.PayPalGateway;
import com.fpl.core.subsciption.fpl.CountryPV;
import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.core.subsciption.fpl.IFplSubscriptionManager;
import com.fpl.core.subsciption.fpl.SubscriptionMasterPV;
import com.fpl.country.CountryUtils;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.fpldb.Fpldb;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.persistence.fpldb.IFpldbDAO;
import com.fpl.persistence.subscription.IFplSubscriptionDAO;
import com.fpl.persistence.subscription.ISubscriptionMasterDAO;
import com.fpl.persistence.subscription.ISubscriptionTransactionLogDAO;
import com.fpl.profile.admin.SearchProfilePV;
import com.fpl.subscription.FplSubscription;
import com.fpl.subscription.PaymentTransaction;
import com.fpl.subscription.SubscriptionMaster;
import com.fpl.subscription.audit.SubscriptionPaymentTransactionLog;
import com.fpl.util.StringUtil;

@Component
public class FplSubscriptionManager implements IFplSubscriptionManager {

	@Autowired
	@Qualifier("fpl.profile.financialPlannerDAO")
	private IFinancialPlannerDAO financialPlannerDAO;

	@Autowired
	@Qualifier("fpl.subscrption.SubscriptionMasterDAO")
	private ISubscriptionMasterDAO subscriptionMasterDAO;

	@Autowired
	@Qualifier("fpl.subscrption.FpldbDAO")
	private IFpldbDAO fpldbDAO;
	
	@Autowired
	@Qualifier("fpl.subscrption.FplSubscriptionDAO")
	private IFplSubscriptionDAO subscriptionDAO;
	
	@Autowired
	private PayPalGateway payPalGateway; 

	@Autowired
	private EWayGateway eWayGateway;
	
	@Autowired
	@Qualifier("fpl.subscrption.SubscriptionTransactionLogDAO")
	protected ISubscriptionTransactionLogDAO transactionLogDAO;
	
	SubscriptionPVTransformer subscriptionPVTransformer=new SubscriptionPVTransformer();

	@Override
	public Collection<FPSubscriptionListPV> getAllFplSubscription() {
		final Collection<FplSubscription> subscriptions = subscriptionDAO.getAllEntities();
		return new AbstractTransformer<FplSubscription, FPSubscriptionListPV>() {
			@Override
			public FPSubscriptionListPV transform(final FplSubscription subscription) {
				final FPSubscriptionListPV listPV = new FPSubscriptionListPV();
				listPV.setId(subscription.getId()+"");
				listPV.setName(subscription.getSubscriptionMaster().getType());
				listPV.setStatus(subscription.getActivityFlag());
				final FinancialPlanner financialPlanner = financialPlannerDAO.get(subscription.getFplId());
				listPV.setContactName(financialPlanner.getPersonalData().getName());
				return listPV;
			}
		}.transform(subscriptions);
	}

	public void cancelTransaction(final FPSubscriptionListPV persistPV) {
     	  payPalGateway.cancelTransaction(persistPV);
	}
	
	@Override
	public FPSubscriptionListPV persist(FPSubscriptionListPV persistPV) {
		
		
		
		FplSubscription subscription =new FplSubscription();
		if(StringUtil.isNotEmpty(persistPV.getId()))
		{
			subscription=subscriptionDAO.get(Long.parseLong(persistPV.getId()));
		}	
		subscription.setFplId(Long.parseLong(persistPV.getFplId()));
		long subId=Long.parseLong(persistPV.getSubMasterId());
		SubscriptionMaster master=subscriptionMasterDAO.get(subId);
		subscription.setSubscriptionMasterId(subId);
		subscription.setSubscriptionMaster(master);
		subscription.setCustomerName(persistPV.getContactName());
		subscription.setDuriation(master.getPlanFrequency().intValue());
		subscription.setActivityFlag(persistPV.getStatus());
		subscription.setSubscriptionMaster(master);
		subscription.setStartDate(new Date());
		
		CountryPV countryPV=new CountryPV();
		if(master.getCountry()!=null)
		{
			
			try {
				BeanUtils.copyProperties(countryPV, master.getCountry());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		persistPV=populateSubscriptionAmount(persistPV,countryPV);
		
		subscription.setSubscriptionAmount(persistPV.getSubscriptionAmount());
		subscription.setSubscriptionTaxAmount(persistPV.getSubscriptionTaxAmount());
		subscription.setSubscriptionTotalAmount(persistPV.getSubscriptionTotalAmount());
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.YEAR, c.get(Calendar.YEAR)+master.getPlanFrequency().intValue());
		Date toDate = c.getTime();
		subscription.setEndDate(toDate);
		
		PaymentTransaction transaction=subscription.getPaymentTransaction();
		if(transaction==null)
		{
			transaction=new PaymentTransaction();
		}
		transaction.setMode(persistPV.getMode());
		transaction.setStatus(persistPV.getPaymentStatus());
		
		subscription.setPaymentTransaction(transaction);
		
		subscriptionDAO.saveOrUpdate(subscription, true);
		return subscriptionPVTransformer.transform(subscription);
	}

	public FPSubscriptionListPV getFplSubscriptionById(final long subId)
	{
		FplSubscription subscription=subscriptionDAO.load(subId);

		FPSubscriptionListPV listPV=subscriptionPVTransformer.transform(subscription);
				
		return listPV;

	}
	
		
	
	public FPSubscriptionListPV getFplSubscription(final long fplId)
	{
	
		FplSubscription subscription = subscriptionDAO.getActiveOrInProgressFPLEntities(fplId);

		return subscriptionPVTransformer.transform(subscription);
	}

	public FPSubscriptionListPV getPayPalCheckoutRef(FPSubscriptionListPV fpSubscriptionListPV)
	{
		return payPalGateway.getCheckoutRef(fpSubscriptionListPV);
	}
	
	
	
	public FPSubscriptionListPV getPayPalCheckoutToken(FPSubscriptionListPV fpSubscriptionListPV)
	{

		return payPalGateway.getCheckoutToken(fpSubscriptionListPV);	
	}
	
	
	
	@Override
	public FPSubscriptionListPV getEWayCheckoutToken(FPSubscriptionListPV fpSubscriptionListPV) {
	    return eWayGateway.getCheckoutToken(fpSubscriptionListPV);
	}

	@Override
	public FPSubscriptionListPV getEWayCheckoutRef(FPSubscriptionListPV fpSubscriptionListPV) {
		return eWayGateway.getCheckoutRef(fpSubscriptionListPV);
	}

	public Collection<SubscriptionMasterPV> getSubscriptionDetails()
	{
	
		final Collection<SubscriptionMaster> subscriptions = subscriptionMasterDAO.getAllEntities();
		return new AbstractTransformer<SubscriptionMaster, SubscriptionMasterPV>() {
			@Override
			public SubscriptionMasterPV transform(final SubscriptionMaster subscription) {
				final SubscriptionMasterPV masterPV = new SubscriptionMasterPV();
				masterPV.setId(subscription.getId());
				masterPV.setPlanFrequency(subscription.getPlanFrequency());
				masterPV.setType(subscription.getType());
				masterPV.setRate(subscription.getRate());
				masterPV.setStartDate(new Date());
				
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				c.set(Calendar.YEAR, c.get(Calendar.YEAR)+subscription.getPlanFrequency().intValue());
				Date toDate = c.getTime();
				masterPV.setEndDate(toDate);
				
				try {
				
					if(subscription.getCountry()!=null)
					{
						CountryPV countryPV=new CountryPV();
						BeanUtils.copyProperties(countryPV, subscription.getCountry());
						masterPV.setCountryPV(countryPV);
					}
					
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				return masterPV;
			}
		}.transform(subscriptions);
	}
	

	public void persistToken(FPSubscriptionListPV persistPV)
	{
		FplSubscription fplSubscription=subscriptionDAO.get(Long.parseLong(persistPV.getId()));
		
		fplSubscription.getPaymentTransaction().setTransactionToken(persistPV.getToken());
		
		subscriptionDAO.saveOrUpdate(fplSubscription, true);
	}
	

	
	
	public void persistTransactionDetails(FPSubscriptionListPV persistPV)
	{
		FplSubscription fplSubscription=subscriptionDAO.get(Long.parseLong(persistPV.getId()));
		fplSubscription.setActivityFlag(persistPV.getStatus());
		fplSubscription.getPaymentTransaction().setTransactionDate(new Date());
		fplSubscription.getPaymentTransaction().setTransactionPayerId(persistPV.getTransactionPayerId());
		fplSubscription.getPaymentTransaction().setTransactionRef(persistPV.getTransactionRef());
		fplSubscription.getPaymentTransaction().setStatus(persistPV.getPaymentStatus());
		
		subscriptionDAO.saveOrUpdate(fplSubscription, true);
	}
	
	
	public Long getSubscriptionMaxId()
	{
		return subscriptionDAO.getMaxId()+1;
	}
	
	public Fpldb getFPLDB()
	{
		Fpldb fpldb=new Fpldb();
		Collection<Fpldb> list=fpldbDAO.getAllEntities();
		if(list!=null && !list.isEmpty())
		{
			fpldb=list.iterator().next();
		}
	  return fpldb;	
	}
	
	private FPSubscriptionListPV populateSubscriptionAmount(FPSubscriptionListPV fpSubscriptionListPV,CountryPV countryPV)
	{
		
		Float subAmt=Float.parseFloat(fpSubscriptionListPV.getSubscriptionMasterPV().getRate().toString());
		Float taxAmount=CountryUtils.calculateTax(countryPV, subAmt);
		Float totalAmt=subAmt+taxAmount;
		fpSubscriptionListPV.setSubscriptionAmount(subAmt);
		fpSubscriptionListPV.setSubscriptionTaxAmount(taxAmount);
		fpSubscriptionListPV.setSubscriptionTotalAmount(totalAmt);
		
		return fpSubscriptionListPV;
		
	}

	@Override
	public Collection<FPSubscriptionListPV> getAllFplSubscription(SearchProfilePV searchProfilePV) {
		
		Collection<FPSubscriptionListPV> listSubscriptionListPVs=new ArrayList<FPSubscriptionListPV>();
		if(searchProfilePV.getFplId()!=null && searchProfilePV.getFplId()>0)
		{
			FinancialPlanner financialPlanner=financialPlannerDAO.get(searchProfilePV.getFplId());

			Collection<FplSubscription> fplSubscriptions=subscriptionDAO.getFPLEntities(financialPlanner.getId());
			
			if(fplSubscriptions!=null && !fplSubscriptions.isEmpty())
			{
				for(FplSubscription fplSubscription:fplSubscriptions)
				{
					FPSubscriptionListPV fpSubscriptionListPV=subscriptionPVTransformer.transform(fplSubscription);
					fpSubscriptionListPV.setName(financialPlanner.getPersonalData().getName());
					listSubscriptionListPVs.add(fpSubscriptionListPV);
					
				}
			}
		}
		else if(searchProfilePV.getSubScriptionId()!=null && searchProfilePV.getSubScriptionId()>0)
		{
		
			FplSubscription subscription=subscriptionDAO.get(searchProfilePV.getSubScriptionId());
			FPSubscriptionListPV fpSubscriptionListPV=subscriptionPVTransformer.transform(subscription);
			
			FinancialPlanner financialPlanner=financialPlannerDAO.get(subscription.getFplId());
			fpSubscriptionListPV.setName(financialPlanner.getPersonalData().getName());
			listSubscriptionListPVs.add(fpSubscriptionListPV);
			
		}else if(StringUtil.isNotEmpty(searchProfilePV.getName()))
		{
			Collection<FinancialPlanner> financialPlanners=financialPlannerDAO.getFinancialPlannerList(searchProfilePV);
			if(financialPlanners!=null && !financialPlanners.isEmpty())
			{
				for(FinancialPlanner financialPlanner:financialPlanners)
				{
					Collection<FplSubscription> fplSubscriptions=subscriptionDAO.getFPLEntities(financialPlanner.getId());
		
					if(fplSubscriptions!=null && !fplSubscriptions.isEmpty())
					{
						for(FplSubscription fplSubscription:fplSubscriptions)
						{
							FPSubscriptionListPV fpSubscriptionListPV=subscriptionPVTransformer.transform(fplSubscription);
							fpSubscriptionListPV.setName(financialPlanner.getPersonalData().getName());
							listSubscriptionListPVs.add(fpSubscriptionListPV);
							
						}
					}
				}	
			}	
		}
		
		return listSubscriptionListPVs;
	}

	@Override
	public void logAdminFPStatusUpdate(
			FPSubscriptionListPV oldFpSubscriptionListPV,
			FPSubscriptionListPV newFpSubscriptionListPV, String loginId) {
	
		SubscriptionPaymentTransactionLog log=new SubscriptionPaymentTransactionLog();
		StringBuffer buffer=new StringBuffer();
		buffer.append("User : "+loginId);
		buffer.append("has changed FPL :"+oldFpSubscriptionListPV.getFplId());
		buffer.append("status from:"+oldFpSubscriptionListPV.getStatusDesc());
		buffer.append("to :"+newFpSubscriptionListPV.getStatusDesc());
		
		log.setNote(buffer.toString());
		transactionLogDAO.save(log, true);
	}
	
	
	
	
}
