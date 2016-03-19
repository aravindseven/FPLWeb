package com.fpl.crypt;

import java.util.List;

final class MultiSimmetricCrypting implements SimmetricCrypting {

	private final List<SimmetricCrypting> simpleSimmetricCryptingList;
	private final SimmetricCrypting principleSimmetricCrypting;
	
	MultiSimmetricCrypting(final List<SimmetricCrypting> simpleSimmetricCryptingList) {
		this.simpleSimmetricCryptingList = simpleSimmetricCryptingList;
		if((this.simpleSimmetricCryptingList == null) || this.simpleSimmetricCryptingList.isEmpty()) {
			throw new IllegalStateException("simpleSimmetricCryptingList can not be null or empty!");
		}
		this.principleSimmetricCrypting = this.simpleSimmetricCryptingList.get(0);
	}
	
	@Override
	public final boolean isEncrypted(final String value) {
		for(final SimmetricCrypting simpleSimmetricCrypting : getSimpleSimmetricCryptingList()) {
			if(simpleSimmetricCrypting.isEncrypted(value)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String encrypt(final String cryptingPrefix, final String value) {
		for(final SimmetricCrypting simpleSimmetricCrypting : getSimpleSimmetricCryptingList()) {
			if(simpleSimmetricCrypting.isEncrypted(cryptingPrefix)) {
				return simpleSimmetricCrypting.encrypt(value);
			}
		}
		throw new RuntimeException("No SimmetricCrypting found in "+getSimpleSimmetricCryptingList() + "for cryptingPrefix = "+ cryptingPrefix);
	}

	@Override
	public String encrypt(final String value) {
		return getPrincipleSimmetricCrypting().encrypt(value);
	}

	@Override
	public String decrypt(final String encryptedValue) {
		RuntimeException runtimeException = null;
		for(final SimmetricCrypting simpleSimmetricCrypting : getSimpleSimmetricCryptingList()) {
			if(simpleSimmetricCrypting.isEncrypted(encryptedValue)) {
				try {
					return simpleSimmetricCrypting.decrypt(encryptedValue);
				} catch (final RuntimeException e) {
					runtimeException = e;
				}
			}
		}
		if(runtimeException == null) {
			throw new RuntimeException("No SimmetricCrypting found in "+getSimpleSimmetricCryptingList() + "for encryptedValue = "+ encryptedValue);
		} else {
			throw runtimeException;
		}
	}

	private List<SimmetricCrypting> getSimpleSimmetricCryptingList() {
		return simpleSimmetricCryptingList;
	}

	public SimmetricCrypting getPrincipleSimmetricCrypting() {
		return principleSimmetricCrypting;
	}
}
