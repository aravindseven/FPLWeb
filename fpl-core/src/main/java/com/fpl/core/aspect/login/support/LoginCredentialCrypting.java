package com.fpl.core.aspect.login.support;

import com.fpl.core.aspect.login.ILoginCredentialCrypting;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.login.entity.LoginCredential;
import com.fpl.util.StringUtil;

public class LoginCredentialCrypting implements ILoginCredentialCrypting {

	private static final String CRYPT_FIXED_KEY = CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier();
	
	@Override
	public void cryptLoginCredential(final LoginCredential credential) {
		final String password = credential.getPassword();
		final String email = credential.getMailId();
		final String alterEmail = credential.getAlternateMailId();
		credential.setPassword(getEncryptedValue(password));
		credential.setMailId(getEncryptedValue(email));
		credential.setAlternateMailId(getEncryptedValue(alterEmail));
	}
	
	@Override
	public void decrypt(final LoginCredential credential) {
		credential.setPassword(getDecryptedValue(credential.getPassword()));
		credential.setMailId(getDecryptedValue(credential.getMailId()));
		credential.setAlternateMailId(getDecryptedValue(credential.getAlternateMailId()));
	}
	
	@Override
	public String getEncryptedValue(final Object value) {
		String encryptedValue = null;
		if((value != null) && !value.toString().isEmpty()) {
			encryptedValue = Encryption.encrypt(value.toString(),CRYPT_FIXED_KEY);
		}
		return encryptedValue;
	}
	
	@Override
	public String getDecryptedValue(final String value) {
		String decryptedValue = null;
		if(!StringUtil.isEmpty(value)) {
			decryptedValue = Encryption.decrypt(value,CRYPT_FIXED_KEY);
		}
		return decryptedValue;
	}
}
