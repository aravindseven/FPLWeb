package com.fpl.crypt;

import javax.crypto.SecretKey;

public class FixedKeyGenerator extends AbstractKeyGenerator {

	private static final String DEFALUT_ENC_PREFIX = "ENC-";
	
	public FixedKeyGenerator(final String secretKey) {
		this(DEFALUT_ENC_PREFIX,DEFALUT_CIPHER_ALGORYTHM,secretKey);
	}
	
	protected FixedKeyGenerator(final String encryptionPrefix, final String algorythm, final String secretKey) {
		super(encryptionPrefix, algorythm, getSecretKeyBuilder(algorythm, secretKey));
	}
	
	private static SecretKeyBuilder getSecretKeyBuilder(final String algorythm, final String secretKey) {
		return new SecretKeyBuilder() {
			private final SecretKey internalSecretKey = createSecretKey(algorythm, secretKey);
			@Override
			public SecretKey getSecretKey() {
				return internalSecretKey;
			}
		};
	}
}
