package com.fpl.mail;

import java.util.Properties;

public class TLSMailHandler extends AbstractMailHandler {

	private static final String START_TLS_KEY = "mail.smtp.starttls.enable";
	
	@Override
	public Properties getProperties(final MailInfo mailInfo) {
		final Properties props = new Properties();
		props.put(SMTP_AUTH_KEY, "true");
		props.put(SMTP_HOST_KEY, mailInfo.getHost());
		props.put(SMTP_PORT_KEY, mailInfo.getPort());
		props.put(START_TLS_KEY, "true");
		return props;
	}
}
