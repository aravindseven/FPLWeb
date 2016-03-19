package com.fpl.persistence.policy;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.policy.Policy;
import com.fpl.policy.Policy2;
import com.fpl.policy.PolicySearchParam;

public class PolicyDAO2 extends HibernateDAOSupport<Policy2> implements IPolicyDAO2 {

	@Override
	protected Class<Policy2> getEntityClass() {
		return Policy2.class;
	}

	@Override
	public Collection<Policy2> getPolicyList(final PolicySearchParam searchParam) {
		return getHibernateTemplate().execute(new HibernateCallback<Policy2, Collection<Policy2>>("getPolicyList: "+searchParam) {
			@Override
			public Collection<Policy2> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				if(searchParam.getCustomerId() != null) {
					criteria.add(Restrictions.eq("customerId", searchParam.getCustomerId()));
				}
				if(searchParam.getFplId() != null) {
					criteria.add(Restrictions.eq("fplId", searchParam.getFplId()));
				}
				if(searchParam.getPolicyId() != null) {
					criteria.add(Restrictions.eq("id", searchParam.getPolicyId()));
				}
				return criteria.list();
			}
		});
	}
	
}

