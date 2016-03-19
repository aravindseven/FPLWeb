package com.fpl.persistence.company;

import java.util.Collection;

import com.fpl.company.Company;
import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.profile.admin.SearchProfilePV;

public interface ICompanyDAO extends IHibernateDAOSupport<Company> {
	
	Company getCompanyByName(String name);
	
	Collection<Company> getCompanyList(SearchProfilePV searchProfile);
	
	Company getCompanyByLocation(String location);
	
	Company getCompanyByNameLocation(String name, String location);
	
	Collection<String> getCompanyList();
}


