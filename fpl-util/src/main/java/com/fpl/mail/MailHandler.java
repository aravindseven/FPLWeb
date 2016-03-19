package com.fpl.mail;

public interface MailHandler {

	void sendMail(MailInfo mailInfo);
	
	void sendMailWithDefaultUser(MailInfo mailInfo);
}
