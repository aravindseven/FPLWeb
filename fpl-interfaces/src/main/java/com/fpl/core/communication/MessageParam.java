package com.fpl.core.communication;

import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.login.UserType;

public class MessageParam {
	private Long loginId;
	private UserType userType;
	private Long mailfromid;
	private Long mailtoid;
	private String msgid;
	private String message;
	private StringBuilder chatmessage;
	
	private String subject;
	private String date;
	private String sentip;
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public StringBuilder getChatmessage() {
		return chatmessage;
	}

	public void setChatmessage(StringBuilder chatmessage) {
		this.chatmessage = chatmessage;
	}

	public Long getMailfromid() {
		return mailfromid;
	}

	public void setMailfromid(Long mailfromid) {
		this.mailfromid = mailfromid;
	}

	public Long getMailtoid() {
		return mailtoid;
	}

	public void setMailtoid(String mailtoid) {
		this.mailtoid = Long.parseLong(mailtoid);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getSentip() {
		return sentip;
	}

	public void setSentip(String sentip) {
		this.sentip = sentip;
	}
}
