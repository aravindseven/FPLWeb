package com.fpl.persistence.login;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.login.entity.LoginRecovery;
import com.fpl.persistence.support.HibernateDAOSupport;

public class LoginRecoveryDAO extends HibernateDAOSupport<LoginRecovery> implements  ILoginRecoveryDAO {

	private static final String ENTITY_CLASS_NAME = "com.fpl.login.entity.LoginRecovery";
	
	@Override
	protected Class<LoginRecovery> getEntityClass() {
		return (Class<LoginRecovery>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}

	@Override
	public LoginRecovery getLoginRecovery(final Integer otp) {
		return getHibernateTemplate().execute(new HibernateCallback<LoginRecovery, LoginRecovery>("getLoginRecovery") {
			@Override
			public LoginRecovery doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.eq("otp",otp));
				return (LoginRecovery) criteria.uniqueResult();
			}
		});
	}
	
	@Override
	public LoginRecovery getLoginRecovery(final Long loginCredentialId) {
		return getHibernateTemplate().execute(new HibernateCallback<LoginRecovery, LoginRecovery>("getLoginRecovery") {
			@Override
			public LoginRecovery doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.eq("loginCredentialId",loginCredentialId));
				return (LoginRecovery) criteria.uniqueResult();
			}
		});
	}
}
