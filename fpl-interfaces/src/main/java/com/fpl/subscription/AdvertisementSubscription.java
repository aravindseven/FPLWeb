package com.fpl.subscription;

import java.util.Date;
import java.util.Set;

import com.fpl.country.Country;

public class AdvertisementSubscription {

	private Long id;
	private Long advertisementTypeId;
	private String target;
	private Date startDate;
	private Date endDate;
	private Long countryId;
	private String media;
	private String renewal;
	private String activityFlag;
	private String workStatus;
	private Date submissionDate;
	private Date subscriptionDate;
	private String note;
	private Set<PaymentTransaction> transactionList;
	private AdvertisementType advertisementType;
	private Country country;
	
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
	 * @return the advertisementTypeId
	 */
	public Long getAdvertisementTypeId() {
		return advertisementTypeId;
	}

	/**
	 * @param advertisementTypeId the advertisementTypeId to set
	 */
	public void setAdvertisementTypeId(final Long advertisementTypeId) {
		this.advertisementTypeId = advertisementTypeId;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(final String target) {
		this.target = target;
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
	 * @return the countryId
	 */
	public Long getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(final Long countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the media
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(final String media) {
		this.media = media;
	}

	/**
	 * @return the renewal
	 */
	public String getRenewal() {
		return renewal;
	}

	/**
	 * @param renewal the renewal to set
	 */
	public void setRenewal(final String renewal) {
		this.renewal = renewal;
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
	 * @return the workStatus
	 */
	public String getWorkStatus() {
		return workStatus;
	}

	/**
	 * @param workStatus the workStatus to set
	 */
	public void setWorkStatus(final String workStatus) {
		this.workStatus = workStatus;
	}

	/**
	 * @return the submissionDate
	 */
	public Date getSubmissionDate() {
		return submissionDate;
	}

	/**
	 * @param submissionDate the submissionDate to set
	 */
	public void setSubmissionDate(final Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	/**
	 * @return the subscriptionDate
	 */
	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	/**
	 * @param subscriptionDate the subscriptionDate to set
	 */
	public void setSubscriptionDate(final Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
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

	/**
	 * @return the transactionList
	 */
	public Set<PaymentTransaction> getTransactionList() {
		return transactionList;
	}

	/**
	 * @param transactionList the transactionList to set
	 */
	public void setTransactionList(final Set<PaymentTransaction> transactionList) {
		this.transactionList = transactionList;
	}

	/**
	 * @return the advertisementType
	 */
	public AdvertisementType getAdvertisementType() {
		return advertisementType;
	}

	/**
	 * @param advertisementType the advertisementType to set
	 */
	public void setAdvertisementType(final AdvertisementType advertisementType) {
		this.advertisementType = advertisementType;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(final Country country) {
		this.country = country;
	}
}
