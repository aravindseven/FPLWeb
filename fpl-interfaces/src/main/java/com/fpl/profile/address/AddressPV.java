package com.fpl.profile.address;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AddressPV extends Address {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
