package com.fpl.persistence.status;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;
import com.fpl.status.Status;

public class StatusDAO extends HibernateDAOSupport<Status> implements IStatusDAO {

	private static final String ENTITY_CLASS_NAME = "com.fpl.status.Status";
	
	@Override
	protected Class<Status> getEntityClass() {
		return (Class<Status>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}
	
	@Override
	public Status getStatusByDisc(final String disDescription) {
		return getHibernateTemplate().execute(new HibernateCallback<Status, Status>("getStatusByDisc") {
			@Override
			public Status doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.add(Restrictions.eq("disDescription", disDescription));
				return (Status) criteria.uniqueResult();
			}
		});
	}

	@Override
	public Collection<Status> getStatusList(final Long statusMasterId) {
		return getHibernateTemplate().execute(new HibernateCallback<Status, Collection<Status>>("getStatusList") {
			@Override
			public Collection<Status> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.add(Restrictions.eq("statusMasterId", statusMasterId));
				return criteria.list();
			}
		});
	}
}
