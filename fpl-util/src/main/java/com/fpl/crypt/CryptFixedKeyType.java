package com.fpl.crypt;

public enum CryptFixedKeyType {

	DEFAULT_KEY("85829558916032681");
	
	private String keyIdentifier;
	private CryptFixedKeyType(final String keyIdentifier) {
		this.keyIdentifier = keyIdentifier;
	}
	
	/**
	 * @return the keyIdentifier
	 */
	public String getKeyIdentifier() {
		return keyIdentifier;
	}
}
