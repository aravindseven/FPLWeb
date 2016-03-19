package com.fpl.persistence.request;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.request.RequestStatusReference;

public class RequestStatusRefDAO  extends HibernateDAOSupport<RequestStatusReference> implements IRequestStatusRefDAO {

	private static final String ENTITY_CLASS_NAME = "com.fpl.request.RequestStatusReference";
	
	@Override
	protected Class<RequestStatusReference> getEntityClass() {
		return (Class<RequestStatusReference>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}
}
