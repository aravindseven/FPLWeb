package com.fpl.core.tips;

import java.util.ArrayList;

import com.fpl.core.communication.MailInboxContent;
import com.fpl.login.UserType;

public class AdminTips {
   //private	String  MB_USER_ID;
   //private	UserType  MS_USER_TYPE;
   //private  String  MS_PROFILE_PIC;
   //private  String MS_ARCHIVE_DURATION;
   //private  String MS_AUTO_RES;
  // private  String MS_AUTO_RES_MESS;
  // private  String MS_NOTIFICATION;
  // private  String MS_STARS;
   
   private String Tip_Name;
   private String Tip_Description;
   public ArrayList<AdminTips> al=new ArrayList<AdminTips>();
   public String getTip_Name() {
		return Tip_Name;
	}
	public void setTip_Name(String tip_Name) {
		Tip_Name = tip_Name;
	}
	
	 public String getTip_Description() {
			return Tip_Description;
		}
		public void setTip_Description(String tip_Description) {
			Tip_Description = tip_Description;
		}
	//a
	
	
	public ArrayList<AdminTips> getAl() {
		return al;
	}
	public void setAl(ArrayList<AdminTips> a1l) {
		this.al = a1l;
	}
	
	
	
	
   /*public String getMB_USER_ID() {
	return MB_USER_ID;
}
public void setMB_USER_ID(String mB_USER_ID) {
	MB_USER_ID = mB_USER_ID;
}
public UserType getMS_USER_TYPE() {
	return MS_USER_TYPE;
}
public void setMS_USER_TYPE(UserType mS_USER_TYPE) {
	MS_USER_TYPE = mS_USER_TYPE;
}
public String getMS_PROFILE_PIC() {
	return MS_PROFILE_PIC;
}
public void setMS_PROFILE_PIC(String mS_PROFILE_PIC) {
	MS_PROFILE_PIC = mS_PROFILE_PIC;
}
public String getMS_ARCHIVE_DURATION() {
	return MS_ARCHIVE_DURATION;
}
public void setMS_ARCHIVE_DURATION(String mS_ARCHIVE_DURATION) {
	MS_ARCHIVE_DURATION = mS_ARCHIVE_DURATION;
}
public String getMS_AUTO_RES() {
	return MS_AUTO_RES;
}
public void setMS_AUTO_RES(String mS_AUTO_RES) {
	MS_AUTO_RES = mS_AUTO_RES;
}
public String getMS_AUTO_RES_MESS() {
	return MS_AUTO_RES_MESS;
}
public void setMS_AUTO_RES_MESS(String mS_AUTO_RES_MESS) {
	MS_AUTO_RES_MESS = mS_AUTO_RES_MESS;
}
public String getMS_NOTIFICATION() {
	return MS_NOTIFICATION;
}
public void setMS_NOTIFICATION(String mS_NOTIFICATION) {
	MS_NOTIFICATION = mS_NOTIFICATION;
}
public String getMS_STARS() {
	return MS_STARS;
}
public void setMS_STARS(String mS_STARS) {
	MS_STARS = mS_STARS;
}*/


}
