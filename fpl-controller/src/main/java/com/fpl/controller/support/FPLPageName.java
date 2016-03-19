package com.fpl.controller.support;

public enum FPLPageName {
	
	INDEX("index1.jsp"),
	PASSWORD_RESET("ChangePassword.jsp"),
	PROFILE("Profile.jsp"),
	PROFILESETTINGS("settings_1.jsp"),
	ADMIN_MANAGE_PROFILE("AdminProfileManage.jsp"),
	REQUEST("Request.jsp"),
	POLICY("Policy.jsp"),
	SUBSCRIBE("subScribeNow.jsp"),
	ERROR("Error.jsp"),
	FIRST_TIME_PASSWORD_RESET("firstTimePassword.jsp"),	
	PASSWORD_RESET2("forget.jsp");
	private String pageName;
	private FPLPageName(final String pageName) {
		this.pageName = pageName;
	}
	
	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}
}
