package com.fpl.persistence.policy;

import java.util.Collection;

import com.fpl.persistence.support.IHibernateDAOSupport;
import com.fpl.policy.Policy;
import com.fpl.policy.Policy2;
import com.fpl.policy.PolicySearchParam;

public interface IPolicyDAO2 extends IHibernateDAOSupport<Policy2> {

	Collection<Policy2> getPolicyList(PolicySearchParam searchParam);
}
