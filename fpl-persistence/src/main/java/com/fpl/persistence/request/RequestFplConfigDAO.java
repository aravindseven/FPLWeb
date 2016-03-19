package com.fpl.persistence.request;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.request.RequestFplConfig;

public class RequestFplConfigDAO extends HibernateDAOSupport<RequestFplConfig> implements IRequestFplConfigDAO {

	private static final String ENTITY_CLASS_NAME = "com.fpl.request.RequestFplConfig";
	
	@Override
	protected Class<RequestFplConfig> getEntityClass() {
		return (Class<RequestFplConfig>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}

	@Override
	public Collection<RequestFplConfig> getRequestFplConfigList(final Long fplId) {
		return getHibernateTemplate().execute(new HibernateCallback<RequestFplConfig, Collection<RequestFplConfig>>("getRequestFplConfigList") {
			@Override
			public Collection<RequestFplConfig> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.add(Restrictions.eq("finPlannerId", fplId));
				criteria.createAlias("status", "st");
				criteria.add(Restrictions.ne("st.disDescription", "ST_04"));
				criteria.addOrder(Order.asc("date"));
				return criteria.list();
			}
		});
	}

	@Override
	public RequestFplConfig getByRequestFPLId(final Long requestId, final Long fplId) {
		return getHibernateTemplate().execute(new HibernateCallback<RequestFplConfig, RequestFplConfig>("getByRequestId") {
			@Override
			public RequestFplConfig doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.add(Restrictions.eq("requestId", requestId));
				criteria.add(Restrictions.eq("finPlannerId", fplId));
				return (RequestFplConfig) criteria.uniqueResult();
			}
		});
	}
}
