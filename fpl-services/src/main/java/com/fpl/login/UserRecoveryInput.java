package com.fpl.login;

public class UserRecoveryInput implements IUserRecoverInput {
	private String firstname;
	private String lastname;
	private String AltEmail;
	private String secretQuestion;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAltEmail() {
		return AltEmail;
	}
	public void setAltEmail(String altEmail) {
		this.AltEmail = altEmail;
	}
	public String getSecretQuestion() {
		return secretQuestion;
	}
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	
	
}
