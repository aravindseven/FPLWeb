package com.fpl.core.profile.customer;

import java.util.Date;

import com.fpl.common.AbstractTransformer;
import com.fpl.common.IValidator;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.persistence.address.IAddressDAO;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.customer.Customer;
import com.fpl.profile.customer.CustomerPV;

public class CustomerPersisterImpl implements ICustomerPersister {
    
    private ICustomerDAO customerDAO;
    private IAddressDAO addressDAO;
	private IValidator<CustomerPV> customerValidator;
	private AbstractTransformer<CustomerPV, Customer> customerTransformer;
	
    @Override
    public void persistCustomer(final CustomerPV customerPV) {
    	//customerValidator.validate(customerPV);
    	final Customer customer = customerTransformer.transform(customerPV);
    	final Customer oldCustomer = customerDAO.getCustomerLoginId(customer.getLoginCredentialId());
    	final String email = Encryption.encrypt(customer.getPersonalData().getEmail(), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
    	customer.getPersonalData().setEmail(email);
    	if(oldCustomer != null) {
    		customer.setId(oldCustomer.getId());
    		
    		customer.getPersonalData().setId(oldCustomer.getPersonalData().getId());
    		if(oldCustomer.getSecondaryPersonData() != null) {
    			customer.getSecondaryPersonData().setId(oldCustomer.getSecondaryPersonData().getId());
    			customer.getSecondaryPersonData().setAddress(null);
    		}
    		if(oldCustomer.getPersonalData().getAddress() != null && customer.getPersonalData().getAddress() != null) {
    			customer.getPersonalData().getAddress().setId(oldCustomer.getPersonalData().getAddress().getId());
    		}
    		if(oldCustomer.getPersonalData().getPermanentAddress() != null && customer.getPersonalData().getPermanentAddress() != null) {
    			customer.getPersonalData().getPermanentAddress().setId(oldCustomer.getPersonalData().getPermanentAddress().getId());
    		}
    		customer.setUpdationDate(new Date());
    	}
    	
    	System.out.println(customer.getPersonalData().getAddress());
    	customerDAO.saveOrUpdate(customer, true);
    	if(oldCustomer != null) {
        	if(oldCustomer.getPersonalData().getAddress() != null && customer.getPersonalData().getAddress() == null) {
        		addressDAO.delete(oldCustomer.getPersonalData().getAddress(), true);
        	}
        	if(oldCustomer.getPersonalData().getPermanentAddress() != null && customer.getPersonalData().getPermanentAddress() == null) {
        		addressDAO.delete(oldCustomer.getPersonalData().getPermanentAddress(), true);
        	}
    	}
    }
    
	@Override
	public void activateProfile(final Long loginId) {
    	final Customer customer = customerDAO.getCustomerLoginId(loginId);
    	customer.setStatus(ProfileStatus.VERIFIED.getStatus());
    	customer.setState(ProfileState.ACTIVE.getValue());
    	customerDAO.update(customer, true);
	}
	
	@Override
	public void deActivateProfile(final Long loginId) {
    	final Customer customer = customerDAO.getCustomerLoginId(loginId);
    	customer.setState(ProfileState.DEACTIVE.getValue());
    	customerDAO.update(customer, true);
	}
	
    /**
     * @param customerDAO the customerDAO to set
     */
    public void setCustomerDAO(final ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

	/**
	 * @param customerValidator the customerValidator to set
	 */
	public void setCustomerValidator(final IValidator<CustomerPV> customerValidator) {
		this.customerValidator = customerValidator;
	}

	/**
	 * @param customerTransformer the customerTransformer to set
	 */
	public void setCustomerTransformer(final AbstractTransformer<CustomerPV, Customer> customerTransformer) {
		this.customerTransformer = customerTransformer;
	}

	/**
	 * @param addressDAO the addressDAO to set
	 */
	public void setAddressDAO(final IAddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}
}


