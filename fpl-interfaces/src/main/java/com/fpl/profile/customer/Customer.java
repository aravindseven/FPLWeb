package com.fpl.profile.customer;

import java.util.Date;

import com.fpl.profile.personal.PersonalData;

public class Customer {

    private Long id;
    private String relationship;
    private String service;
    private Date startDate;
    private Date updationDate;
    private String status;
    private String state;
    private String confPreference;
    private Long userTypeId;
    private Long loginCredentialId;
    private PersonalData personalData;
    private PersonalData secondaryPersonData;
    
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
     * @return the relationship
     */
    public String getRelationship() {
        return relationship;
    }
    
    /**
     * @param relationship the relationship to set
     */
    public void setRelationship(final String relationship) {
        this.relationship = relationship;
    }
    
    /**
     * @return the service
     */
    public String getService() {
        return service;
    }
    
    /**
     * @param service the service to set
     */
    public void setService(final String service) {
        this.service = service;
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
     * @return the updationDate
     */
    public Date getUpdationDate() {
        return updationDate;
    }
    
    /**
     * @param updationDate the updationDate to set
     */
    public void setUpdationDate(final Date updationDate) {
        this.updationDate = updationDate;
    }
    
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }
    
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }
    
    /**
     * @param state the state to set
     */
    public void setState(final String state) {
        this.state = state;
    }
    
    /**
     * @return the confPreference
     */
    public String getConfPreference() {
        return confPreference;
    }
    
    /**
     * @param confPreference the confPreference to set
     */
    public void setConfPreference(final String confPreference) {
        this.confPreference = confPreference;
    }
    
    /**
     * @return the userTypeId
     */
    public Long getUserTypeId() {
        return userTypeId;
    }
    
    /**
     * @param userTypeId the userTypeId to set
     */
    public void setUserTypeId(final Long userTypeId) {
        this.userTypeId = userTypeId;
    }
    
    /**
     * @return the loginCredentialId
     */
    public Long getLoginCredentialId() {
        return loginCredentialId;
    }
    
    /**
     * @param loginCredentialId the loginCredentialId to set
     */
    public void setLoginCredentialId(final Long loginCredentialId) {
        this.loginCredentialId = loginCredentialId;
    }
    
    /**
     * @return the personalData
     */
    public PersonalData getPersonalData() {
        return personalData;
    }
    
    /**
     * @param personalData the personalData to set
     */
    public void setPersonalData(final PersonalData personalData) {
        this.personalData = personalData;
    }

    /**
     * @return the secondaryPersonData
     */
    public PersonalData getSecondaryPersonData() {
        return secondaryPersonData;
    }

    /**
     * @param secondaryPersonData the secondaryPersonData to set
     */
    public void setSecondaryPersonData(final PersonalData secondaryPersonData) {
        this.secondaryPersonData = secondaryPersonData;
    }
    
}


