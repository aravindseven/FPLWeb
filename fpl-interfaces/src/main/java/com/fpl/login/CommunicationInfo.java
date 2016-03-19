package com.fpl.login;

import org.apache.commons.lang.builder.ToStringBuilder;


public class CommunicationInfo {

	private String recipient;
	private String subject;
	private String content;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(final String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
