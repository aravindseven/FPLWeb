package com.fpl.core.communication;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fpl.login.UserType;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MailInboxContent {
	String MI_ID;
    String MI_FROM_ID;
    String FROM_Name;
	String MI_TO_ID;
	String MI_SUBJECT;
	String MI_CONTENT;
	String MI_STAR_TYPE;
	String MI_SENT_IP;
	String MI_READ_IP;
	String MI_SENT_DATE_TIME;
	String MI_SENT_DATE;
	String MI_SENT_TIME;
	String MI_READ_DATE;
	ArrayList<MailInboxContent> threadmsg=new ArrayList<MailInboxContent>();
	
	public String getMI_ID() {
		return MI_ID;
	}
	public void setMI_ID(String mI_ID) {
		MI_ID = mI_ID;
	}
	public String getMI_FROM_ID() {
		return MI_FROM_ID;
	}
	public void setMI_FROM_ID(String mI_FROM_ID) {
		MI_FROM_ID = mI_FROM_ID;
	}
	public String getMI_TO_ID() {
		return MI_TO_ID;
	}
	public void setMI_TO_ID(String mI_TO_ID) {
		MI_TO_ID = mI_TO_ID;
	}
	public String getMI_SUBJECT() {
		return MI_SUBJECT;
	}
	public void setMI_SUBJECT(String mI_SUBJECT) {
		MI_SUBJECT = mI_SUBJECT;
	}
	public String getMI_CONTENT() {
		return MI_CONTENT;
	}
	public void setMI_CONTENT(String mI_CONTENT) {
		MI_CONTENT = mI_CONTENT;
	}
	public String getMI_STAR_TYPE() {
		return MI_STAR_TYPE;
	}
	public void setMI_STAR_TYPE(String mI_STAR_TYPE) {
		MI_STAR_TYPE = mI_STAR_TYPE;
	}
	public String getMI_SENT_IP() {
		return MI_SENT_IP;
	}
	public void setMI_SENT_IP(String mI_SENT_IP) {
		MI_SENT_IP = mI_SENT_IP;
	}
	public String getMI_READ_IP() {
		return MI_READ_IP;
	}
	public void setMI_READ_IP(String mI_READ_IP) {
		MI_READ_IP = mI_READ_IP;
	}
	public String getMI_SENT_DATE_TIME() {
		return MI_SENT_DATE_TIME;
	}
	public void setMI_SENT_DATE_TIME(String mI_SENT_DATE_TIME) {
		MI_SENT_DATE_TIME = mI_SENT_DATE_TIME;
	}
	public String getMI_SENT_DATE() {
		return MI_SENT_DATE;
	}
	public void setMI_SENT_DATE(String mI_SENT_DATE) {
		MI_SENT_DATE = mI_SENT_DATE;
	}
	public String getMI_SENT_TIME() {
		return MI_SENT_TIME;
	}
	public void setMI_SENT_TIME(String mI_SENT_TIME) {
		MI_SENT_TIME = mI_SENT_TIME;
	}
	public String getMI_READ_DATE() {
		return MI_READ_DATE;
	}
	public void setMI_READ_DATE(String mI_READ_DATE) {
		MI_READ_DATE = mI_READ_DATE;
	}
	public String getFROM_Name() {
		return FROM_Name;
	}
	public void setFROM_Name(String fROM_Name) {
		FROM_Name = fROM_Name;
	}
	public ArrayList<MailInboxContent> getThreadmsg() {
		return threadmsg;
	}
	public void setThreadmsg(ArrayList<MailInboxContent> threadmsg) {
		this.threadmsg = threadmsg;
	}
	public void makePojoFromBson( DBObject bson )
    {
        BasicDBObject b = ( BasicDBObject ) bson;

        this.MI_ID   = b.get( "MI_ID" ).toString();
        this.MI_FROM_ID  =  (String) b.get( "MI_FROM_ID" );
        this.MI_TO_ID  =  (String) b.get( "MI_TO_ID" );
        this.MI_CONTENT  =  (String) b.get( "MI_CONTENT" );
        this.MI_SUBJECT  =  (String) b.get( "MI_SUBJECT" );
        this.MI_STAR_TYPE  =  (String) b.get( "MI_STAR_TYPE" );
        this.MI_SENT_IP  =  (String) b.get( "MI_SENT_IP" );
        this.MI_READ_IP  =  (String) b.get( "MI_READ_IP" );
        this.MI_SENT_DATE_TIME  =  (String) b.get( "MI_SENT_DATE_TIME" );
        this.MI_SENT_DATE  =  (String) b.get( "MI_SENT_DATE" );
        this.MI_SENT_TIME  =  (String) b.get( "MI_SENT_TIME" );
        this.MI_READ_DATE  =  (String) b.get( "MI_READ_DATE" );
        //System.out.println("makefrompojo");
    }
	public void Threadmsg( DBObject bson )
    {
        BasicDBObject b = ( BasicDBObject ) bson;
        MailInboxContent m1=new MailInboxContent();
         m1.MI_ID   = b.get( "MI_ID" ).toString();
         m1.MI_FROM_ID  =  (String) b.get( "MI_FROM_ID" );
         m1.MI_TO_ID  =  (String) b.get( "MI_TO_ID" );
         m1.MI_CONTENT  =  (String) b.get( "MI_CONTENT" );
         m1.MI_SUBJECT  =  (String) b.get( "MI_SUBJECT" );
         m1.MI_STAR_TYPE  =  (String) b.get( "MI_STAR_TYPE" );
         m1.MI_SENT_IP  =  (String) b.get( "MI_SENT_IP" );
         m1.MI_READ_IP  =  (String) b.get( "MI_READ_IP" );
         m1.MI_SENT_DATE_TIME  =  (String) b.get( "MI_SENT_DATE_TIME" );
         m1.MI_SENT_DATE  =  (String) b.get( "MI_SENT_DATE" );
         m1.MI_SENT_TIME  =  (String) b.get( "MI_SENT_TIME" );
         m1.MI_READ_DATE  =  (String) b.get( "MI_READ_DATE" );
         threadmsg.add(m1);
       // System.out.println("threadmsg pojo");
    }

	
}
