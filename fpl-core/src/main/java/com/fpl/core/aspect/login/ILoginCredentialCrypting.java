package com.fpl.core.aspect.login;

import com.fpl.core.aspect.IFPLBaseDecrypterSupport;
import com.fpl.login.entity.LoginCredential;

public interface ILoginCredentialCrypting extends IFPLBaseDecrypterSupport<LoginCredential> {

	void cryptLoginCredential(LoginCredential credential);
}
