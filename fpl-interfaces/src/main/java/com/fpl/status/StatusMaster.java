package com.fpl.status;

public class StatusMaster {

    private Long id;
    private String type;
    private String description;
    
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
     * @return the type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(final String type) {
        this.type = type;
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
}


