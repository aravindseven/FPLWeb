package com.fpl.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateTemplateFactory {

	private static final HibernateTemplateFactory instance = new HibernateTemplateFactory();
	private HibernateSessionFactoryProvider sessionFactoryProvider;
	
	protected HibernateTemplateFactory() {}

	public static HibernateTemplateFactory getInstance() {
		return instance;
	}
	
	protected HibernateSessionFactory getHibernateSessionFactory() {
		/*return HibernateSessionFactoryProvider.getInstance().getHibernateSessionFactory();*/
		return sessionFactoryProvider.getHibernateSessionFactory();
	}

	public HibernateTemplate getHibernateTemplate() {
		return new HibernateTemplate() {
			@Override
			public <T,R> R execute(final HibernateCallback<T,R> hibernateCallback) {
				Transaction transaction = null;
				try {
					final HibernateSessionFactory hibernateSessionFactory = getHibernateSessionFactory();
					final Session session = hibernateSessionFactory.getSessionFactory().getCurrentSession();
					transaction = session.beginTransaction();
					System.out.println("<execute> executing: "+hibernateCallback);
					final R result = hibernateCallback.doInHibernate(session);
					//System.out.println("<execute> going to commit execution of: "+hibernateCallback);
					transaction.commit();
					//System.out.println("<execute> committed execution of: "+hibernateCallback);
					return result;
				} catch (final Throwable e) {
					if ((transaction != null) && hibernateCallback.needRollback(e)) {
						//System.out.println("<execute> going to rollback execution of: "+hibernateCallback);
						transaction.rollback();
					}
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
	 * @param sessionFactoryProvider the sessionFactoryProvider to set
	 */
	public void setSessionFactoryProvider(
			final HibernateSessionFactoryProvider sessionFactoryProvider) {
		this.sessionFactoryProvider = sessionFactoryProvider;
	}
}

