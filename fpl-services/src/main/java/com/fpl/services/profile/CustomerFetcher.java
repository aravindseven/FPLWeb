package com.fpl.services.profile;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.profile.customer.ICustomerFinder;
import com.fpl.profile.customer.Customer;
import com.fpl.profile.customer.CustomerPV;

public class CustomerFetcher implements IProfileFetcher {

	private ICustomerFinder customerFinder;
	private AbstractTransformer<Customer, CustomerPV> transformer;

	@SuppressWarnings("unchecked")
	@Override
	public CustomerPV getProfilePageView(final Long loginId) {
		CustomerPV customerPV = null;
		final Customer customer = customerFinder.getCustomerLoginId(loginId);
		if(customer != null) {
			customerPV  = transformer.transform(customer);
		}
		return customerPV;
	}
	
	@Override
	public Long getMaxId() {
		
		return customerFinder.getMaxId();
	}
	
	/**
	 * @param customerFinder the customerFinder to set
	 */
	public void setCustomerFinder(final ICustomerFinder customerFinder) {
		this.customerFinder = customerFinder;
	}

	/**
	 * @param transformer the transformer to set
	 */
	public void setTransformer(final AbstractTransformer<Customer, CustomerPV> transformer) {
		this.transformer = transformer;
	}
}
