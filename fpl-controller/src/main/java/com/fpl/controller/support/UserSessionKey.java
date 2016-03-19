package com.fpl.controller.support;

public enum UserSessionKey {

	USER_LOGIN_INFO("UserLoginInfo"),
	NEW_REQUEST_PV("NewRequestPV");
	
	private String key;
	private UserSessionKey(final String key) {
		this.key = key;
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}
