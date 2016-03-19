package com.fpl.country;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Country {

	private Long id;
	private String name;
	private String isoCode;
	private String currency;        
	private String dialingCode;   
	private Float saleTaxRate;
	private String particular1;
	private String particular2;
	private String particular3;   
	private String particular4;		   
	private String governingBody;  
	private String govBodyWebsite;
	private String note;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the isoCode
	 */
	public String getIsoCode() {
		return isoCode;
	}

	/**
	 * @param isoCode the isoCode to set
	 */
	public void setIsoCode(final String isoCode) {
		this.isoCode = isoCode;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	/**
	 * @return the dialingCode
	 */
	public String getDialingCode() {
		return dialingCode;
	}

	/**
	 * @param dialingCode the dialingCode to set
	 */
	public void setDialingCode(final String dialingCode) {
		this.dialingCode = dialingCode;
	}

	/**
	 * @return the saleTaxRate
	 */
	public Float getSaleTaxRate() {
		return saleTaxRate;
	}

	/**
	 * @param saleTaxRate the saleTaxRate to set
	 */
	public void setSaleTaxRate(final Float saleTaxRate) {
		this.saleTaxRate = saleTaxRate;
	}

	/**
	 * @return the particular1
	 */
	public String getParticular1() {
		return particular1;
	}

	/**
	 * @param particular1 the particular1 to set
	 */
	public void setParticular1(final String particular1) {
		this.particular1 = particular1;
	}

	/**
	 * @return the particular2
	 */
	public String getParticular2() {
		return particular2;
	}

	/**
	 * @param particular2 the particular2 to set
	 */
	public void setParticular2(final String particular2) {
		this.particular2 = particular2;
	}

	/**
	 * @return the particular3
	 */
	public String getParticular3() {
		return particular3;
	}

	/**
	 * @param particular3 the particular3 to set
	 */
	public void setParticular3(final String particular3) {
		this.particular3 = particular3;
	}

	/**
	 * @return the particular4
	 */
	public String getParticular4() {
		return particular4;
	}

	/**
	 * @param particular4 the particular4 to set
	 */
	public void setParticular4(final String particular4) {
		this.particular4 = particular4;
	}

	/**
	 * @return the governingBody
	 */
	public String getGoverningBody() {
		return governingBody;
	}

	/**
	 * @param governingBody the governingBody to set
	 */
	public void setGoverningBody(final String governingBody) {
		this.governingBody = governingBody;
	}

	/**
	 * @return the govBodyWebsite
	 */
	public String getGovBodyWebsite() {
		return govBodyWebsite;
	}

	/**
	 * @param govBodyWebsite the govBodyWebsite to set
	 */
	public void setGovBodyWebsite(final String govBodyWebsite) {
		this.govBodyWebsite = govBodyWebsite;
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
