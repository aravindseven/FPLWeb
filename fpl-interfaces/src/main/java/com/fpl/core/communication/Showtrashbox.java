package com.fpl.core.communication;

import java.util.ArrayList;

public class Showtrashbox {
	String MB_USER_TYPE;
	public ArrayList<MailTrashcontent> Tal=new ArrayList<MailTrashcontent>();
	public String getMB_USER_TYPE() {
		return MB_USER_TYPE;
	}
	public void setMB_USER_TYPE(String mB_USER_TYPE) {
		MB_USER_TYPE = mB_USER_TYPE;
	}
	public ArrayList<MailTrashcontent> getAl() {
		return Tal;
	}
	public void setAl(ArrayList<MailTrashcontent> al) {
		this.Tal = al;
	}

}
