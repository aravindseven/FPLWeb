package com.fpl.core.paymentgateway;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.ErrorType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentInfoType;
import urn.ebay.apis.eBLBaseComponents.SellerDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

import com.fpl.core.subsciption.fpl.CountryPV;
import com.fpl.core.subsciption.fpl.FPSubscriptionListPV;
import com.fpl.subscription.SubscriptionStatusType;
import com.fpl.subscription.TransactionStatusType;
import com.fpl.subscription.audit.SubscriptionPaymentTransactionLog;

@Component
public class PayPalGateway extends AbstractPaymentGateway{

	@Value("classpath:com/fpl/controller/admin/subscription/sdk_config.properties")
    private Resource configFile;
	

	@Override
	public FPSubscriptionListPV getCheckoutRef(
			FPSubscriptionListPV fpSubscriptionListPV) {
		SubscriptionPaymentTransactionLog log=buildLog(fpSubscriptionListPV);	
		log.setTransactionToken(fpSubscriptionListPV.getToken());
		log.setTransactionPayerId(fpSubscriptionListPV.getTransactionPayerId());

		// ## DoExpressCheckoutPaymentReq
		DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();

		DoExpressCheckoutPaymentRequestDetailsType doExpressCheckoutPaymentRequestDetails = new DoExpressCheckoutPaymentRequestDetailsType();

		// The timestamped token value that was returned in the
		// `SetExpressCheckout` response and passed in the
		// `GetExpressCheckoutDetails` request.
		doExpressCheckoutPaymentRequestDetails.setToken(fpSubscriptionListPV.getToken());

		// Unique paypal buyer account identification number as returned in
		// `GetExpressCheckoutDetails` Response
		doExpressCheckoutPaymentRequestDetails.setPayerID(fpSubscriptionListPV.getTransactionPayerId());

		// ### Payment Information
		// list of information about the payment
		List<PaymentDetailsType> paymentDetailsList = new ArrayList<PaymentDetailsType>();

		// information about the first payment
		PaymentDetailsType paymentDetails1 = new PaymentDetailsType();

		// Total cost of the transaction to the buyer. If shipping cost and tax
		// charges are known, include them in this value. If not, this value
		// should be the current sub-total of the order. 
		// 
		// If the transaction includes one or more one-time purchases, this field must be equal to
		// the sum of the purchases. Set this field to 0 if the transaction does
		// not include a one-time purchase such as when you set up a billing
		// agreement for a recurring payment that is not immediately charged.
		// When the field is set to 0, purchase-specific fields are ignored.
		// 
		// * `Currency Code` - You must set the currencyID attribute to one of the
		// 3-character currency codes for any of the supported PayPal
		// currencies.
		// * `Amount`
		Float subAmt=fpSubscriptionListPV.getSubscriptionAmount();
		Float taxAmount=fpSubscriptionListPV.getSubscriptionTaxAmount();
		Float totalAmt=fpSubscriptionListPV.getSubscriptionTotalAmount();


		
		BasicAmountType orderTotal1 = new BasicAmountType(CurrencyCodeType.SGD,
				String.format( "%.2f",totalAmt));
		paymentDetails1.setOrderTotal(orderTotal1);

		// How you want to obtain payment. When implementing parallel payments,
		// this field is required and must be set to `Order`. When implementing
		// digital goods, this field is required and must be set to `Sale`. If the
		// transaction does not include a one-time purchase, this field is
		// ignored. It is one of the following values:
		// 
		// * `Sale` - This is a final sale for which you are requesting payment
		// (default).
		// * `Authorization` - This payment is a basic authorization subject to
		// settlement with PayPal Authorization and Capture.
		// * `Order` - This payment is an order authorization subject to
		// settlement with PayPal Authorization and Capture.
		// Note:
		// You cannot set this field to Sale in SetExpressCheckout request and
		// then change the value to Authorization or Order in the
		// DoExpressCheckoutPayment request. If you set the field to
		// Authorization or Order in SetExpressCheckout, you may set the field
		// to Sale.
		paymentDetails1.setPaymentAction(PaymentActionCodeType.ORDER);

		// Unique identifier for the merchant. For parallel payments, this field
		// is required and must contain the Payer Id or the email address of the
		// merchant.
		SellerDetailsType sellerDetails1 = new SellerDetailsType();
		sellerDetails1.setPayPalAccountID("rkseram-facilitator@gmail.com");
		paymentDetails1.setSellerDetails(sellerDetails1);

		// A unique identifier of the specific payment request, which is
		// required for parallel payments.
		paymentDetails1.setPaymentRequestID("PaymentRequest1");
		
		// Your URL for receiving Instant Payment Notification (IPN) about this transaction. If you do not specify this value in the request, the notification URL from your Merchant Profile is used, if one exists.
		paymentDetails1.setNotifyURL("http://localhost/ipn");

		
		paymentDetailsList.add(paymentDetails1);

		doExpressCheckoutPaymentRequestDetails
				.setPaymentDetails(paymentDetailsList);
		DoExpressCheckoutPaymentRequestType doExpressCheckoutPaymentRequest = new DoExpressCheckoutPaymentRequestType(
				doExpressCheckoutPaymentRequestDetails);
		doExpressCheckoutPaymentReq
				.setDoExpressCheckoutPaymentRequest(doExpressCheckoutPaymentRequest);

		// ## Creating service wrapper object
		// Creating service wrapper object to make API call and loading
		// configuration file for your credentials and endpoint
		PayPalAPIInterfaceServiceService service = null;
		try {
			service = new PayPalAPIInterfaceServiceService(configFile.getInputStream());
		} catch (IOException e) {
			System.out.println("Error Message : " + e.getMessage());
		}
		DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentResponse = null;
		try {
			// ## Making API call
			// Invoke the appropriate method corresponding to API in service
			// wrapper object
			 doExpressCheckoutPaymentResponse = service
					.doExpressCheckoutPayment(doExpressCheckoutPaymentReq);
		} catch (Exception e) {
			System.out.println("Error Message : " + e.getMessage());
		}

		// ## Accessing response parameters
		// You can access the response parameters using getter methods in
		// response object as shown below
		// ### Success values
		if (doExpressCheckoutPaymentResponse.getAck().getValue()
				.equalsIgnoreCase("success")) {

			// Transaction identification number of the transaction that was
			// created.
			// This field is only returned after a successful transaction
			// for DoExpressCheckout has occurred.
			if (doExpressCheckoutPaymentResponse
					.getDoExpressCheckoutPaymentResponseDetails()
					.getPaymentInfo() != null) {
				Iterator<PaymentInfoType> paymentInfoIterator = doExpressCheckoutPaymentResponse
						.getDoExpressCheckoutPaymentResponseDetails()
						.getPaymentInfo().iterator();
				while (paymentInfoIterator.hasNext()) {
					PaymentInfoType paymentInfo = paymentInfoIterator
							.next();
					System.out.println("Transaction ID : "
							+ paymentInfo.getTransactionID());
					fpSubscriptionListPV.setTransactionRef(paymentInfo.getTransactionID());
					fpSubscriptionListPV.setStatus(SubscriptionStatusType.AC.name());
					fpSubscriptionListPV.setPaymentStatus(TransactionStatusType.PR.name());
					log.setTransactionRef(paymentInfo.getTransactionID());
				}
			}
		}
		// ### Error Values
		// Access error values from error list using getter methods
		else {
			List<ErrorType> errorList = doExpressCheckoutPaymentResponse
					.getErrors();
			StringBuffer buffer=new StringBuffer();
			for(ErrorType errorType:errorList)
			{
				buffer.append(errorType.getLongMessage());
			}
			log.setNote(buffer.toString());
			fpSubscriptionListPV.setStatus(SubscriptionStatusType.IN.name());
			fpSubscriptionListPV.setPaymentStatus(TransactionStatusType.IF.name());
			System.out.println("API Error Message : "
					+ errorList.get(0).getLongMessage());
		}

		transactionLogDAO.save(log, true);
		return fpSubscriptionListPV;

	}

	
	@Override
	public FPSubscriptionListPV getCheckoutToken(
			FPSubscriptionListPV fpSubscriptionListPV) {
		String token="";
		SubscriptionPaymentTransactionLog log=buildLog(fpSubscriptionListPV);
		log.setMode(fpSubscriptionListPV.getMode());
		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		paymentDetails.setPaymentAction(PaymentActionCodeType.SALE);
		PaymentDetailsItemType item = new PaymentDetailsItemType();
		BasicAmountType amt = new BasicAmountType();

		CountryPV countryPV=fpSubscriptionListPV.getSubscriptionMasterPV().getCountryPV();
		String currency=countryPV.getCurrency();
		
		amt.setCurrencyID(CurrencyCodeType.fromValue(currency));
		
		Float subAmt=fpSubscriptionListPV.getSubscriptionAmount();
		Float taxAmount=fpSubscriptionListPV.getSubscriptionTaxAmount();
		Float totalAmt=fpSubscriptionListPV.getSubscriptionTotalAmount();
		
		amt.setValue(String.format( "%.2f",subAmt));
		int itemQuantity = 1;
		item.setQuantity(itemQuantity);
		item.setName(fpSubscriptionListPV.getSubscriptionMasterPV().getType());
		item.setAmount(amt);
		
		BasicAmountType tax=new BasicAmountType();
		tax.setCurrencyID(CurrencyCodeType.fromValue(currency));
		tax.setValue(String.format( "%.2f",taxAmount));
		
		item.setTax(tax);
			

		List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();
		lineItems.add(item);
		paymentDetails.setPaymentDetailsItem(lineItems);
		BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setCurrencyID(CurrencyCodeType.fromValue(currency));

		orderTotal.setValue(String.format( "%.2f",totalAmt)); 
		
		
		paymentDetails.setOrderTotal(orderTotal);
		
		BasicAmountType orderTax = new BasicAmountType();
		orderTax.setCurrencyID(CurrencyCodeType.fromValue(currency));

		orderTax.setValue(String.format( "%.2f",taxAmount)); 

		BasicAmountType itemTotal = new BasicAmountType();
		itemTotal.setCurrencyID(CurrencyCodeType.fromValue(currency));

		itemTotal.setValue(String.format( "%.2f",subAmt)); 

		paymentDetails.setItemTotal(itemTotal);
		paymentDetails.setTaxTotal(orderTax);
		
		List<PaymentDetailsType> paymentDetailsList = new ArrayList<PaymentDetailsType>();
		paymentDetailsList.add(paymentDetails);

		SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetails = new SetExpressCheckoutRequestDetailsType();
		setExpressCheckoutRequestDetails.setReturnURL("http://localhost:8080/FPLWeb/payNowSubsription.do?ACTION=CHECKOUT&fplSubId="+fpSubscriptionListPV.getId());
		setExpressCheckoutRequestDetails.setCancelURL("http://localhost:8080/FPLWeb/payNowSubsription.do?ACTION=CANCEL&fplSubId="+fpSubscriptionListPV.getId());

		setExpressCheckoutRequestDetails.setPaymentDetails(paymentDetailsList);

		SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType(setExpressCheckoutRequestDetails);
		setExpressCheckoutRequest.setVersion("104.0");

		SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
		setExpressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutRequest);

		try {
			PayPalAPIInterfaceServiceService service =new PayPalAPIInterfaceServiceService(
					configFile.getInputStream());
			SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(setExpressCheckoutReq);
			if (setExpressCheckoutResponse.getAck().getValue()
					.equalsIgnoreCase("success")) {

				// ### Redirecting to PayPal for authorization
				// Once you get the "Success" response, needs to authorise the
				// transaction by making buyer to login into PayPal. For that,
				// need to construct redirect url using EC token from response.
				// For example,
				// `redirectURL="https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+setExpressCheckoutResponse.getToken();`

				// Express Checkout Token
				System.out.println("EC Token:" + setExpressCheckoutResponse.getToken());
				token=setExpressCheckoutResponse.getToken();
				log.setNote("Sucess:: Payment Transcation Token Created");
				log.setTransactionToken(token);
				fpSubscriptionListPV.setStatus(SubscriptionStatusType.IP.name());
				fpSubscriptionListPV.setToken(token);
			}
			// ### Error Values
			// Access error values from error list using getter methods
			else {
				List<ErrorType> errorList = setExpressCheckoutResponse.getErrors();
				StringBuffer buffer=new StringBuffer();
				for(ErrorType errorType:errorList)
				{
					buffer.append(errorType.getLongMessage());
				}
				log.setNote("Failed::");
				log.setNote(buffer.toString());
				fpSubscriptionListPV.setStatus(SubscriptionStatusType.IP.name());
				
				System.out.println("API Error Message : "
						+ errorList.get(0).getLongMessage());
				
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		transactionLogDAO.save(log, true);
		
		return fpSubscriptionListPV;

	}
	
	
	

	
}
