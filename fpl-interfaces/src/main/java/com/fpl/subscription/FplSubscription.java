package com.fpl.subscription;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FplSubscription {

	private Long id;
	private Long fplId;
	private Date startDate;
	private Date endDate;
	private Long subscriptionMasterId;
	private Integer duriation;
	private Long countryId;
	private String customerName;
	private String activityFlag;
	private String note;
	private Float subscriptionAmount;
	private Float subscriptionTaxAmount;
	private Float subscriptionTotalAmount;
	
	private PaymentTransaction paymentTransaction;
	private SubscriptionMaster subscriptionMaster;
	
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the fplId
	 */
	public Long getFplId() {
		return fplId;
	}

	/**
	 * @param fplId the fplId to set
	 */
	public void setFplId(final Long fplId) {
		this.fplId = fplId;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the subscriptionMasterId
	 */
	public Long getSubscriptionMasterId() {
		return subscriptionMasterId;
	}

	/**
	 * @param subscriptionMasterId the subscriptionMasterId to set
	 */
	public void setSubscriptionMasterId(final Long subscriptionMasterId) {
		this.subscriptionMasterId = subscriptionMasterId;
	}

	/**
	 * @return the duriation
	 */
	public Integer getDuriation() {
		return duriation;
	}

	/**
	 * @param duriation the duriation to set
	 */
	public void setDuriation(final Integer duriation) {
		this.duriation = duriation;
	}

	/**
	 * @return the companyId
	 */
	public Long getCountryId() {
		return countryId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCountryId(final Long companyId) {
		this.countryId = companyId;
	}

	/**
	 * @return the activityFlag
	 */
	public String getActivityFlag() {
		return activityFlag;
	}

	/**
	 * @param activityFlag the activityFlag to set
	 */
	public void setActivityFlag(final String activityFlag) {
		this.activityFlag = activityFlag;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(final String note) {
		this.note = note;
	}

	

	public PaymentTransaction getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	/**
	 * @return the subscriptionMaster
	 */
	public SubscriptionMaster getSubscriptionMaster() {
		return subscriptionMaster;
	}

	/**
	 * @param subscriptionMaster the subscriptionMaster to set
	 */
	public void setSubscriptionMaster(final SubscriptionMaster subscriptionMaster) {
		this.subscriptionMaster = subscriptionMaster;
	}
	
	
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
