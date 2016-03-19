package com.fpl.mail;

public class MailHandlerFactory {

	private MailHandlerFactory() {}
	
	public static MailHandler getMailSender(final MailType mailType)  {
		MailHandler mailHandler = null;
		if(MailType.SSL.equals(mailType)) {
			mailHandler = new SSLMailHandler();
		} else {
			mailHandler = new TLSMailHandler();
		}
		return mailHandler;
	}
}
