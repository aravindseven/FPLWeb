package com.fpl.paging.config;

import com.fpl.factory.config.BeanConfigLocationType;
import com.fpl.factory.config.IBeanInfo;

public enum FPLPagingBean implements IBeanInfo {

	CRITERIA_PAGING("criteriaPagingDAOSupport",BeanConfigLocationType.DEFAULT_CONTEXT);
	
	private final String beanId;
	private final BeanConfigLocationType beanConfig;

	private FPLPagingBean(final String beanId, final BeanConfigLocationType beanConfig) {
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


