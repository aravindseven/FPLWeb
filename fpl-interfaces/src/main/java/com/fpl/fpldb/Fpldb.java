package com.fpl.fpldb;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Fpldb {

	private Long id;
	private String name;
	private String registrationId;
	private String location;
	private String proof;
	private String proofId;
	private String website;
	
	private String country;
	private String postalCode;
	private String block;
	private String buildingName;
	private String street;
	private String city;
	private String state;
	private String mobile;
	private String landline;
	private String email;
	
	private String primaryFirstName;
	private String primarySecondName;
	private String primaryMobile;
	private String primaryPosition;
	private String primaryProof;
	private String primaryProofId;
	
	private String altFirstName;
	private String altSecondName;
	private String altMobile;
	private String altPosition;
	private String altProof;
	private String altProofId;
	

	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getRegistrationId() {
		return registrationId;
	}



	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getProof() {
		return proof;
	}



	public void setProof(String proof) {
		this.proof = proof;
	}



	public String getProofId() {
		return proofId;
	}



	public void setProofId(String proofId) {
		this.proofId = proofId;
	}



	public String getWebsite() {
		return website;
	}



	public void setWebsite(String website) {
		this.website = website;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}



	public String getBlock() {
		return block;
	}



	public void setBlock(String block) {
		this.block = block;
	}



	public String getBuildingName() {
		return buildingName;
	}



	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getLandline() {
		return landline;
	}



	public void setLandline(String landline) {
		this.landline = landline;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPrimaryFirstName() {
		return primaryFirstName;
	}



	public void setPrimaryFirstName(String primaryFirstName) {
		this.primaryFirstName = primaryFirstName;
	}



	public String getPrimarySecondName() {
		return primarySecondName;
	}



	public void setPrimarySecondName(String primarySecondName) {
		this.primarySecondName = primarySecondName;
	}



	public String getPrimaryMobile() {
		return primaryMobile;
	}



	public void setPrimaryMobile(String primaryMobile) {
		this.primaryMobile = primaryMobile;
	}



	public String getPrimaryPosition() {
		return primaryPosition;
	}



	public void setPrimaryPosition(String primaryPosition) {
		this.primaryPosition = primaryPosition;
	}



	public String getPrimaryProof() {
		return primaryProof;
	}



	public void setPrimaryProof(String primaryProof) {
		this.primaryProof = primaryProof;
	}



	public String getPrimaryProofId() {
		return primaryProofId;
	}



	public void setPrimaryProofId(String primaryProofId) {
		this.primaryProofId = primaryProofId;
	}



	public String getAltFirstName() {
		return altFirstName;
	}



	public void setAltFirstName(String altFirstName) {
		this.altFirstName = altFirstName;
	}



	public String getAltSecondName() {
		return altSecondName;
	}



	public void setAltSecondName(String altSecondName) {
		this.altSecondName = altSecondName;
	}



	public String getAltMobile() {
		return altMobile;
	}



	public void setAltMobile(String altMobile) {
		this.altMobile = altMobile;
	}



	public String getAltPosition() {
		return altPosition;
	}



	public void setAltPosition(String altPosition) {
		this.altPosition = altPosition;
	}



	public String getAltProof() {
		return altProof;
	}



	public void setAltProof(String altProof) {
		this.altProof = altProof;
	}



	public String getAltProofId() {
		return altProofId;
	}



	public void setAltProofId(String altProofId) {
		this.altProofId = altProofId;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
