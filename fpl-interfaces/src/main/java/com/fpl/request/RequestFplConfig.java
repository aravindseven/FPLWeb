package com.fpl.request;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.status.Status;

public class RequestFplConfig {

    private Long id;
    private Long requestId;
    private Long statusId;
    private Timestamp date;
    private Long finPlannerId;
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
     * @return the date
     */
    public Timestamp getDate() {
        return date;
    }
    
    /**
     * @param date the date to set
     */
    public void setDate(final Timestamp date) {
        this.date = date;
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
    
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}


