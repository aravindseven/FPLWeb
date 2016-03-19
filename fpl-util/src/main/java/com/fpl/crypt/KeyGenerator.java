package com.fpl.crypt;

import javax.crypto.SecretKey;

public interface KeyGenerator {

	String getEncryptionPrefix();
	
	SecretKey getSecretKey();
	
	String getCipherAlgorythm();
}
