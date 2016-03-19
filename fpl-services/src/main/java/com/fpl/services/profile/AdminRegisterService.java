package com.fpl.services.profile;

import com.fpl.common.FPLServiceResponse;
import com.fpl.core.profile.customer.ICustomerPersister;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.admin.AdminProfilePV;
import com.fpl.profile.customer.CustomerPV;

public class AdminRegisterService implements IAdminRegisterService {
	
	private ICustomerPersister persister;
	
	@Override
	public FPLServiceResponse insertAdminProfile(final AdminProfilePV adminProfilePV) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			persister.persistCustomer(getCustomerPV(adminProfilePV));
			response.setReason("New custome created !");
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
	
	private CustomerPV getCustomerPV(final AdminProfilePV adminProfilePV) {
		final CustomerPV customerPV = new CustomerPV();
		customerPV.setAddressPV(adminProfilePV.getAddressPV());
		customerPV.setPrimaryPersonalData(adminProfilePV.getPersonalDataPV());
		customerPV.setState(ProfileState.ACTIVE);
		customerPV.setStatus(ProfileStatus.VERIFIED);
		return customerPV;
	}
}
