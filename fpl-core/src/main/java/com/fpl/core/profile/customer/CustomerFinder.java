package com.fpl.core.profile.customer;

import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.profile.customer.Customer;

public class CustomerFinder implements ICustomerFinder {
	
	private ICustomerDAO customerDAO;
	
	@Override
	public Customer getCustomerLoginId(final Long loginId) {
		return customerDAO.getCustomerLoginId(loginId);
	}
	
	
	
	@Override
	public Customer getCustomerId(Long id) {
		return customerDAO.get(id);
	}



	public Long getMaxId()
	{
		return customerDAO.getMaxId();
	}

	/**
	 * @param customerDAO the customerDAO to set
	 */
	public void setCustomerDAO(final ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
}
