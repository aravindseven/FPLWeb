package com.fpl.request;

import java.util.Date;
import java.util.Set;

import com.fpl.domain.Domain;
import com.fpl.status.Status;

public class Request {

    private Long id;
    private Long domainId;
    private String location;
    private String description;
    private String keyword;
    private String postalCode;
    private Long customerId;
    private Long finPlannerId;
    private Date createdDate;
    private Date updatedDate;
    private Long currentStatusId;
    private Long notification;
    private Status status;
    private Domain domain;
    private Set<RequestStatusReference> requestReferenceList;
    private Set<RequestFplConfig> requestFplConfigList;
    
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
     * @return the location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * @param location the location to set
     */
    public void setLocation(final String location) {
        this.location = location;
    }
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
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
     * @return the finPlannerId
     */
    public Long getFinPlannerId() {
        return finPlannerId;
    }
    
    /**
     * @param finPlannerId the finPlannerId to set
     */
    public void setFinPlannerId(final Long finPlannerId) {
        this.finPlannerId = finPlannerId;
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
     * @return the updatedDate
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }
    
    /**
     * @param updatedDate the updatedDate to set
     */
    public void setUpdatedDate(final Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    /**
     * @return the notification
     */
    public Long getNotification() {
        return notification;
    }
    
    /**
     * @param notification the notification to set
     */
    public void setNotification(final Long notification) {
        this.notification = notification;
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
    
    /**
     * @return the currentStatusId
     */
    public Long getCurrentStatusId() {
        return currentStatusId;
    }

    /**
     * @param currentStatusId the currentStatusId to set
     */
    public void setCurrentStatusId(final Long currentStatusId) {
        this.currentStatusId = currentStatusId;
    }
    
    /**
     * @return the requestReferenceList
     */
    public Set<RequestStatusReference> getRequestReferenceList() {
        return requestReferenceList;
    }
    
    /**
     * @param requestReferenceList the requestReferenceList to set
     */
    public void setRequestReferenceList(final Set<RequestStatusReference> requestReferenceList) {
        this.requestReferenceList = requestReferenceList;
    }
    
	/**
	 * @return the requestFplConfigList
	 */
	public Set<RequestFplConfig> getRequestFplConfigList() {
		return requestFplConfigList;
	}

	/**
	 * @param requestFplConfigList the requestFplConfigList to set
	 */
	public void setRequestFplConfigList(final Set<RequestFplConfig> requestFplConfigList) {
		this.requestFplConfigList = requestFplConfigList;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
}


