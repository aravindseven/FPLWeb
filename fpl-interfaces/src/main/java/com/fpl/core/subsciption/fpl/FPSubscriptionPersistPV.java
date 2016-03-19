package com.fpl.core.subsciption.fpl;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FPSubscriptionPersistPV {

	private String fpSubAgent;
	private String fpSubStartDate;
	private String fpSubEndDate;
	private String fpSubDuration;
	private String fpSubCountry;
	private String fpSubPaymentMode;
	private String fpSubPaymentTerms;
	private String fpSubPayReceipt;
	private String fpSubCurrency;
	private String fpSubAmountReceived;
	private String fpSubActiveFlag;
	private String fpSubComments;
	
	/**
	 * @return the fpSubAgent
	 */
	public String getFpSubAgent() {
		return fpSubAgent;
	}
	
	/**
	 * @param fpSubAgent the fpSubAgent to set
	 */
	public void setFpSubAgent(final String fpSubAgent) {
		this.fpSubAgent = fpSubAgent;
	}

	/**
	 * @return the fpSubStartDate
	 */
	public String getFpSubStartDate() {
		return fpSubStartDate;
	}

	/**
	 * @param fpSubStartDate the fpSubStartDate to set
	 */
	public void setFpSubStartDate(final String fpSubStartDate) {
		this.fpSubStartDate = fpSubStartDate;
	}

	/**
	 * @return the fpSubEndDate
	 */
	public String getFpSubEndDate() {
		return fpSubEndDate;
	}

	/**
	 * @param fpSubEndDate the fpSubEndDate to set
	 */
	public void setFpSubEndDate(final String fpSubEndDate) {
		this.fpSubEndDate = fpSubEndDate;
	}

	/**
	 * @return the fpSubDuration
	 */
	public String getFpSubDuration() {
		return fpSubDuration;
	}

	/**
	 * @param fpSubDuration the fpSubDuration to set
	 */
	public void setFpSubDuration(final String fpSubDuration) {
		this.fpSubDuration = fpSubDuration;
	}

	/**
	 * @return the fpSubCountry
	 */
	public String getFpSubCountry() {
		return fpSubCountry;
	}

	/**
	 * @param fpSubCountry the fpSubCountry to set
	 */
	public void setFpSubCountry(final String fpSubCountry) {
		this.fpSubCountry = fpSubCountry;
	}

	/**
	 * @return the fpSubPaymentMode
	 */
	public String getFpSubPaymentMode() {
		return fpSubPaymentMode;
	}

	/**
	 * @param fpSubPaymentMode the fpSubPaymentMode to set
	 */
	public void setFpSubPaymentMode(final String fpSubPaymentMode) {
		this.fpSubPaymentMode = fpSubPaymentMode;
	}

	/**
	 * @return the fpSubPaymentTerms
	 */
	public String getFpSubPaymentTerms() {
		return fpSubPaymentTerms;
	}

	/**
	 * @param fpSubPaymentTerms the fpSubPaymentTerms to set
	 */
	public void setFpSubPaymentTerms(final String fpSubPaymentTerms) {
		this.fpSubPaymentTerms = fpSubPaymentTerms;
	}

	/**
	 * @return the fpSubPayReceipt
	 */
	public String getFpSubPayReceipt() {
		return fpSubPayReceipt;
	}

	/**
	 * @param fpSubPayReceipt the fpSubPayReceipt to set
	 */
	public void setFpSubPayReceipt(final String fpSubPayReceipt) {
		this.fpSubPayReceipt = fpSubPayReceipt;
	}

	/**
	 * @return the fpSubCurrency
	 */
	public String getFpSubCurrency() {
		return fpSubCurrency;
	}

	/**
	 * @param fpSubCurrency the fpSubCurrency to set
	 */
	public void setFpSubCurrency(final String fpSubCurrency) {
		this.fpSubCurrency = fpSubCurrency;
	}

	/**
	 * @return the fpSubAmountReceived
	 */
	public String getFpSubAmountReceived() {
		return fpSubAmountReceived;
	}

	/**
	 * @param fpSubAmountReceived the fpSubAmountReceived to set
	 */
	public void setFpSubAmountReceived(final String fpSubAmountReceived) {
		this.fpSubAmountReceived = fpSubAmountReceived;
	}

	/**
	 * @return the fpSubActiveFlag
	 */
	public String getFpSubActiveFlag() {
		return fpSubActiveFlag;
	}

	/**
	 * @param fpSubActiveFlag the fpSubActiveFlag to set
	 */
	public void setFpSubActiveFlag(final String fpSubActiveFlag) {
		this.fpSubActiveFlag = fpSubActiveFlag;
	}

	/**
	 * @return the fpSubComments
	 */
	public String getFpSubComments() {
		return fpSubComments;
	}

	/**
	 * @param fpSubComments the fpSubComments to set
	 */
	public void setFpSubComments(final String fpSubComments) {
		this.fpSubComments = fpSubComments;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
