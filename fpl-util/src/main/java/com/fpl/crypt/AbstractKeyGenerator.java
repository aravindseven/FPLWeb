package com.fpl.crypt;

import javax.crypto.SecretKey;

abstract class AbstractKeyGenerator implements KeyGenerator {

	private final String ENCRYPTION_PREFIX;
	private final String CIPHER_ALGORYTHM;
	private final SecretKeyBuilder SECRET_KEY_BUILDER;
	protected static final String DEFALUT_CIPHER_ALGORYTHM = "DESede/ECB/PKCS5Padding"; //AES/CBC/PKCS5Padding
	
	AbstractKeyGenerator(final String encryptionPrefix, final String cipherAlgorythm, final SecretKeyBuilder secretKeyBuilder) {
		this.ENCRYPTION_PREFIX = encryptionPrefix;
		this.CIPHER_ALGORYTHM = cipherAlgorythm;
		this.SECRET_KEY_BUILDER = secretKeyBuilder;
	}

	@Override
	public String getEncryptionPrefix() {
		return ENCRYPTION_PREFIX;
	}
	
	@Override
	public SecretKey getSecretKey() {
		return SECRET_KEY_BUILDER.getSecretKey();
	}
	
	@Override
	public String getCipherAlgorythm() {
		return CIPHER_ALGORYTHM;
	}
	
}
