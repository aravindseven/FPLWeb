package com.fpl.core.aspect.login.support;

import com.fpl.core.aspect.IFPLBaseDecrypterSupport;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.login.entity.LoginSupport;
import com.fpl.util.StringUtil;

public class LoginSupportCrypting implements IFPLBaseDecrypterSupport<LoginSupport> {

	private static final String CRYPT_FIXED_KEY = CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier();
	
	@Override
	public void decrypt(final LoginSupport support) {
		support.setPasswordOne(getDecryptedValue(support.getPasswordOne()));
		support.setPasswordTwo(getDecryptedValue(support.getPasswordTwo()));
		support.setPasswordThree(getDecryptedValue(support.getPasswordThree()));
	}

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
