package com.fpl.login;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.login.entity.User;

public class LoginRecordTO {
	private long id;	
	 private String sessionId;
	 private String userId  ;	
	 private String devicetype ; 				
	 private String  deviceid  ; 				
	 private String loginresult  ;			
	 private String resultreason ;  		    
	 private long logincrediantialid ;  	
	 private String chatstatus;   			    
	 private String ipaddress ;  				
	 private String logindate;				
	 private String logoutdate;
	 //private User user;
	public long getId() {
			return id;
	}
	public void setId(long id) {
			this.id = id;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getLoginresult() {
		return loginresult;
	}
	public void setLoginresult(String loginresult) {
		this.loginresult = loginresult;
	}
	public String getResultreason() {
		return resultreason;
	}
	public void setResultreason(String resultreason) {
		this.resultreason = resultreason;
	}
	public long getLogincrediantialid() {
		return logincrediantialid;
	}
	public void setLogincrediantialid(long logincrediantialid) {
		this.logincrediantialid = logincrediantialid;
	}
	public String getChatstatus() {
		return chatstatus;
	}
	public void setChatstatus(String chatstatus) {
		this.chatstatus = chatstatus;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getLogindate() {
		return logindate;
	}
	public void setLogindate(String logindate) {
		this.logindate = logindate;
	}
	public String getLogoutdate() {
		return logoutdate;
	}
	public void setLogoutdate(String logoutdate) {
		this.logoutdate = logoutdate;
	}
	/*public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
