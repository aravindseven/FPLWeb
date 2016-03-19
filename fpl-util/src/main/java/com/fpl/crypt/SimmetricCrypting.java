package com.fpl.crypt;

public interface SimmetricCrypting {

	String encrypt(final String cryptingPrefix, final String value);
	
	String encrypt(final String value);
	
	String decrypt(final String encryptedValue);
	
	boolean isEncrypted(final String value);
}
