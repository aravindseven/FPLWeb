package com.fpl.services.profile;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.profile.customer.ICustomerFinder;
import com.fpl.profile.admin.AdminProfilePV;
import com.fpl.profile.customer.Customer;
import com.fpl.profile.customer.CustomerPV;

public class AdminFetcher implements IProfileFetcher {

	private ICustomerFinder customerFinder;
	private AbstractTransformer<Customer, CustomerPV> transformer;

	@SuppressWarnings("unchecked")
	@Override
	public AdminProfilePV getProfilePageView(final Long loginId) {
		AdminProfilePV adminProfilePV = null;
		CustomerPV customerPV = null;
		final Customer customer = customerFinder.getCustomerLoginId(loginId);
		if(customer != null) {
			customerPV  = transformer.transform(customer);
			adminProfilePV = new AdminProfilePV();
			adminProfilePV.setAddressPV(customerPV.getAddressPV());
			adminProfilePV.setPersonalDataPV(customerPV.getPrimaryPersonalData());
			adminProfilePV.setState(customerPV.getState().getValue());
			adminProfilePV.setStatus(customerPV.getStatus().getStatus());
			adminProfilePV.setValidationDate(customerPV.getValidationDate());
		}
		return adminProfilePV;
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
