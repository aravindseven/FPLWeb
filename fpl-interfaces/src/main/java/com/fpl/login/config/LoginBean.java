package com.fpl.login.config;

import com.fpl.factory.config.BeanConfigLocationType;
import com.fpl.factory.config.IBeanInfo;

public enum LoginBean implements IBeanInfo {

	/*LOGIN_PROCESSOR("fpl.login.loginProcessor",BeanConfigLocationType.LOGIN),
	LOGIN_PERSISTER("fpl.login.loginPersister",BeanConfigLocationType.LOGIN);*/
	LOGIN_PROCESSOR("fpl.login.loginProcessor",BeanConfigLocationType.DEFAULT_CONTEXT),
	LOGIN_PERSISTER("fpl.login.loginPersister",BeanConfigLocationType.DEFAULT_CONTEXT);

	private final String beanId;
	private final BeanConfigLocationType beanConfig;

	private LoginBean(final String beanId, final BeanConfigLocationType beanConfig) {
		this.beanId = beanId;
		this.beanConfig = beanConfig;
	}

	@Override
	public String getBeanId() {
		return beanId;
	}

	@Override
	public BeanConfigLocationType getBeanConfig() {
		return beanConfig;
	}
}


