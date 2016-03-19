package com.fpl.core.aspect.login;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.fpl.login.ILoginInputParam;
import com.fpl.login.ILoginRecoveryInput;
import com.fpl.login.entity.LoginCredential;

@Aspect
public class LoginCredentialCryptingAspect {
	
	private ILoginCredentialCrypting credentialCrypting;
	
	@Pointcut("execution(* com.fpl.persistence.login.LoginCredentialDAO.*(com.fpl.login.entity.LoginCredential,..)) "
			+ "&& args(entity,..)")
	private void encryptLoginCredentialOperation(final LoginCredential entity) {}
	
	@Pointcut("execution(* com.fpl.persistence.*.*.*(com.fpl.login.ILoginInputParam)) && args(loginParam)")
	private void encryptLoginInputOperation(final ILoginInputParam loginParam) {}

	@Pointcut("execution(* com.fpl.persistence.*.*.*(com.fpl.login.ILoginRecoveryInput)) && args(loginRecovery)")
	private void encryptLoginRecoveryOperation(final ILoginRecoveryInput loginRecovery) {}
	
	@Before("encryptLoginCredentialOperation(entity)")
	public void encryptLoginCredential(final LoginCredential entity) {
		System.out.println("LoginCredentialCryptingAspect --> encryptLoginCredential ");
		credentialCrypting.cryptLoginCredential(entity);
	}

	@After("encryptLoginCredentialOperation(entity)")
	public void decryptLoginCredential(final LoginCredential entity) {
		credentialCrypting.decrypt(entity);
	}

	@Before("encryptLoginInputOperation(loginParam)")
	public void encryptLoginInput(final ILoginInputParam loginParam) {
		final String password = credentialCrypting.getEncryptedValue(loginParam.getPassword());
		final String email = credentialCrypting.getEncryptedValue(loginParam.getPrimaryEmailId());
		loginParam.setPrimaryEmailId(email);
		loginParam.setPassword(password);
	}

	@After("encryptLoginInputOperation(loginParam)")
	public void decryptLoginInput(final ILoginInputParam loginParam) {
		final String password = credentialCrypting.getDecryptedValue(loginParam.getPassword());
		final String email = credentialCrypting.getDecryptedValue(loginParam.getPrimaryEmailId());
		loginParam.setPrimaryEmailId(email);
		loginParam.setPassword(password);
	}
	
	@Before("encryptLoginRecoveryOperation(loginRecovery)")
	public void encryptLoginRecovery(final ILoginRecoveryInput loginRecovery) {
		final String email = credentialCrypting.getEncryptedValue(loginRecovery.getPrimaryEmailId());
		final String altEmail = credentialCrypting.getEncryptedValue(loginRecovery.getAltEmail());
		loginRecovery.setPrimaryEmailId(email);
		loginRecovery.setAltEmail(altEmail);
	}
	
	@After("encryptLoginRecoveryOperation(loginRecovery)")
	public void decryptLoginRecovery(final ILoginRecoveryInput loginRecovery) {
		final String email = credentialCrypting.getDecryptedValue(loginRecovery.getPrimaryEmailId());
		final String altEmail = credentialCrypting.getDecryptedValue(loginRecovery.getAltEmail());
		loginRecovery.setPrimaryEmailId(email);
		loginRecovery.setAltEmail(altEmail);
	}
	
	/**
	 * @param credentialCrypting the credentialCrypting to set
	 */
	public void setCredentialCrypting(final ILoginCredentialCrypting credentialCrypting) {
		this.credentialCrypting = credentialCrypting;
	}
}
