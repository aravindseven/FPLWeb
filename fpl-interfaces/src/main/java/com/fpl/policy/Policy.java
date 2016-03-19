package com.fpl.policy;

import java.math.BigDecimal;
import java.util.Date;

import com.fpl.domain.Domain;
import com.fpl.status.Status;

public class Policy {

	private Long id;
	private Long requestId;
	private Long customerId;
	private Long fplId;
	private Long domainId;
	private Long companyId;
	private Long statusId;
	private String policyNumber;
	private Date policyDate;
	private Integer duriation;
	private Integer frequency;
	private BigDecimal amount;
	private Date renewalDate;
	private Integer advanceAlert;
	private String alertType;
	private String contactNumber;
	private String contactName;
	private String relation;
	private String idProof;
	private String note;
	private Date createdDate;
	private Domain domain;
	private Status status;
	
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
	 * @return the requestId
	 */
	public Long getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(final Long requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
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
	 * @return the domainId
	 */
	public Long getDomainId() {
		return domainId;
	}

	/**
	 * @param domainId the domainId to set
	 */
	public void setDomainId(final Long domainId) {
		this.domainId = domainId;
	}

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(final Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the statusId
	 */
	public Long getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(final Long statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the policyNumber
	 */
	public String getPolicyNumber() {
		return policyNumber;
	}

	/**
	 * @param policyNumber the policyNumber to set
	 */
	public void setPolicyNumber(final String policyNumber) {
		this.policyNumber = policyNumber;
	}

	/**
	 * @return the policyDate
	 */
	public Date getPolicyDate() {
		return policyDate;
	}

	/**
	 * @param policyDate the policyDate to set
	 */
	public void setPolicyDate(final Date policyDate) {
		this.policyDate = policyDate;
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
	 * @return the frequency
	 */
	public Integer getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(final Integer frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the renewalDate
	 */
	public Date getRenewalDate() {
		return renewalDate;
	}

	/**
	 * @param renewalDate the renewalDate to set
	 */
	public void setRenewalDate(final Date renewalDate) {
		this.renewalDate = renewalDate;
	}

	/**
	 * @return the advanceAlert
	 */
	public Integer getAdvanceAlert() {
		return advanceAlert;
	}

	/**
	 * @param advanceAlert the advanceAlert to set
	 */
	public void setAdvanceAlert(final Integer advanceAlert) {
		this.advanceAlert = advanceAlert;
	}

	/**
	 * @return the alertType
	 */
	public String getAlertType() {
		return alertType;
	}

	/**
	 * @param alertType the alertType to set
	 */
	public void setAlertType(final String alertType) {
		this.alertType = alertType;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(final String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(final String contactName) {
		this.contactName = contactName;
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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the domain
	 */
	public Domain getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(final Domain domain) {
		this.domain = domain;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final Status status) {
		this.status = status;
	}
}
