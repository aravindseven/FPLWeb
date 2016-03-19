package com.fpl.core.subsciption.fpl;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FPSubscriptionListPV {

	private String id;
	private String idText;
	private String invoiceNumber;
	private String fplId;
	private String subMasterId;
	private String Name;
	private String ContactName;
	private String Status;
	private Date validationDate;
	private Date startDate;
	private Date endDate;
	private String token;
	private String transactionRef;
	private String transactionPayerId;
	private String paymentUrl;
	private String paymentStatus;
	private String paymentStatusDesc;
	private String statusDesc;
	private String mode;
	private Float subscriptionAmount;
	private Float subscriptionTaxAmount;
	private Float subscriptionTotalAmount;
	

	private SubscriptionMasterPV subscriptionMasterPV;
	private Collection<SubscriptionMasterPV>  masterPVs ;
	
	 
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		Name = name;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return ContactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(final String contactName) {
		ContactName = contactName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return Status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		Status = status;
	}
	
	
	
	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}
	
	
	

	public String getFplId() {
		return fplId;
	}

	public void setFplId(String fplId) {
		this.fplId = fplId;
	}

	public SubscriptionMasterPV getSubscriptionMasterPV() {
		return subscriptionMasterPV;
	}

	public void setSubscriptionMasterPV(SubscriptionMasterPV subscriptionMasterPV) {
		this.subscriptionMasterPV = subscriptionMasterPV;
	}

	public Collection<SubscriptionMasterPV> getMasterPVs() {
		return masterPVs;
	}

	public void setMasterPVs(Collection<SubscriptionMasterPV> masterPVs) {
		this.masterPVs = masterPVs;
	}

	public String getSubMasterId() {
		return subMasterId;
	}

	public void setSubMasterId(String subMasterId) {
		this.subMasterId = subMasterId;
	}
	
	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}
	
	


	public String getTransactionPayerId() {
		return transactionPayerId;
	}

	public void setTransactionPayerId(String transactionPayerId) {
		this.transactionPayerId = transactionPayerId;
	}
	
	

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	

	public String getIdText() {
		return idText;
	}

	public void setIdText(String idText) {
		this.idText = idText;
	}
	
	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	

	public Float getSubscriptionAmount() {
		return subscriptionAmount;
	}

	public void setSubscriptionAmount(Float subscriptionAmount) {
		this.subscriptionAmount = subscriptionAmount;
	}

	public Float getSubscriptionTaxAmount() {
		return subscriptionTaxAmount;
	}

	public void setSubscriptionTaxAmount(Float subscriptionTaxAmount) {
		this.subscriptionTaxAmount = subscriptionTaxAmount;
	}

	public Float getSubscriptionTotalAmount() {
		return subscriptionTotalAmount;
	}

	public void setSubscriptionTotalAmount(Float subscriptionTotalAmount) {
		this.subscriptionTotalAmount = subscriptionTotalAmount;
	}
	
	

	public String getPaymentStatusDesc() {
		return paymentStatusDesc;
	}

	public void setPaymentStatusDesc(String paymentStatusDesc) {
		this.paymentStatusDesc = paymentStatusDesc;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
