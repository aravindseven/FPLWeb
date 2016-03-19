package com.fpl.profile.personal;

import java.util.Date;

import com.fpl.profile.address.Address;

public class PersonalData {

    private Long id;
    private String name;
    private String lastNameOrRNumber;
    private String genderOrLoc;
    private Date dateOfBirth;
    private String proofDesc;
    private String proofNum;
    private String mobileNumber;
    private String landlineNum;
    private String email;
    private Address address; 
    private Address permanentAddress; 
    
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }
    
    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    /**
     * @return the proofDesc
     */
    public String getProofDesc() {
        return proofDesc;
    }
    
    /**
     * @param proofDesc the proofDesc to set
     */
    public void setProofDesc(final String proofDesc) {
        this.proofDesc = proofDesc;
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
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }
    
    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    /**
     * @return the landlineNum
     */
    public String getLandlineNum() {
        return landlineNum;
    }
    
    /**
     * @param landlineNum the landlineNum to set
     */
    public void setLandlineNum(final String landlineNum) {
        this.landlineNum = landlineNum;
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
     * @return the address
     */
    public Address getAddress() {
        return address;
    }
    
    /**
     * @param address the address to set
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

	/**
	 * @return the lastNameOrRNumber
	 */
	public String getLastNameOrRNumber() {
		return lastNameOrRNumber;
	}

	/**
	 * @param lastNameOrRNumber the lastNameOrRNumber to set
	 */
	public void setLastNameOrRNumber(final String lastNameOrRNumber) {
		this.lastNameOrRNumber = lastNameOrRNumber;
	}

	/**
	 * @return the genderOrLoc
	 */
	public String getGenderOrLoc() {
		return genderOrLoc;
	}

	/**
	 * @param genderOrLoc the genderOrLoc to set
	 */
	public void setGenderOrLoc(final String genderOrLoc) {
		this.genderOrLoc = genderOrLoc;
	}

	/**
	 * @return the permanentAddress
	 */
	public Address getPermanentAddress() {
		return permanentAddress;
	}

	/**
	 * @param permanentAddress the permanentAddress to set
	 */
	public void setPermanentAddress(final Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
}


