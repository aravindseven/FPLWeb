package com.fpl.persistence.policy;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.policy.Policy;
import com.fpl.policy.PolicySearchParam;

public class PolicyDAO extends HibernateDAOSupport<Policy> implements IPolicyDAO {

	@Override
	protected Class<Policy> getEntityClass() {
		return Policy.class;
	}

	@Override
	public Collection<Policy> getPolicyList(final PolicySearchParam searchParam) {
		return getHibernateTemplate().execute(new HibernateCallback<Policy, Collection<Policy>>("getPolicyList: "+searchParam) {
			@Override
			public Collection<Policy> doInHibernate(final Session session) {
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
