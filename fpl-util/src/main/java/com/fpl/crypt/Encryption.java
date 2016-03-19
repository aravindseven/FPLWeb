package com.fpl.crypt;

public final class Encryption {

	private static final Encryption instance = new Encryption();
	private static final int SECRET_KEY_MIN_LENGTH = 10;
	
	private Encryption() {}
	
	private static Encryption getInstance() {
		return instance;
	}
	
	public static String decrypt(final String encryptedValue) {
		return getInstance().decryptImpl(encryptedValue,null);
	}

	public static String decrypt(final String encryptedValue, final String secretKey) {
		checkNotNull(secretKey,"secretKey");
		checkStringNotTooShort(secretKey,"secretKey",SECRET_KEY_MIN_LENGTH);
		return getInstance().decryptImpl(encryptedValue,secretKey);
	}
	
	private String decryptImpl(final String encryptedValue, final String secretKey) {
		checkNotNull(encryptedValue,"encryptedValue");
		return getSimmetricCrypting(secretKey).decrypt(encryptedValue);
	}
	
	public static String encrypt(final String valueToBeEncrypted) {
		return getInstance().encryptImpl(valueToBeEncrypted,null);
	}
	
	public static String encrypt(final String encryptedValue, final String secretKey) {
		checkNotNull(secretKey,"secretKey");
		checkStringNotTooShort(secretKey,"secretKey",SECRET_KEY_MIN_LENGTH);
		return getInstance().encryptImpl(encryptedValue,secretKey);
	}
	
	private String encryptImpl(final String valueToBeEncrypted, final String secretKey) {
		checkNotNull(valueToBeEncrypted,"valueToBeEncrypted");
		return getSimmetricCrypting(secretKey).encrypt(valueToBeEncrypted);
	}
	
	private static void checkNotNull(final String value, final String message) {
		if((value == null) || value.isEmpty()) {
			throw new NullPointerException(message + "cannot be null!");
		}
	}
	
	private static void checkStringNotTooShort(final String value, final String message, final int minLength) {
		if(value.length() < minLength) {
			throw new RuntimeException(message+ "cannot be shorter then: "+ minLength);
		}
	}
	
	private SimmetricCrypting getSimmetricCrypting(final String secretKey) {
		SimmetricCrypting simmetricCrypting = null;
		if(secretKey == null) {
			simmetricCrypting = SimmetricCryptingFactory.getInstance().getDailyChangingSimmetricCrypting();
		} else {
			simmetricCrypting = SimmetricCryptingFactory.getInstance().getSimmetricCrypting(secretKey);
		}
		return simmetricCrypting;
	}
}
