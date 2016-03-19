package com.fpl.persistence.login;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.login.entity.LoginSupport;
import com.fpl.persistence.support.HibernateDAOSupport;

public class LoginSupportDAO extends HibernateDAOSupport<LoginSupport> implements ILoginSupportDAO {
	
	private static final String ENTITY_CLASS_NAME = "com.fpl.login.entity.LoginSupport";
	
	@Override
	protected Class<LoginSupport> getEntityClass() {
		return (Class<LoginSupport>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}
	
	@Override
	public Serializable save(final LoginSupport entity, final boolean flushMode) {
		return super.save(entity, flushMode);
	}

	@Override
	public void update(final LoginSupport entity, final boolean flushMode) {
		super.update(entity, flushMode);
	}

	@Override
	public void delete(final LoginSupport entity, final boolean flushMode) {
		super.delete(entity, flushMode);
	}

	@Override
	public LoginSupport getByLoginCredentialId(final Long loginCredentialid) {
		return getHibernateTemplate().execute(new HibernateCallback<LoginSupport, LoginSupport>("getByLoginCredentialId") {
			@Override
			public LoginSupport doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.eq("loginCredentialId",loginCredentialid));
				return (LoginSupport) criteria.uniqueResult();
			}
		});
	}
}
