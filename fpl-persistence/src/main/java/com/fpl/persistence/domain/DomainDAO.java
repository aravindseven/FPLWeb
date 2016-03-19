package com.fpl.persistence.domain;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.fpl.domain.Domain;
import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.persistence.support.HibernateDAOSupport;

public class DomainDAO extends HibernateDAOSupport<Domain> implements IDomainDAO {

    private static final String ENTITY_CLASS_PATH = "com.fpl.domain.Domain";
    
    @Override
    protected Class<Domain> getEntityClass() {
        return (Class<Domain>) ClassInstantiator.loadClass(ENTITY_CLASS_PATH);
    }

	@Override
	public List<Domain> getDomainByName(final Collection<String> names) {
		return getHibernateTemplate().execute(new HibernateCallback<Domain, List<Domain>>("getDomainByName") {
			@Override
			public List<Domain> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.in("name", names));
				return criteria.list();
			}
		});
	}

	@Override
	public List<Domain> getAllEntities() {
		return getHibernateTemplate().execute(new HibernateCallback<Domain, List<Domain>>("getAllEntities") {
			@Override
			public List<Domain> doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
				criteria.addOrder(Order.asc("type"));
                criteria.addOrder(Order.asc("name"));
				return criteria.list();
			}
		});
	}
}
