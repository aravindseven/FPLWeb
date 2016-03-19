package com.fpl.mail;

import org.apache.commons.lang.builder.ToStringBuilder;

public class MailInfo {
	
	private String host;
	private String port;
	private String fromAddress;
	private String toAddress;
	private String password;
	private String subject;
	private String message;
	
	public String getHost() {
		return host;
	}
	public void setHost(final String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(final String port) {
		this.port = port;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(final String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(final String toAddress) {
		this.toAddress = toAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(final String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(final String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(final String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
