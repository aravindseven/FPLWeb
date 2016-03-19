package com.fpl.status;

public class Status {

    private Long id;
    private String description;
    private String disDescription;
    private Long statusMasterId;
    private StatusMaster statusMaster;
    
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
     * @return the disDescription
     */
    public String getDisDescription() {
        return disDescription;
    }
    
    /**
     * @param disDescription the disDescription to set
     */
    public void setDisDescription(final String disDescription) {
        this.disDescription = disDescription;
    }
    
    /**
     * @return the statusMasterId
     */
    public Long getStatusMasterId() {
        return statusMasterId;
    }
    
    /**
     * @param statusMasterId the statusMasterId to set
     */
    public void setStatusMasterId(final Long statusMasterId) {
        this.statusMasterId = statusMasterId;
    }
    
    /**
     * @return the statusMaster
     */
    public StatusMaster getStatusMaster() {
        return statusMaster;
    }
    
    /**
     * @param statusMaster the statusMaster to set
     */
    public void setStatusMaster(final StatusMaster statusMaster) {
        this.statusMaster = statusMaster;
    }
    
}


