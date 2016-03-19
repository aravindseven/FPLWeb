package com.fpl.core.communication;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.login.UserType;

public class MailInbox {
	private Long loginId;
	private UserType userType;
	String MB_USER_TYPE;
	public int getNumberofMails() {
		return NumberofMails;
	}
	public void setNumberofMails(int numberofMails) {
		NumberofMails = numberofMails;
	}

	private int NumberofMails;
	public ArrayList<MailInboxContent> al=new ArrayList<MailInboxContent>();
	public String getMB_USER_TYPE() {
		return MB_USER_TYPE;
	}
	public void setMB_USER_TYPE(String mB_USER_TYPE) {
		MB_USER_TYPE = mB_USER_TYPE;
	}
	public ArrayList<MailInboxContent> getAl() {
		return al;
	}
	public void setAl(ArrayList<MailInboxContent> al) {
		this.al = al;
	}
	
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

	
}
