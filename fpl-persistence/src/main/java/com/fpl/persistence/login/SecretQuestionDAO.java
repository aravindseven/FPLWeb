package com.fpl.persistence.login;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fpl.factory.factory.ClassInstantiator;
import com.fpl.hibernate.HibernateCallback;
import com.fpl.login.entity.SecretQuestion;
import com.fpl.persistence.support.HibernateDAOSupport;

public class SecretQuestionDAO extends HibernateDAOSupport<SecretQuestion> implements ISecretQuestionDAO {

	private static final String ENTITY_CLASS_NAME = "com.fpl.login.entity.SecretQuestion";
	
	@Override
	protected Class<SecretQuestion> getEntityClass() {
		return (Class<SecretQuestion>) ClassInstantiator.loadClass(ENTITY_CLASS_NAME);
	}

	@Override
	public SecretQuestion getSecretQuestion(final String question) {
		return getHibernateTemplate().execute(new HibernateCallback<SecretQuestion, SecretQuestion>("getSecretQuestion") {
			@Override
			public SecretQuestion doInHibernate(final Session session) {
				final Criteria criteria = session.createCriteria(getEntityClass());
                criteria.add(Restrictions.eq("descrption",question));
				return (SecretQuestion) criteria.uniqueResult();
			}
		});
	}
}
