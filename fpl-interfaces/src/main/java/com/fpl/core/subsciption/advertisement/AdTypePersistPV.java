package com.fpl.core.subsciption.advertisement;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AdTypePersistPV {

	private String advType;
	private String country;
	private String mediaWeb;
	private String mediaMobile;
	private String SubRate;
	private String pStartDate;
	private String pEndDate;
	private String FileTypes;
	private String FileSize;
	private String Dimension;
	private String SubDeadline;
	private String ItemSubmit;
	private String ActiveFlag;
	private String DiscountPolicy;
	private String discountPercentage;
	private String comments;
	
	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return ActiveFlag;
	}
	
	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		ActiveFlag = activeFlag;
	}

	/**
	 * @return the advType
	 */
	public String getAdvType() {
		return advType;
	}

	/**
	 * @param advType the advType to set
	 */
	public void setAdvType(final String advType) {
		this.advType = advType;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * @return the mediaWeb
	 */
	public String getMediaWeb() {
		return mediaWeb;
	}

	/**
	 * @param mediaWeb the mediaWeb to set
	 */
	public void setMediaWeb(final String mediaWeb) {
		this.mediaWeb = mediaWeb;
	}

	/**
	 * @return the mediaMobile
	 */
	public String getMediaMobile() {
		return mediaMobile;
	}

	/**
	 * @param mediaMobile the mediaMobile to set
	 */
	public void setMediaMobile(final String mediaMobile) {
		this.mediaMobile = mediaMobile;
	}

	/**
	 * @return the subRate
	 */
	public String getSubRate() {
		return SubRate;
	}

	/**
	 * @param subRate the subRate to set
	 */
	public void setSubRate(final String subRate) {
		SubRate = subRate;
	}

	/**
	 * @return the pStartDate
	 */
	public String getpStartDate() {
		return pStartDate;
	}

	/**
	 * @param pStartDate the pStartDate to set
	 */
	public void setpStartDate(final String pStartDate) {
		this.pStartDate = pStartDate;
	}

	/**
	 * @return the pEndDate
	 */
	public String getpEndDate() {
		return pEndDate;
	}

	/**
	 * @param pEndDate the pEndDate to set
	 */
	public void setpEndDate(final String pEndDate) {
		this.pEndDate = pEndDate;
	}

	/**
	 * @return the fileTypes
	 */
	public String getFileTypes() {
		return FileTypes;
	}

	/**
	 * @param fileTypes the fileTypes to set
	 */
	public void setFileTypes(final String fileTypes) {
		FileTypes = fileTypes;
	}

	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return FileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(final String fileSize) {
		FileSize = fileSize;
	}

	/**
	 * @return the dimension
	 */
	public String getDimension() {
		return Dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(final String dimension) {
		Dimension = dimension;
	}

	/**
	 * @return the subDeadline
	 */
	public String getSubDeadline() {
		return SubDeadline;
	}

	/**
	 * @param subDeadline the subDeadline to set
	 */
	public void setSubDeadline(final String subDeadline) {
		SubDeadline = subDeadline;
	}

	/**
	 * @return the itemSubmit
	 */
	public String getItemSubmit() {
		return ItemSubmit;
	}

	/**
	 * @param itemSubmit the itemSubmit to set
	 */
	public void setItemSubmit(final String itemSubmit) {
		ItemSubmit = itemSubmit;
	}

	/**
	 * @return the discountPolicy
	 */
	public String getDiscountPolicy() {
		return DiscountPolicy;
	}

	/**
	 * @param discountPolicy the discountPolicy to set
	 */
	public void setDiscountPolicy(final String discountPolicy) {
		DiscountPolicy = discountPolicy;
	}

	/**
	 * @return the discountPercentage
	 */
	public String getDiscountPercentage() {
		return discountPercentage;
	}

	/**
	 * @param discountPercentage the discountPercentage to set
	 */
	public void setDiscountPercentage(final String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
