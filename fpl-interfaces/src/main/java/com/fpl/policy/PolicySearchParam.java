package com.fpl.policy;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.login.UserType;

public class PolicySearchParam {

	private Long loginId;
	private String policyId;
	private UserType userType;
	private Long customerId;
	private Long fplId;
	
	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the fplId
	 */
	public Long getFplId() {
		return fplId;
	}

	/**
	 * @param fplId the fplId to set
	 */
	public void setFplId(final Long fplId) {
		this.fplId = fplId;
	}

	/**
	 * @return the loginId
	 */
	public Long getLoginId() {
		return loginId;
	}
	
	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(final Long loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the policyId
	 */
	public String getPolicyId() {
		return policyId;
	}

	/**
	 * @param policyId the policyId to set
	 */
	public void setPolicyId(final String policyId) {
		this.policyId = policyId;
	}

	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(final UserType userType) {
		this.userType = userType;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
