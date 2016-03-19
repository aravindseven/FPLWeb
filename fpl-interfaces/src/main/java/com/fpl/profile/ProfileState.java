package com.fpl.profile;

public enum ProfileState {

	ACTIVE("active"),
	DEACTIVE("deactive");
	
	private String value;
	private ProfileState(final String value) {
		this.value = value;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
