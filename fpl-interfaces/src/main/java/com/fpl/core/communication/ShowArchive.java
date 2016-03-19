package com.fpl.core.communication;

import java.util.ArrayList;

public class ShowArchive {
	String MB_USER_TYPE;
	public ArrayList<MailArchivecontent> Aal=new ArrayList<MailArchivecontent>();
	public String getMB_USER_TYPE() {
		return MB_USER_TYPE;
	}
	public void setMB_USER_TYPE(String mB_USER_TYPE) {
		MB_USER_TYPE = mB_USER_TYPE;
	}
	public ArrayList<MailArchivecontent> getAl() {
		return Aal;
	}
	public void setAl(ArrayList<MailArchivecontent> al) {
		this.Aal = al;
	}


}
