package com.fpl.core.profile.customer;

import com.fpl.profile.customer.Customer;

public interface ICustomerFinder {

	Customer getCustomerLoginId(Long loginId);
	
	Customer getCustomerId(Long id);
	
	Long getMaxId();
}
