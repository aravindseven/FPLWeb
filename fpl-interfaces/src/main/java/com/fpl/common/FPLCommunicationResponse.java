package com.fpl.common;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FPLCommunicationResponse {

	private String status;
	private String reason;
	/*private String toMail;
	private String fromMail;
	private String message;*/
	
	
	private List<MailView> inboxInfo = new ArrayList<MailView>();
	
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	
	/**
	 * @param reason the reason to set
	 */
	public void setReason(final String reason) {
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the inboxInfo
	 */
	public List<MailView> getInboxInfo() {
		return inboxInfo;
	}

	/**
	 * @param inboxInfo the inboxInfo to set
	 */
	public void setInboxInfo(List<MailView> inboxInfo) {
		this.inboxInfo = inboxInfo;
	}

	
	
	
	
}
