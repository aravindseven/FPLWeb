package com.fpl.persistence.address;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.profile.address.Address;

public class AddressDAO extends HibernateDAOSupport<Address> implements IAddressDAO {

    private static final String ENTITY_CLASS_PATH = "com.fpl.profile.address.Address";
    
    @Override
    protected Class<Address> getEntityClass() {
        return (Class<Address>) ClassInstantiator.loadClass(ENTITY_CLASS_PATH);
    }
}
