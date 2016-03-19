package com.fpl.core.aspect.login;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.fpl.core.aspect.IFPLBaseDecrypterSupport;
import com.fpl.login.entity.LoginSupport;

@Aspect
public class LoginSupportCryptingAspect {

	private IFPLBaseDecrypterSupport<LoginSupport> baseDecrypterSupport;
	
	@Pointcut("execution(* com.fpl.persistence.login.LoginSupportDAO.*(com.fpl.login.entity.LoginSupport,..)) "
			+ "&& args(support,*)")
	private void encryptLoginSupportOperation(final LoginSupport support) {}

	@Before("encryptLoginSupportOperation(support)")
	public void encryptLoginSupport(final LoginSupport support) {
		System.out.println("LoginSupportCryptingAspect --> encryptLoginSupport ");
		support.setPasswordOne(baseDecrypterSupport.getEncryptedValue(support.getPasswordOne()));
		support.setPasswordTwo(baseDecrypterSupport.getEncryptedValue(support.getPasswordTwo()));
		support.setPasswordThree(baseDecrypterSupport.getEncryptedValue(support.getPasswordThree()));
	}

	@After("encryptLoginSupportOperation(support)")
	public void decryptLoginSupport(final LoginSupport support) {
		System.out.println("LoginSupportCryptingAspect --> decryptLoginSupport ");
		baseDecrypterSupport.decrypt(support);
	}
	
	/**
	 * @param baseDecrypterSupport the baseDecrypterSupport to set
	 */
	public void setBaseDecrypterSupport(
			final IFPLBaseDecrypterSupport<LoginSupport> baseDecrypterSupport) {
		this.baseDecrypterSupport = baseDecrypterSupport;
	}
}
