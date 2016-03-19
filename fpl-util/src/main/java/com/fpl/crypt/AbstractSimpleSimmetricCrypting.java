package com.fpl.crypt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class AbstractSimpleSimmetricCrypting implements SimmetricCrypting {

	private static Map<String, HashSet<String>> methodGrants;
	
	protected AbstractSimpleSimmetricCrypting() {}
	
	@Override
	public final boolean isEncrypted(final String value) {
		return value.startsWith(getEncryptedValuePrefix());
	}
	
	@Override
	public final String encrypt(final String value) {
		checkInvokerClassPermission("encrypt");
		return getEncryptedValue(value);
	}
	
	@Override
	public final String encrypt(final String cryptingPrefix, final String value) {
		if(getEncryptedValuePrefix().equals(cryptingPrefix)) {
			return getEncryptedValue(value);
		} else {
			throw new RuntimeException("cryptingPrefix "+ cryptingPrefix + "not allowed to invoke CryptingProvide "+ this.getClass().getSimpleName());
		}
	}
	
	protected abstract String getEncryptedValuePrefix();
	
	protected abstract String getEncryptedValue(final String value);
	
	@Override
	public final String decrypt(final String encryptedValue) {
		checkInvokerClassPermission("decrypt");
		return getDecryptedValue(encryptedValue);
	}
	
	protected abstract String getDecryptedValue(final String value);
	
	private void checkInvokerClassPermission(final String methodName) {
		final Throwable throwable = new Throwable();
		final String[] invokerClassNames = new String[] {
			throwable.getStackTrace()[2].getClassName(),
			throwable.getStackTrace()[3].getClassName()
		};
		for(final String invokerClassName : invokerClassNames) {
			if(methodGrants.get(methodName).contains(invokerClassName)) {
				return;
			}
		}
		throw new RuntimeException("Class not allowed to invoke CryptingProvide "+ methodName + "method: "+Arrays.asList(invokerClassNames));
	}
	
	static {
		methodGrants = new HashMap<String, HashSet<String>>();
		methodGrants.put("encrypt", new HashSet<String>(Arrays.asList(new String[] {
			"com.fpl.crypt.Encryption"
		})));
		methodGrants.put("decrypt", new HashSet<String>(Arrays.asList(new String[] {
			"com.fpl.crypt.Encryption"
		})));
	}
}
