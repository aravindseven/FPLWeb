package com.fpl.hibernate;

import com.fpl.factory.DefaultApplicationContextProvider;

public class HibernateSessionFactoryProvider {

	private static HibernateSessionFactoryProvider instance;
	private DefaultApplicationContextProvider contextProvider;
	
	public static HibernateSessionFactoryProvider getInstance() {
		if (instance == null) {
			synchronized (HibernateSessionFactoryProvider.class) {
				if (instance == null) {
					instance = new HibernateSessionFactoryProvider();
				}
			}
		}
		return instance;
	}

	public HibernateSessionFactory getHibernateSessionFactory() {
		/*return BeanUtil.getInstance(new IBeanInfo() {
			@Override
			public String getBeanId() {
				return "hibernate.sessionFactory";
			}
			@Override
			public BeanConfigLocationType getBeanConfig() {
				//return BeanConfigLocationType.SPRING_HIBERNATE;
				return BeanConfigLocationType.DEFAULT_CONTEXT;
			}
		}).getBean(HibernateSessionFactory.class);*/
		return contextProvider.getApplicationContext().getBean("hibernate.sessionFactory", HibernateSessionFactory.class);
	}

	/**
	 * @param contextProvider the contextProvider to set
	 */
	public void setContextProvider(final DefaultApplicationContextProvider contextProvider) {
		this.contextProvider = contextProvider;
	}
}