package com.fpl.crypt;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encrypter {
	
	private final KeyGenerator keyGenerator;
	
	private static Encrypter getInstance(final KeyGenerator keyGenerator) {
		return new Encrypter(keyGenerator);
	}
	
	protected Encrypter(final KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}
	
	private String decrypt(final String encryptedValue) {
		final Cipher desCipher = getCipher();
		try {
			desCipher.init(Cipher.DECRYPT_MODE, keyGenerator.getSecretKey());
			final String valueToBeDecrypt = encryptedValue.replaceFirst(keyGenerator.getEncryptionPrefix(), "");
			return new String(desCipher.doFinal(decodeBase64(valueToBeDecrypt)));
		} catch (final InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (final IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (final BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	static byte[] decodeBase64(final String encodedBase64Value) {
		try {
			return new BASE64Decoder().decodeBuffer(new String(encodedBase64Value.getBytes()));
		} catch (final IOException e) {
			throw new RuntimeException("Error while decoding the value: "+ encodedBase64Value, e);
		}
	}
	
	private static String encodeBase64(final byte[] toBeEncodedArray) {
		return new BASE64Encoder().encode(toBeEncodedArray);
	}
	
	private Cipher getCipher() {
		try {
			return Cipher.getInstance(keyGenerator.getCipherAlgorythm());
		} catch (final NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (final NoSuchPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String crypt(final String valueToBeCrypted) {
		try {
			final Cipher desCipher = getCipher();
			desCipher.init(Cipher.ENCRYPT_MODE, keyGenerator.getSecretKey());
			return keyGenerator.getEncryptionPrefix()+encodeBase64(desCipher.doFinal(valueToBeCrypted.getBytes()));
		} catch (final InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (final IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (final BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	static String decrypt(final KeyGenerator keyGenerator, final String encryptedValue) {
		return getInstance(keyGenerator).decrypt(encryptedValue);
	}
	
	static String crypt(final KeyGenerator keyGenerator, final String valueToBeCrypted) {
		return getInstance(keyGenerator).crypt(valueToBeCrypted);
	}
}
