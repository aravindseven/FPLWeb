package com.fpl.hibernate;

import org.hibernate.SessionFactory;

public class HibernateSessionFactory {

	private SessionFactory sessionFactory;

	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}