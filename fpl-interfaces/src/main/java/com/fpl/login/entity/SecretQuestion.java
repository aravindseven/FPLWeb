package com.fpl.login.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SecretQuestion {

	private Long id;
	private String descrption;
	
	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public final void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * @return the descrption
	 */
	public final String getDescrption() {
		return descrption;
	}
	
	/**
	 * @param descrption the descrption to set
	 */
	public final void setDescrption(final String descrption) {
		this.descrption = descrption;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
