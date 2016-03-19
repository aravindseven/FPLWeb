package com.fpl.crypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class PropertiesTest {
	
	public static void main(final String[] args) throws Throwable {
		readConfigfile();
	}
	
	public static void readConfigfile() throws Exception {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle("com.fpl.crypt.Keyconfig");
		final String keyStr = resourceBundle.getString("key.85829558916032681");
		final byte[] bytes = decodeBase64(keyStr);
        final ByteArrayInputStream  secretKeyStream = new ByteArrayInputStream (bytes);
        final ObjectInputStream is = new ObjectInputStream(secretKeyStream);
        final SecretKey secretKey = (SecretKey) is.readObject();
        System.out.println(secretKey.getAlgorithm());
	}
	
	public static void writeConfigfile() throws Exception {
		final SecretKey secretKey = getSecretKey("DESede/ECB/PKCS5Padding", "147258369");
		final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(secretKey);
        final byte[] bytes = b.toByteArray();	
        final String str = encodeBase64(bytes);
		final Properties prop = new Properties();
		prop.setProperty("key.85829558916032681", str);
		final OutputStream output = new FileOutputStream("C:/Users/Yaazhsoft/workspace/FPL/fpl-util/src/test/java/com/fpl/crypt/Keyconfig.properties");
		prop.store(output, null);
		System.out.println("done");
	}
	
	public static SecretKey getSecretKey(final String cipherAlgorythm, final String secreatKey) throws Exception {
		final KeyGenerator keygen = KeyGenerator.getInstance(getAlgorythm(cipherAlgorythm));
		final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(secreatKey.getBytes());
		keygen.init(secureRandom);
		return keygen.generateKey();
	}

	private static String getAlgorythm(final String algorythm) {
		final int separatorPosition = algorythm.indexOf('/');
		return separatorPosition > -1 ? algorythm.substring(0,separatorPosition) : algorythm;
	}
	
	public static byte[] decodeBase64(final String encodedBase64Value) {
		try {
			return new BASE64Decoder().decodeBuffer(new String(encodedBase64Value.getBytes()));
		} catch (final IOException e) {
			throw new RuntimeException("Error while decoding the value: "+ encodedBase64Value, e);
		}
	}
	
	public static String encodeBase64(final byte[] toBeEncodedArray) {
		return new BASE64Encoder().encode(toBeEncodedArray);
	}

}
