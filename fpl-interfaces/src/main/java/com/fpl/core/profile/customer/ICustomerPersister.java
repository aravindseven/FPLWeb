package com.fpl.core.profile.customer;

import com.fpl.core.profile.IProfileModifier;
import com.fpl.profile.customer.CustomerPV;

public interface ICustomerPersister extends IProfileModifier {
 
    void persistCustomer(CustomerPV customerPV);
}


