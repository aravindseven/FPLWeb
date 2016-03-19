package com.fpl.login;

public enum UserType {

	CUST_INDIVIDUAL("cust_individual"),
	CUST_CORPORATE("cust_corporate"),
	FP_INDIVIDUAL("fp_individual"),
	FP_CORPORATE("fp_corporate"),
	FP_ADMIN("fp_admin");
	
	private String user;
	private UserType(final String user) {
		this.user = user;
	}
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
}
