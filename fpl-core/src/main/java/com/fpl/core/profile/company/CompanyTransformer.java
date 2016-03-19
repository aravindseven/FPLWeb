package com.fpl.core.profile.company;

import java.util.Date;

import com.fpl.common.AbstractTransformer;
import com.fpl.company.Company;
import com.fpl.profile.address.Address;
import com.fpl.profile.company.CompanyPV;
import com.fpl.util.NumberUtil;

public class CompanyTransformer extends AbstractTransformer<CompanyPV, Company> {

	@Override
	public Company transform(final CompanyPV companyPV) {
		final Company company = new Company();
		company.setName(companyPV.getName());
		company.setRegistration(companyPV.getRegistration());
		company.setIncLocation(companyPV.getIncLocation());
		
		company.setFax(companyPV.getFax());
		company.setWebsite(companyPV.getWebsite());
		/*if(NumberUtil.isValidNumber(companyPV.getLandLine())) {
			company.setLandLine(Long.valueOf(companyPV.getLandLine()));
		}*/
		company.setEmail(companyPV.getEmail());
		final String contactPerson1 = companyPV.getContactFirstName1() + "^" + companyPV.getContactLastName1();
		company.setContactPerson1(contactPerson1);
		final String contactPerson2 = companyPV.getContactFirstName2() + "^" + companyPV.getContactLastName2();
		company.setContactPerson2(contactPerson2);
		if(NumberUtil.isValidNumber(companyPV.getPersoanNumber1())) {
			company.setPersoanNumber1(Long.valueOf(companyPV.getPersoanNumber1()));
		}
		if(NumberUtil.isValidNumber(companyPV.getPersoanNumber2())) {
			company.setPersoanNumber2(Long.valueOf(companyPV.getPersoanNumber2()));
		}
		/*company.setDescription1(companyPV.getDescription1());
		company.setDescription2(companyPV.getDescription2());
		System.out.println("---------- companyPV.getPersonPosition1() ----------"+ companyPV.getPersonPosition1());
		company.setPersonPosition1(companyPV.getPersonPosition1());
		company.setPersonPosition2(companyPV.getPersonPosition2());*/
		company.setPersonId1(companyPV.getPersonId1());
		company.setPersonId2(companyPV.getPersonId2());
		company.setRegistrationDate(new Date());
		company.setUpdationDate(new Date());
		company.setStatus(companyPV.getStatus());
		final Address address = new Address();
		address.setBlock(companyPV.getBlock());
		address.setBuildingName(companyPV.getBuildingName());
		address.setCity(companyPV.getCity());
		address.setCountry(companyPV.getCountry());
		address.setPostalCode(companyPV.getPostalCode());
		address.setState(companyPV.getState());
		address.setStreet(companyPV.getStreet());
		company.setAddress(address);
		return company;
	}
}
