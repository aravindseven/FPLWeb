package com.fpl.core.aspect;

public interface IFPLBaseDecrypterSupport<T> {

	void decrypt(T encryptedObj);
	
	String getDecryptedValue(final String value);
	
	String getEncryptedValue(final Object value);
}
