package com.fpl.core.paymentgateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.eway.payment.rapid.sdk.RapidClient;
import com.eway.payment.rapid.sdk.RapidSDK;
import com.eway.payment.rapid.sdk.beans.external.LineItem;
import com.eway.payment.rapid.sdk.beans.external.PaymentDetails;
import com.eway.payment.rapid.sdk.beans.external.PaymentMethod;
import com.eway.payment.rapid.sdk.beans.external.Transaction;
import com.eway.payment.rapid.sdk.beans.external.TransactionType;
import com.eway.payment.rapid.sdk.output.CreateTransactionResponse;
import com.eway.payment.rapid.sdk.output.QueryTransactionResponse;
import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.subscription.SubscriptionStatusType;
import com.fpl.subscription.TransactionStatusType;
import com.fpl.subscription.audit.SubscriptionPaymentTransactionLog;

@Component
public class EWayGateway extends AbstractPaymentGateway{

	private String ACCESS_CODE_TEXT="?AccessCode=";
	
	@Override
	public FPSubscriptionListPV getCheckoutRef(
			FPSubscriptionListPV fpSubscriptionListPV) {
		SubscriptionPaymentTransactionLog log=buildLog(fpSubscriptionListPV);	
		log.setTransactionToken(fpSubscriptionListPV.getToken());
		

		String apiKey = "60CF3Ck77kO2wSavjNRrdSU2GdW369MKBmNf9hBhqTf5EhGhO8gIE//K8qwg7oDBBs0PUF";
	//	String password = "vsYUQsKt";
		String password = "eway007";
		String rapidEndpoint = "https://api.sandbox.ewaypayments.com";

		RapidClient client = RapidSDK.newRapidClient(apiKey, password, rapidEndpoint);

		 QueryTransactionResponse response = 
	        	    client.queryTransaction(fpSubscriptionListPV.getToken());
	        
	        
	        if (response.getTransactionStatus().isStatus()) {
	            System.out.println("Payment successful! ID: " + response.getTransactionStatus().getTransactionID());
				fpSubscriptionListPV.setTransactionRef(String.valueOf(response.getTransactionStatus().getTransactionID()));
				fpSubscriptionListPV.setStatus(SubscriptionStatusType.AC.name());
				fpSubscriptionListPV.setPaymentStatus(TransactionStatusType.PR.name());
				log.setTransactionRef(String.valueOf(response.getTransactionStatus().getTransactionID()));

	        } else {
	            String[] errorcodes = response.getTransactionStatus().getProcessingDetails().getResponseMessage().split(", ");
	            StringBuffer buffer=new StringBuffer();
	            for (String errorcode: errorcodes) {
	                System.out.println("Response Messages: " 
	                    + RapidSDK.userDisplayMessage(errorcode, "en"));
	                buffer.append(RapidSDK.userDisplayMessage(errorcode, "en"));
	            }
	            
				log.setNote(buffer.toString());
				fpSubscriptionListPV.setStatus(SubscriptionStatusType.IN.name());
				fpSubscriptionListPV.setPaymentStatus(TransactionStatusType.IF.name());
				

	         
	        }

		transactionLogDAO.save(log, true);
		return fpSubscriptionListPV;

	}

	@Override
	public FPSubscriptionListPV getCheckoutToken(
			FPSubscriptionListPV fpSubscriptionListPV) {
		String token="";
		
		Float subAmt=fpSubscriptionListPV.getSubscriptionAmount();
		Float taxAmount=fpSubscriptionListPV.getSubscriptionTaxAmount();
		Float totalAmt=fpSubscriptionListPV.getSubscriptionTotalAmount();
		
		
		SubscriptionPaymentTransactionLog log=buildLog(fpSubscriptionListPV);
		log.setMode(fpSubscriptionListPV.getMode());
		
		
		String apiKey = "60CF3Ck77kO2wSavjNRrdSU2GdW369MKBmNf9hBhqTf5EhGhO8gIE//K8qwg7oDBBs0PUF";
	//	String password = "vsYUQsKt";
		String password = "eway007";
		String rapidEndpoint = "https://api.sandbox.ewaypayments.com";

		RapidClient client = RapidSDK.newRapidClient(apiKey, password, rapidEndpoint);

        Transaction transaction = new Transaction();
        transaction.setPartnerID("9889897");
        transaction.setCancelURL("");
        transaction.setCapture(true);
        transaction.setCheckoutPayment(true);
        transaction.setRedirectURL("http://localhost:8080/FPLWeb/payNowSubsription.do?ACTION=EWAYCHECKOUT&fplSubId="+fpSubscriptionListPV.getId());
        transaction.setDeviceID("546545");
        transaction.setTransactionType(TransactionType.Purchase);

        List<String> listOption = new ArrayList<String>();
        listOption.add("Option1");
        transaction.setOptions(listOption);
        List<LineItem> listItem = new ArrayList<LineItem>();
        LineItem item = new LineItem();
       
        item.setDescription(fpSubscriptionListPV.getSubscriptionMasterPV().getType());
        
        item.setQuantity(1);
        item.setUnitCost(subAmt.intValue());
        item.setTotalTax(taxAmount.intValue());
        item.setTotal(totalAmt.intValue());
        
        listItem.add(item);
        transaction.setLineItems(listItem);

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setCurrencyCode("AUD");
        paymentDetails.setInvoiceDescription(fpSubscriptionListPV.getSubscriptionMasterPV().getType());
        paymentDetails.setInvoiceNumber(fpSubscriptionListPV.getInvoiceNumber());
        
        paymentDetails.setTotalAmount(totalAmt.intValue());
     
        transaction.setPaymentDetails(paymentDetails);
        transaction.setCustomView("bootstrap");
        transaction.setHeaderText("FPL");
        transaction.setCustomerReadOnly(false);
        transaction.setLogoUrl("https://mysite.com/images/logo4eway.jpg");
        transaction.setVerifyCustomerEmail(false);
        transaction.setVerifyCustomerPhone(false);
        transaction.setLanguage("EN");
        transaction.setCheckoutPayment(false);
        CreateTransactionResponse res = client.create(PaymentMethod.ResponsiveShared, transaction);
        
		if (!res.getErrors().isEmpty()) {
			StringBuffer buffer=new StringBuffer();
	    for (String errorcode: res.getErrors()) {
	        System.out.println("Error Message: " 
	            + RapidSDK.userDisplayMessage(errorcode, "en"));
	        buffer.append("Error Message: " 
	            + RapidSDK.userDisplayMessage(errorcode, "en"));
	    	}
	    
	    log.setNote("Failed::");
		log.setNote(buffer.toString());
		fpSubscriptionListPV.setStatus(SubscriptionStatusType.IP.name());

		}else
		{
			System.out.println("EC Token:" + res.getSharedPaymentUrl());
			
			String url=res.getSharedPaymentUrl();
			
			token=url.substring(url.indexOf(ACCESS_CODE_TEXT)+ACCESS_CODE_TEXT.length());
			log.setNote("Sucess:: Payment Transcation Token Created");
			log.setTransactionToken(token);
			fpSubscriptionListPV.setStatus(SubscriptionStatusType.IP.name());
			fpSubscriptionListPV.setToken(token);
			fpSubscriptionListPV.setPaymentUrl(url);
		}
	
		transactionLogDAO.save(log, true);
		
		return fpSubscriptionListPV;

	}


}
