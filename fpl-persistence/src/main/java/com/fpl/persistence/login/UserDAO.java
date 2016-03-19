package com.fpl.persistence.login;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.login.entity.User;
import com.fpl.persistence.support.HibernateDAOSupport;

public class UserDAO extends HibernateDAOSupport<User> implements IUserDAO {
	
	private static final String ENTITY_CLASS_NAME = "com.fpl.login.entity.User";
	
	@Override
	protected Class<User> getEntityClass() {
		return (Class<User>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}
	
	@Override
	public User getUser(final String userType) {
		return getHibernateTemplate().execute(new HibernateCallback<User, User>("getUser") {
			@Override
			public User doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.eq("userType",userType));
				return (User) criteria.uniqueResult();
			}
		});
	}
	
}
