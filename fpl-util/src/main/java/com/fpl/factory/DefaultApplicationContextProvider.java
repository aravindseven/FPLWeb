package com.fpl.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class DefaultApplicationContextProvider implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	private static final DefaultApplicationContextProvider CONTEXT_PROVIDER = new DefaultApplicationContextProvider();
	
	private DefaultApplicationContextProvider() {}
	
	public static DefaultApplicationContextProvider getInstance() {
		return CONTEXT_PROVIDER;
	}
	
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
