package com.fpl.profile;

public enum ProfileStatus {

	PENDING("Pending"),
	VERIFIED("Verified");
	
	private String status;
	private ProfileStatus(final String status) {
		this.status = status;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
}
