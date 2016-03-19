package com.fpl.core.communication;

import java.util.ArrayList;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.login.UserType;

public class MaildraftBox {
	private Long loginId;
	private UserType userType;
	String MB_USER_TYPE;
	public ArrayList<MaildraftContent> dal=new ArrayList<MaildraftContent>();
	public String getMB_USER_TYPE() {
		return MB_USER_TYPE;
	}
	public void setMB_USER_TYPE(String mB_USER_TYPE) {
		MB_USER_TYPE = mB_USER_TYPE;
	}
	public ArrayList<MaildraftContent> getAl() {
		return dal;
	}
	public void setAl(ArrayList<MaildraftContent> al) {
		this.dal = al;
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
