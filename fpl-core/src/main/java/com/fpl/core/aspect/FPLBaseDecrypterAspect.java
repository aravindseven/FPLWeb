package com.fpl.core.aspect;

import java.util.Map;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import com.fpl.core.communication.UserInfo;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.login.UserLoginInfo;
import com.fpl.util.StringUtil;

@Aspect
public class FPLBaseDecrypterAspect {
	
	private Map<String, IFPLBaseDecrypterSupport> decrypterSupportMap;
	
	@AfterReturning(pointcut="execution(* com.fpl.persistence.*.*.*(..))", returning="result")
	public void decryptData(final Object result) {
		final Class resultClazz = result != null ? result.getClass() : String.class;
		if(resultClazz.toString().contains("com.fpl")) {
			final String key = resultClazz.getSimpleName();
			final IFPLBaseDecrypterSupport decrypterSupport = decrypterSupportMap.get(key);
			if(decrypterSupport != null) {
				decrypterSupport.decrypt(result);
			} else if(result instanceof UserLoginInfo) {
				final UserLoginInfo info = (UserLoginInfo) result;
				if(!StringUtil.isEmpty(info.getPassword())) {
					info.setPassword(Encryption.decrypt(info.getPassword(),CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier()));
				}
				if(!StringUtil.isEmpty(info.getEmail())) {
					info.setEmail(Encryption.decrypt(info.getEmail(),CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier()));
				}
				if(!StringUtil.isEmpty(info.getAlternateMailId())) {
					info.setAlternateMailId(Encryption.decrypt(info.getAlternateMailId(),CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier()));
				}
			} else if(result instanceof UserInfo) {
				final UserInfo info = (UserInfo) result;
				if(!StringUtil.isEmpty(info.getEmail())) {
					info.setEmail(Encryption.decrypt(info.getEmail(),CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier()));
				}
				if(!StringUtil.isEmpty(info.getAlternateEmail())) {
					info.setAlternateEmail(Encryption.decrypt(info.getAlternateEmail(),CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier()));
				}
				
			}
		}
	}

	/**
	 * @param decrypterSupportMap the decrypterSupportMap to set
	 */
	public void setDecrypterSupportMap(
			final Map<String, IFPLBaseDecrypterSupport> decrypterSupportMap) {
		this.decrypterSupportMap = decrypterSupportMap;
	}
}
