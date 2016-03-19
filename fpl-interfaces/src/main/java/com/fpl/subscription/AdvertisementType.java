package com.fpl.subscription;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.country.Country;

public class AdvertisementType {

	private Long id;                 
	private String type;               
	private Long countryid;              
	private String media;				
	private String subscriptionRate;	
	private Date startDate;			
	private Date endDate;			
	private String fileType;			
	private String fileSize;			
	private String dimension;			
	private String subscrptionDeadLine;		
	private String itemSubmit;		
	private Date registrationDate;	
	private String acticityFlag;		
	private String discountPolicy;	
	private String note;
	private Date insertedDate;
	private Date updateDate;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
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
	 * @return the subscriptionRate
	 */
	public String getSubscriptionRate() {
		return subscriptionRate;
	}

	/**
	 * @param subscriptionRate the subscriptionRate to set
	 */
	public void setSubscriptionRate(final String subscriptionRate) {
		this.subscriptionRate = subscriptionRate;
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
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(final String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(final String fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the dimension
	 */
	public String getDimension() {
		return dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(final String dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return the subscrptionDeadLine
	 */
	public String getSubscrptionDeadLine() {
		return subscrptionDeadLine;
	}

	/**
	 * @param subscrptionDeadLine the subscrptionDeadLine to set
	 */
	public void setSubscrptionDeadLine(final String subscrptionDeadLine) {
		this.subscrptionDeadLine = subscrptionDeadLine;
	}

	/**
	 * @return the itemSubmit
	 */
	public String getItemSubmit() {
		return itemSubmit;
	}

	/**
	 * @param itemSubmit the itemSubmit to set
	 */
	public void setItemSubmit(final String itemSubmit) {
		this.itemSubmit = itemSubmit;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(final Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the acticityFlag
	 */
	public String getActicityFlag() {
		return acticityFlag;
	}

	/**
	 * @param acticityFlag the acticityFlag to set
	 */
	public void setActicityFlag(final String acticityFlag) {
		this.acticityFlag = acticityFlag;
	}

	/**
	 * @return the discountPolicy
	 */
	public String getDiscountPolicy() {
		return discountPolicy;
	}

	/**
	 * @param discountPolicy the discountPolicy to set
	 */
	public void setDiscountPolicy(final String discountPolicy) {
		this.discountPolicy = discountPolicy;
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
	 * @return the countryid
	 */
	public Long getCountryid() {
		return countryid;
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

	/**
	 * @param countryid the countryid to set
	 */
	public void setCountryid(final Long countryid) {
		this.countryid = countryid;
	}
	
	/**
	 * @return the insertedDate
	 */
	public Date getInsertedDate() {
		return insertedDate;
	}

	/**
	 * @param insertedDate the insertedDate to set
	 */
	public void setInsertedDate(final Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(final Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
