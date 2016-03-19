package com.fpl;

import com.fpl.login.LoginCredentialServices;
import com.fpl.login.LoginRegisterParam;

public class LoginCredentialServicesTest {

	public static void main(final String[] args) {
		final LoginCredentialServices services = new LoginCredentialServices();
		final LoginRegisterParam loginRegister = new LoginRegisterParam();
		loginRegister.setAlternateMailId("murali4fnz@gmail.com");
		loginRegister.setPrimaryEmailId("murali.p@yaazhsoft.com");
		loginRegister.setCountry("india");
		loginRegister.setMobileNumber("9789019878");
		loginRegister.setPassword("Qwer@1234");
		loginRegister.setSecretAnswer("1234");
		loginRegister.setSecretQuestion("What is your Mother's Maiden Name?");
		loginRegister.setUserType("cust_individual");
		services.registerLogin(loginRegister);
	}
}
