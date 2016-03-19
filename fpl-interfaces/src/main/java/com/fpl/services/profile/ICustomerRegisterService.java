package com.fpl.services.profile;

import com.fpl.common.FPLServiceResponse;
import com.fpl.profile.customer.CustomerPV;

public interface ICustomerRegisterService {

	FPLServiceResponse insertCustomer(CustomerPV customerPV);
}
