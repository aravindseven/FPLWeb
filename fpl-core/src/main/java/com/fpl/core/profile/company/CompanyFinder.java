package com.fpl.core.profile.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fpl.company.Company;
import com.fpl.persistence.company.ICompanyDAO;
import com.fpl.profile.address.Address;
import com.fpl.profile.company.CompanyPV;
import com.fpl.util.DateUtil;

@Component
public class CompanyFinder implements ICompanyFinder {
	
	@Autowired
	@Qualifier("fpl.profile.companyDAO")
	private ICompanyDAO companyDAO;
	
	@Override
	public CompanyPV getCompany(final String location) {
		final Company company = companyDAO.getCompanyByLocation(location);
		final CompanyPV companyPV = new CompanyPV();
		companyPV.setId(company.getId()+"");
		companyPV.setName(company.getName());
		companyPV.setRegistration(company.getRegistration());
		companyPV.setIncLocation(company.getIncLocation());
		final Address address = company.getAddress();
		companyPV.setBlock(address.getBlock());
		companyPV.setBuildingName(address.getBuildingName());
		companyPV.setStreet(address.getStreet());
		companyPV.setPostalCode(address.getPostalCode());
		companyPV.setCity(address.getCity());
		companyPV.setState(address.getState());
		companyPV.setCountry(address.getCountry());
		companyPV.setFax(company.getFax());
		companyPV.setWebsite(company.getWebsite());
		companyPV.setEmail(company.getEmail());
		companyPV.setContactFirstName1(company.getContactPerson1().split("^")[0]);
		companyPV.setContactLastName1(company.getContactPerson1().split("^")[1]);
		companyPV.setContactFirstName2(company.getContactPerson2().split("^")[0]);
		companyPV.setContactLastName2(company.getContactPerson2().split("^")[1]);
		companyPV.setPersoanNumber1(company.getPersoanNumber1()+"");
		companyPV.setPersoanNumber2(company.getPersoanNumber2()+"");
		companyPV.setPersonId1(company.getPersonId1());
		companyPV.setPersonId2(company.getPersonId2());
		companyPV.setRegistrationDate(DateUtil.getFormattedDate(company.getRegistrationDate()));
		companyPV.setUpdationDate(DateUtil.getFormattedDate(company.getUpdationDate()));
		companyPV.setStatus(company.getStatus());
		return companyPV;
	}
}
