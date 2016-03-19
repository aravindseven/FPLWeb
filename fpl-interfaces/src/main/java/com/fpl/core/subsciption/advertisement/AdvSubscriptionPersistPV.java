package com.fpl.core.subsciption.advertisement;

import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AdvSubscriptionPersistPV {

	private String AdvSubscription;
	private String advSubStartDate;
	private String advSubEndDate;
	private Collection<String> advSubType;
	private String advSubCountry;
	private String advSubPaymentMode;
	private String advSubPaymentTerms;
	private String advSubPayReceipt;
	private String advSubCurrency;
	private String advSubAmountReceived;
	private String advSubRenewalFlag;
	private String advSubArtWorkStatus;
	private String advSubActiveFlag;
	private String advSubAgreedDate;
	private String advSubComments;
	
	/**
	 * @return the advSubActiveFlag
	 */
	public String getAdvSubActiveFlag() {
		return advSubActiveFlag;
	}
	
	/**
	 * @param advSubActiveFlag the advSubActiveFlag to set
	 */
	public void setAdvSubActiveFlag(final String advSubActiveFlag) {
		this.advSubActiveFlag = advSubActiveFlag;
	}

	/**
	 * @return the advSubscription
	 */
	public String getAdvSubscription() {
		return AdvSubscription;
	}

	/**
	 * @param advSubscription the advSubscription to set
	 */
	public void setAdvSubscription(final String advSubscription) {
		AdvSubscription = advSubscription;
	}

	/**
	 * @return the advSubStartDate
	 */
	public String getAdvSubStartDate() {
		return advSubStartDate;
	}

	/**
	 * @param advSubStartDate the advSubStartDate to set
	 */
	public void setAdvSubStartDate(final String advSubStartDate) {
		this.advSubStartDate = advSubStartDate;
	}

	/**
	 * @return the advSubEndDate
	 */
	public String getAdvSubEndDate() {
		return advSubEndDate;
	}

	/**
	 * @param advSubEndDate the advSubEndDate to set
	 */
	public void setAdvSubEndDate(final String advSubEndDate) {
		this.advSubEndDate = advSubEndDate;
	}

	/**
	 * @return the advSubType
	 */
	public Collection<String> getAdvSubType() {
		return advSubType;
	}

	/**
	 * @param advSubType the advSubType to set
	 */
	public void setAdvSubType(final Collection<String> advSubType) {
		this.advSubType = advSubType;
	}

	/**
	 * @return the advSubCountry
	 */
	public String getAdvSubCountry() {
		return advSubCountry;
	}

	/**
	 * @param advSubCountry the advSubCountry to set
	 */
	public void setAdvSubCountry(final String advSubCountry) {
		this.advSubCountry = advSubCountry;
	}

	/**
	 * @return the advSubPaymentMode
	 */
	public String getAdvSubPaymentMode() {
		return advSubPaymentMode;
	}

	/**
	 * @param advSubPaymentMode the advSubPaymentMode to set
	 */
	public void setAdvSubPaymentMode(final String advSubPaymentMode) {
		this.advSubPaymentMode = advSubPaymentMode;
	}

	/**
	 * @return the advSubPaymentTerms
	 */
	public String getAdvSubPaymentTerms() {
		return advSubPaymentTerms;
	}

	/**
	 * @param advSubPaymentTerms the advSubPaymentTerms to set
	 */
	public void setAdvSubPaymentTerms(final String advSubPaymentTerms) {
		this.advSubPaymentTerms = advSubPaymentTerms;
	}

	/**
	 * @return the advSubPayReceipt
	 */
	public String getAdvSubPayReceipt() {
		return advSubPayReceipt;
	}

	/**
	 * @param advSubPayReceipt the advSubPayReceipt to set
	 */
	public void setAdvSubPayReceipt(final String advSubPayReceipt) {
		this.advSubPayReceipt = advSubPayReceipt;
	}

	/**
	 * @return the advSubCurrency
	 */
	public String getAdvSubCurrency() {
		return advSubCurrency;
	}

	/**
	 * @param advSubCurrency the advSubCurrency to set
	 */
	public void setAdvSubCurrency(final String advSubCurrency) {
		this.advSubCurrency = advSubCurrency;
	}

	/**
	 * @return the advSubAmountReceived
	 */
	public String getAdvSubAmountReceived() {
		return advSubAmountReceived;
	}

	/**
	 * @param advSubAmountReceived the advSubAmountReceived to set
	 */
	public void setAdvSubAmountReceived(final String advSubAmountReceived) {
		this.advSubAmountReceived = advSubAmountReceived;
	}

	/**
	 * @return the advSubRenewalFlag
	 */
	public String getAdvSubRenewalFlag() {
		return advSubRenewalFlag;
	}

	/**
	 * @param advSubRenewalFlag the advSubRenewalFlag to set
	 */
	public void setAdvSubRenewalFlag(final String advSubRenewalFlag) {
		this.advSubRenewalFlag = advSubRenewalFlag;
	}

	/**
	 * @return the advSubArtWorkStatus
	 */
	public String getAdvSubArtWorkStatus() {
		return advSubArtWorkStatus;
	}

	/**
	 * @param advSubArtWorkStatus the advSubArtWorkStatus to set
	 */
	public void setAdvSubArtWorkStatus(final String advSubArtWorkStatus) {
		this.advSubArtWorkStatus = advSubArtWorkStatus;
	}

	/**
	 * @return the advSubAgreedDate
	 */
	public String getAdvSubAgreedDate() {
		return advSubAgreedDate;
	}

	/**
	 * @param advSubAgreedDate the advSubAgreedDate to set
	 */
	public void setAdvSubAgreedDate(final String advSubAgreedDate) {
		this.advSubAgreedDate = advSubAgreedDate;
	}

	/**
	 * @return the advSubComments
	 */
	public String getAdvSubComments() {
		return advSubComments;
	}

	/**
	 * @param advSubComments the advSubComments to set
	 */
	public void setAdvSubComments(final String advSubComments) {
		this.advSubComments = advSubComments;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
