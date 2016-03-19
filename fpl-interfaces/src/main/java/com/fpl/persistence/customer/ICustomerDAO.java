package com.fpl.persistence.customer;

import java.util.Collection;

import com.fpl.paging.support.IPagingTemplate;
import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.profile.admin.SearchProfilePV;
import com.fpl.profile.customer.Customer;

public interface ICustomerDAO extends IHibernateDAOSupport<Customer> {
    
	Customer getCustomerLoginId(final Long loginId);
	
	Collection<Customer> getCustomerList(SearchProfilePV searchProfile);
	Collection<Customer> getCustomerList();
	IPagingTemplate getPagingTemplate(SearchProfilePV searchProfile);
	
	Long getMaxId();
}


