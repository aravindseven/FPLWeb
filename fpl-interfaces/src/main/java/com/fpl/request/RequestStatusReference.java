package com.fpl.request;

import java.util.Date;

import com.fpl.status.Status;

public class RequestStatusReference {

    private Long id;
    private Long requestId;
    private Long statusId;
    private Date date;
    private Long finPlannerId;
    private String note;
    private Status status;
    private boolean sendToFP;
    
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
    public Date getDate() {
        return date;
    }
    
    /**
     * @param date the date to set
     */
    public void setDate(final Date date) {
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

	public boolean isSendToFP() {
		return sendToFP;
	}

	public void setSendToFP(boolean sendToFP) {
		this.sendToFP = sendToFP;
	}
    
    
    
}


