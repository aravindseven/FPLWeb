package com.fpl.crypt;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import javax.crypto.SecretKey;

public class ConfiguredKeyGenerator extends AbstractKeyGenerator {

	public ConfiguredKeyGenerator(final String encryptionPrefix, final long secreatKey) {
		super(encryptionPrefix, 
			  KeyConfig.getInstance().getAlgorythm(secreatKey), 
			  KeyConfig.getInstance().getSecretKeyBuilder(secreatKey));
	}
	
	private static class KeyConfig extends ConfigProperties {
		
		public static final KeyConfig KEY_CONFIG = new KeyConfig();
				
		public static KeyConfig getInstance() {
			return KEY_CONFIG;
		}
		
		protected KeyConfig() {
			super("com.fpl.crypt.Keyconfig");
		}
		
		public SecretKeyBuilder getSecretKeyBuilder(final long secreatKey) {
			final SecretKey secretKey = getSecretKey(secreatKey);
			return new SecretKeyBuilder() {
				@Override
				public SecretKey getSecretKey() {
					return secretKey;
				}
			};
		}
		
		public SecretKey getSecretKey(final long secreatKey) {
			final String secreatKeyValue = getProperty("key."+secreatKey);
			if((secreatKeyValue == null) || secreatKeyValue.isEmpty()) {
				throw new RuntimeException("No configured key: "+ secreatKey);
			}
			try {
				final byte[] byteArray = Encrypter.decodeBase64(secreatKeyValue);
				final ObjectInputStream secreatKeyStream = new ObjectInputStream(new ByteArrayInputStream(byteArray));
				return (SecretKey) secreatKeyStream.readObject();
			} catch (final Exception e) {
				throw new RuntimeException("Error reading secreat key: "+ secreatKey, e);
			}
		}
		
		public String getAlgorythm(final long secreatKey) {
			final String cipherAlgorythm = getProperty("algorythm."+secreatKey);
			if((cipherAlgorythm == null) || cipherAlgorythm.isEmpty()) {
				throw new RuntimeException("No configured Algorythm for key: "+ secreatKey);
			}
			return cipherAlgorythm;
		}
	}
}
