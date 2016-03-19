package com.fpl.core.communication;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MailTrashcontent {
	String MI_ID;
    String MI_FROM_ID;
	String MI_TO_ID;
	String MI_SUBJECT;
	String MI_CONTENT;
	String MI_STAR_TYPE;
	String MI_SENT_DATE;
	String MI_READ_DATE;
	String MI_TRASHDATE;
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
	public String getMI_SENT_DATE() {
		return MI_SENT_DATE;
	}
	public void setMI_SENT_DATE(String mI_SENT_DATE) {
		MI_SENT_DATE = mI_SENT_DATE;
	}
	public String getMI_READ_DATE() {
		return MI_READ_DATE;
	}
	public void setMI_READ_DATE(String mI_READ_DATE) {
		MI_READ_DATE = mI_READ_DATE;
	}
	public String getMI_TRASHDATE() {
		return MI_TRASHDATE;
	}
	public void setMI_TRASHDATE(String mI_TRASHDATE) {
		MI_TRASHDATE = mI_TRASHDATE;
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
        this.MI_SENT_DATE  =  (String) b.get( "MI_SENT_DATE" );
        this.MI_READ_DATE  =  (String) b.get( "MI_READ_DATE" );
        this.MI_TRASHDATE  =  (String) b.get( "MI_TRASH_DATE" );
        System.out.println("makefrompojo");
    }
}
