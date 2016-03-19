package com.fpl.profile.address;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Address {

    private Long id;
    private String block;
    private String buildingName;
    private String street;
    private String city;
    private String postalCode;
    private String state;
    private String country;
    private Float longitude;
    private Float latitude;
    
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
     * @return the block
     */
    public String getBlock() {
        return block;
    }
    
    /**
     * @param block the block to set
     */
    public void setBlock(final String block) {
        this.block = block;
    }
    
    /**
     * @return the buildingName
     */
    public String getBuildingName() {
        return buildingName;
    }
    
    /**
     * @param buildingName the buildingName to set
     */
    public void setBuildingName(final String buildingName) {
        this.buildingName = buildingName;
    }
    
    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }
    
    /**
     * @param street the street to set
     */
    public void setStreet(final String street) {
        this.street = street;
    }
    
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * @param city the city to set
     */
    public void setCity(final String city) {
        this.city = city;
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
     * @return the country
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * @param country the country to set
     */
    public void setCountry(final String country) {
        this.country = country;
    }

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	
	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}


