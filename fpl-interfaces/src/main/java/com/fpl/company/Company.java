package com.fpl.company;

import java.util.Date;

import com.fpl.profile.address.Address;

public class Company {

	private Long id;
	private String name;           
	private String registration;
	private String incLocation;
	private String fax;
	private String notes;	
	private String website;           
	private String email;              
	private String contactPerson1;
	private String contactPerson2; 
	private Long persoanNumber1; 
	private Long persoanNumber2; 
	private Long   phonenumber1; 
	private Long   phonenumber2;  
	private String personId1; 
	private String personId2;
	private Date registrationDate;
	private Date updationDate;
	private String status;
	private Address address;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getIncLocation() {
		return incLocation;
	}
	public void setIncLocation(String incLocation) {
		this.incLocation = incLocation;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactPerson1() {
		return contactPerson1;
	}
	public void setContactPerson1(String contactPerson1) {
		this.contactPerson1 = contactPerson1;
	}
	public String getContactPerson2() {
		return contactPerson2;
	}
	public void setContactPerson2(String contactPerson2) {
		this.contactPerson2 = contactPerson2;
	}
	public Long getPersoanNumber1() {
		return persoanNumber1;
	}
	public void setPersoanNumber1(Long persoanNumber1) {
		this.persoanNumber1 = persoanNumber1;
	}
	public Long getPersoanNumber2() {
		return persoanNumber2;
	}
	public void setPersoanNumber2(Long persoanNumber2) {
		this.persoanNumber2 = persoanNumber2;
	}
	public Long getPhonenumber1() {
		return phonenumber1;
	}
	public void setPhonenumber1(Long phoneNumber1) {
		this.phonenumber1 = phoneNumber1;
	}
	public Long getPhonenumber2() {
		return phonenumber2;
	}
	public void setPhonenumber2(Long phoneNumber2) {
		this.phonenumber2 = phoneNumber2;
	}
	public String getPersonId1() {
		return personId1;
	}
	public void setPersonId1(String personId1) {
		this.personId1 = personId1;
	}
	public String getPersonId2() {
		return personId2;
	}
	public void setPersonId2(String personId2) {
		this.personId2 = personId2;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Date getUpdationDate() {
		return updationDate;
	}
	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}         
	

}
