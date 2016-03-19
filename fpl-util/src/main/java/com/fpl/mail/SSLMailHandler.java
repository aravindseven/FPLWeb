package com.fpl.mail;

import java.util.Properties;

public class SSLMailHandler extends AbstractMailHandler {
	
	private static final String SOCKET_FACTORY_PORT_KEY = "mail.smtp.socketFactory.port";
	private static final String SOCKET_FACTORY_CLASS_KEY = "mail.smtp.socketFactory.class";
	private static final String SOCKET_FACTORY_CLASS = "javax.net.ssl.SSLSocketFactory";
	
	@Override
	public Properties getProperties(final MailInfo mailInfo) {
		final Properties props = new Properties();
		props.put(SMTP_HOST_KEY, mailInfo.getHost());
		props.put(SOCKET_FACTORY_PORT_KEY, mailInfo.getPort());
		props.put(SOCKET_FACTORY_CLASS_KEY, SOCKET_FACTORY_CLASS);
		props.put(SMTP_AUTH_KEY, "true");
		props.put(SMTP_PORT_KEY, mailInfo.getPort());
		return props;
	}
}
