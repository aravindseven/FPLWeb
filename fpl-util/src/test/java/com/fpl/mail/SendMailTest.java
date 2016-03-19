package com.fpl.mail;

public class SendMailTest {
	
	public static void main(final String[] args) {
		final MailHandler mailHandler = MailHandlerFactory.getMailSender(MailType.SSL);
		final MailInfo mailInformation = new MailInfo();
		mailInformation.setHost("smtp.gmail.com");
		mailInformation.setPort("465");
		mailInformation.setFromAddress("murali.p@fuzzy15s.com");
		mailInformation.setPassword("***");
		mailInformation.setSubject("Testing Subject");
		mailInformation.setToAddress("murali.p@yaazhsoft.com");
		mailInformation.setMessage("Dear Mail Crawler,\n\n No spam to my email, please!");
		mailHandler.sendMail(mailInformation);
		System.out.println("done");
	}
}