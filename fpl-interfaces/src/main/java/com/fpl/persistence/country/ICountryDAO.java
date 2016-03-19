package com.fpl.persistence.country;

import java.util.Collection;

import com.fpl.country.Country;
import com.fpl.persistence.support.IHibernateDAOSupport;

public interface ICountryDAO extends IHibernateDAOSupport<Country> {
	
	Collection<Country> getAllEntities();
	
	Country getCountryByName(String name);
}


