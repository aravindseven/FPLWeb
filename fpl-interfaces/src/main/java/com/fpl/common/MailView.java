package com.fpl.common;

import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;


public class MailView {

	private String fromAddress;
	private String recivedDate;
	private String subject;
	private int messageNumber;
	private String content;
	private Collection<String> toAddress;
	private Collection<String> ccAddress;

	/**
	 * @return the ccAddress
	 */
	public Collection<String> getCcAddress() {
		return ccAddress;
	}
	/**
	 * @param ccAddress the ccAddress to set
	 */
	public void setCcAddress(final Collection<String> ccAddress) {
		this.ccAddress = ccAddress;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(final String content) {
		this.content = content;
	}
	/**
	 * @return the toAddress
	 */
	public Collection<String> getToAddress() {
		return toAddress;
	}
	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(final Collection<String> toAddress) {
		this.toAddress = toAddress;
	}
	/**
	 * @return the messageNumber
	 */
	public int getMessageNumber() {
		return messageNumber;
	}
	/**
	 * @param messageNumber the messageNumber to set
	 */
	public void setMessageNumber(final int messageNumber) {
		this.messageNumber = messageNumber;
	}
	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(final String fromAddress) {
		this.fromAddress = fromAddress;
	}
	/**
	 * @return the recivedDate
	 */
	public String getRecivedDate() {
		return recivedDate;
	}
	/**
	 * @param recivedDate the recivedDate to set
	 */
	public void setRecivedDate(final String recivedDate) {
		this.recivedDate = recivedDate;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(final String subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
