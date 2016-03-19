package com.fpl.services.profile;

import com.fpl.common.FPLServiceResponse;
import com.fpl.core.profile.customer.ICustomerPersister;
import com.fpl.profile.customer.CustomerPV;

public class CustomerRegisterService implements ICustomerRegisterService {
	
	private ICustomerPersister persister;
	
	@Override
	public FPLServiceResponse insertCustomer(final CustomerPV customerPV) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			persister.persistCustomer(customerPV);
			response.setReason("Profile information updated !!");
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}

	public void setPersister(final ICustomerPersister persister) {
		this.persister = persister;
	}
}
