package com.fpl.crypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public abstract class SecretKeyBuilder {

	public abstract SecretKey getSecretKey();

	protected final SecretKey createSecretKey(final String algorythm, final String secretKey) {
		return createSecretKey(algorythm, secretKey.hashCode());
	}

	protected final SecretKey createSecretKey(final String algorythm, final long secretKey) {
		try {
			final KeyGenerator keyGen = KeyGenerator.getInstance(getAlgorythm(algorythm));
			final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(secretKey);
			keyGen.init(secureRandom);
			return keyGen.generateKey();
		} catch (final NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private String getAlgorythm(final String algorythm) {
		final int separatorPosition = algorythm.indexOf('/');
		return separatorPosition > -1 ? algorythm.substring(0,separatorPosition) : algorythm;
	}
}
