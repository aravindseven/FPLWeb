package com.fpl.profile.personal;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PersonalDataPV {

	private String firstName;
	private String lastName;
	private String gender;
	private String dob;
	private String idProof;
	private String mobile;
	private String landLine;
	private String email;
	private String proofNum;
	private String relation;
	private String confPreference;	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(final String gender) {
		this.gender = gender;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(final String dob) {
		this.dob = dob;
	}

	/**
	 * @return the idProof
	 */
	public String getIdProof() {
		return idProof;
	}

	/**
	 * @param idProof the idProof to set
	 */
	public void setIdProof(final String idProof) {
		this.idProof = idProof;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the landLine
	 */
	public String getLandLine() {
		return landLine;
	}

	/**
	 * @param landLine the landLine to set
	 */
	public void setLandLine(final String landLine) {
		this.landLine = landLine;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}
	
	/**
	 * @return the proofNum
	 */
	public String getProofNum() {
		return proofNum;
	}

	/**
	 * @param proofNum the proofNum to set
	 */
	public void setProofNum(final String proofNum) {
		this.proofNum = proofNum;
	}
	
	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(final String relation) {
		this.relation = relation;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
