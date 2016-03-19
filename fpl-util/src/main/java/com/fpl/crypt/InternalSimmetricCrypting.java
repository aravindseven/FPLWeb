package com.fpl.crypt;

final class InternalSimmetricCrypting extends AbstractSimpleSimmetricCrypting {

	private static final String ENC_PREFIX = "ENC-";
	private static final long ENCRYPTION_KEY = 85829558916032681L;
	
	private final KeyGenerator keyGenerator;
	
	InternalSimmetricCrypting() {
		this(new ConfiguredKeyGenerator(ENC_PREFIX, ENCRYPTION_KEY));
	}
	
	InternalSimmetricCrypting(final KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}
	
	@Override
	protected String getEncryptedValuePrefix() {
		return keyGenerator.getEncryptionPrefix();
	}
	
	@Override
	protected String getEncryptedValue(final String value) {
		return Encrypter.crypt(keyGenerator, value);
	}
	
	@Override
	protected String getDecryptedValue(final String encryptedValue) {
		return Encrypter.decrypt(keyGenerator, encryptedValue);
	}
}
