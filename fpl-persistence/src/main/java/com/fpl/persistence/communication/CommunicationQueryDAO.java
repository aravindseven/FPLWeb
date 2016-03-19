 package com.fpl.persistence.communication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.fpl.core.communication.MailInbox;
import com.fpl.core.communication.MailInboxContent;
import com.fpl.core.communication.MaildraftBox;
import com.fpl.core.communication.MaildraftContent;
import com.fpl.core.communication.MessageParam;
import com.fpl.core.communication.SettingsContent;
import com.fpl.core.communication.UserInfo;
import com.fpl.core.communication.UserSearchParam;
import com.fpl.core.communication.Usersettings;
import com.fpl.core.tips.AdminTips;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.login.UserType;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.financialplanner.FinancialPlannerDAO;
import com.fpl.persistence.login.ILoginSupportDAO;
import com.fpl.util.DateUtil;
import com.fpl.util.StringUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class CommunicationQueryDAO implements ICommunicationQueryDAO {

	private JdbcTemplate jdbcTemplate;
	MongoConfig mc=new MongoConfig();
	@Autowired
	private ICustomerDAO customerDAO;
	@Autowired
	private ILoginSupportDAO longinsupport;
	boolean freshmail1;
	boolean freshmail2;
	Map<List<String>, List<UserInfo>> myMap = new HashMap<List<String>, List<UserInfo>>();
	List<String> user=new ArrayList();
	List<UserInfo> userobj=new ArrayList();
	String copymailfrom = null;
	@Override
	public List<UserInfo> getUserList(final UserSearchParam searchParam) {
		final StringBuilder query = new StringBuilder();
		query.append(" select lc_id, ut_descrption, lc_mail_id, lc_alternate_maill_id, ls_online,ls_status ");
		query.append("   from fpl_ma_login_credential lc, fpl_ma_login_support ls, fpl_ma_user_type ut ");
		query.append("  where lc.lc_id = ls.ls_lc_id ");
		query.append("   and lc.lc_ut_id = ut.ut_id ");
		query.append("   and ut_descrption != ? ");
		if(searchParam.isOnline() != null) {
			query.append("   and ls.ls_online = ? ");
		}
		if(searchParam.getUserTypeCollection() != null && !searchParam.getUserTypeCollection().isEmpty()) {
			final int size = searchParam.getUserTypeCollection().size();
			query.append("   and ut.ut_descrption in ( ");
			for(int count = 0; count < size-1; count++) {
				query.append("?, ");
			}
			query.append("? )");
		}
		return jdbcTemplate.query(query.toString(), new PreparedStatementSetter() {
			@Override
			public void setValues(final PreparedStatement statement) throws SQLException {
				int index = 1;
				statement.setString(index++, UserType.FP_ADMIN.getUser());
				if(searchParam.isOnline() != null) {
					final int online = searchParam.isOnline().booleanValue() ? 0 : 1;
					statement.setInt(index++, online);
				}
				if(searchParam.getUserTypeCollection() != null && !searchParam.getUserTypeCollection().isEmpty()) {
					for(final UserType userType :  searchParam.getUserTypeCollection()) {
						statement.setString(index++, userType.getUser());
					}
				}
			}
		}, new RowMapper<UserInfo>() {
			@Override
			public UserInfo mapRow(final ResultSet resultSet, final int arg1) throws SQLException {
				final UserInfo userInfo = new UserInfo();
				
				userInfo.setLoginId(resultSet.getLong("lc_id"));
				userInfo.setUserType(UserType.valueOf(resultSet.getString("ut_descrption").toUpperCase()));
				
				if(StringUtil.isNotEmpty(resultSet.getString("lc_mail_id"))) {
					userInfo.setEmail(Encryption.decrypt(resultSet.getString("lc_mail_id"), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier()));
				}
				if(StringUtil.isNotEmpty(resultSet.getString("lc_alternate_maill_id"))) {
					userInfo.setAlternateEmail(Encryption.decrypt(resultSet.getString("lc_alternate_maill_id"), CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier()));
				}
				if(resultSet.getInt("ls_online") == 0) {
					userInfo.setOnline(false);
					userInfo.setStatus("3");
				} else {
					userInfo.setOnline(true);
					userInfo.setStatus("1");
				}
				if(resultSet.getString("ls_status") == null) {
					if(resultSet.getInt("ls_online") == 0)
					{
						userInfo.setMood_status("offline");
					}
					else
					{
						userInfo.setMood_status("online");
					}
					
				}
				else if(resultSet.getString("ls_status").equalsIgnoreCase("Online"))
				{
					userInfo.setMood_status(resultSet.getString("ls_status"));
					userInfo.setStatus("1");
				}
				else if(resultSet.getString("ls_status").equalsIgnoreCase("Busy"))
				{
					userInfo.setMood_status(resultSet.getString("ls_status"));
					userInfo.setStatus("2");
				}
				else if(resultSet.getString("ls_status").equalsIgnoreCase("Offline"))
				{
					userInfo.setMood_status(resultSet.getString("ls_status"));
					userInfo.setStatus("3");
				}
				else if(resultSet.getString("ls_status").equalsIgnoreCase("Away"))
				{
					userInfo.setMood_status(resultSet.getString("ls_status"));
					userInfo.setStatus("4");
				}
				else if(resultSet.getString("ls_status").equalsIgnoreCase("Do Not Disturb"))
				{
					userInfo.setMood_status(resultSet.getString("ls_status"));
					userInfo.setStatus("5");
				}
				else {
					userInfo.setMood_status(resultSet.getString("ls_status"));
				}
				/*user.add(userInfo.getLoginId().toString());
				userobj.add(userInfo);
				myMap.put(user, userobj);*/
				return userInfo;
			}
		});
	}
	
	@Override
	public Boolean postMessage(final MessageParam messageParam) {
		System.out.println("Inside Service Persistebce");
		DBCollection SENTBOX = mc.getSentBox();
		DBCollection INBOX = mc.getInBox();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		Date now = new Date();
		String msgdate=sdf.format(now);
		String dateString=sdf.format(now).replaceAll("-", "")
                .replaceAll(":", "");
		String msgid=messageParam.getMailfromid()+""+messageParam.getMailtoid()+dateString;
		try {

			/**** Insert ****/
			
			BasicDBObject query = new BasicDBObject();
			query.append("MB_USER_ID", messageParam.getMailfromid());
		    DBCursor result = SENTBOX.find(query);//checking the document for particular user exists or not
		    int resultcount=result.size();
		    System.out.println(result.size());
		    if(resultcount==0){
		    	//if not exists creating document
		    	BasicDBObject document = new BasicDBObject();
				document.put("MB_USER_ID", messageParam.getMailfromid());
				document.put("MB_USER_TYPE", "CU");//for time being hardcoding customertype as cu
				SENTBOX.insert(document);
		    	
		    }
		  //  operations of from user in inbox
		    BasicDBObject quer0=checkmailbox(messageParam.getSubject(),messageParam.getMailtoid().toString()); 
		    DBCursor cursor0 =INBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailfromid()),quer0);
		    	//check in ARCHIVE of FROM user	
		    DBCollection Archive =mc.getArchive();
		    BasicDBObject Archivequery=checkmailbox(messageParam.getSubject(),messageParam.getMailtoid().toString()); 
		    DBCursor Archivecursorcursor =Archive.find(new BasicDBObject("MB_USER_ID",messageParam.getMailfromid()),Archivequery); 
		    
		    //  operations of from user in sentbox  
		    BasicDBObject quer1=checkmailbox(messageParam.getSubject(),messageParam.getMailtoid().toString());
		    DBCursor cursor1 = SENTBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailfromid()),quer0);
		    
		    System.out.println(cursor0);//checking the same mail in inbox of from user
		    System.out.println(cursor1);//checking the same mail in sentbox of from user
		    
		     BasicDBObject	getidinboxfromuser=null;
			 BasicDBObject	getidsentboxfromuser=null;
			 BasicDBList inboxdetails=null;
			 BasicDBList sentboxdetails=null;
			 BasicDBList Archivedetails=null;
			 String Sentboxmsgidfromuser=null;
			 String inboxmsgidfromuser=null;
			 if(cursor1.count()!=0){
			 while( cursor1.hasNext() )
		        {
				 getidsentboxfromuser = ( BasicDBObject ) cursor1.next();
		        	System.out.println("sentboxid"+getidsentboxfromuser);
		        }
			 if(getidsentboxfromuser!=null){
				 sentboxdetails = ( BasicDBList ) getidsentboxfromuser.get( "MB_MAIL_BOX" );
				 System.out.println("sentboxid"+sentboxdetails);
				 if(sentboxdetails!=null){
				 for( Iterator< Object > it = sentboxdetails.iterator(); it.hasNext(); )
			        {
			            BasicDBObject dbo     = ( BasicDBObject ) it.next();
			            Sentboxmsgidfromuser=dbo.getString("MI_ID");
			            System.out.println("sentboxmsgid from user"+Sentboxmsgidfromuser);
			        }
			 } 
			 }
			 }
			
			 if(cursor0.count()!=0){
			 while( cursor0.hasNext() )
		        {
				 getidinboxfromuser = ( BasicDBObject ) cursor0.next();
		        	System.out.println("inboxid"+getidinboxfromuser);
		        }
			 if(getidinboxfromuser!=null){
				 inboxdetails  = ( BasicDBList ) getidinboxfromuser.get( "MB_MAIL_BOX" );
				 System.out.println("inboxid"+inboxdetails);
				 if(inboxdetails!=null){
				 for( Iterator< Object > it = inboxdetails.iterator(); it.hasNext(); )
			        {
			            BasicDBObject dbo1     = ( BasicDBObject ) it.next();
			            inboxmsgidfromuser=dbo1.getString("MI_ID");
			            System.out.println("inboxmsgid"+inboxmsgidfromuser);
			        }
			 } 
				 }
			 }
			 DBObject Archivefromuser=null;
			 String Archivemsgid=null;
			 String Communication=null;
			 if(Archivecursorcursor.count()!=0){
				 
				 while( Archivecursorcursor.hasNext() )
			        {
					 Archivefromuser = ( BasicDBObject ) Archivecursorcursor.next();
			        	System.out.println("archive"+Archivefromuser);
			        }
				 if(Archivefromuser!=null){
					 Archivedetails = ( BasicDBList ) Archivefromuser.get( "MB_MAIL_BOX" );
					 System.out.println("sentboxid"+Archivedetails);
					 if(Archivedetails!=null){
					 for( Iterator< Object > it = Archivedetails.iterator(); it.hasNext(); )
				        {
				            BasicDBObject dbo     = ( BasicDBObject ) it.next();
				            Archivemsgid=dbo.getString("MI_ID");
				            Communication=dbo.getString("MI_Communication");
				            System.out.println("sentboxmsgid from user"+Archivemsgid);
				        }
				 } 
				 }
				 }
		    
		    if(inboxdetails==null&&sentboxdetails==null){
		    	if(Archivedetails==null){
				 //generate mail as a fresh mail because the mail is not there in inbox ,sentbox and Archive of from user
		    	//mail should be in sentboxonly
		    	System.out.println("0 0");
		    	System.out.println("generate mail as a fresh mail because the mail is not there in inbox and sentbox of from user,mail should be in sentboxonly");
				    String json = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid+"',MI_Communication:'oneway', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
					DBObject push = (DBObject) JSON.parse(json);
					BasicDBObject freshmail = new BasicDBObject();
					freshmail.append("MB_USER_ID", messageParam.getMailfromid());//appending data for particular user in sentbox
					SENTBOX.update(freshmail, push);
					
					String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push1 = (DBObject) JSON.parse(json1);
					BasicDBObject searchQuery2 = new BasicDBObject();
					searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
					searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user in sentbox
					SENTBOX.update(searchQuery2, push1);
					freshmail1=true;
		    	}else if(Archivedetails!=null){
		    		// the mail is present in archive but not in sentbox and inbox of FROM user
		    		//move the mail to sentbox ,if it is replied threaded mail move to inbox also
		    		
		    		copymail(Archivefromuser, "sentbox", messageParam.getMailfromid(),copymailfrom);
		    		//now append the content to it
		    		String jsona = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Archivemsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject pusha = (DBObject) JSON.parse(jsona);
					BasicDBObject searchQuerya = new BasicDBObject();
					searchQuerya.append("MB_USER_ID",messageParam.getMailfromid());
					searchQuerya.append("MB_MAIL_BOX.MI_ID", Archivemsgid);//appending data in threadmsg for particular user in sentbox
					SENTBOX.update(searchQuerya, pusha);
					
		    		if(Communication.equalsIgnoreCase("twoway")){
		    			//now append the content to it
		    			String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Archivemsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
						DBObject push1 = (DBObject) JSON.parse(json1);
						BasicDBObject searchQuery2 = new BasicDBObject();
						searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
						searchQuery2.append("MB_MAIL_BOX.MI_ID", Archivemsgid);//appending data in threadmsg for particular user in sentbox
						INBOX.update(searchQuery2, push1);
		    		}
		    		
		    		// now delete from archive
		    		 checkanddeletemail(messageParam.getMailfromid(), Archivemsgid, Archive);
		    		
		    	}
			 }else if(inboxdetails!=null&&sentboxdetails!=null){
				 // the same mail is present in both inbox and sentbox of from user
				 // so just append the newmail as a reply in both sentbox and inbox of from user
				    System.out.println("the same mail is present in both inbox and sentbox of from user,so just append the newmail as a reply in both sentbox and inbox of from user");
				    String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+inboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push1 = (DBObject) JSON.parse(json1);
					BasicDBObject searchQuery2 = new BasicDBObject();
					searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
					searchQuery2.append("MB_MAIL_BOX.MI_ID", inboxmsgidfromuser);//appending data in threadmsg for particular user in inbox
					INBOX.update(searchQuery2, push1);
					UpdateCommunication(messageParam.getMailfromid(), INBOX, inboxmsgidfromuser);
					
					String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Sentboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push2 = (DBObject) JSON.parse(json2);
					BasicDBObject searchQuery3 = new BasicDBObject();
					searchQuery3.append("MB_USER_ID",messageParam.getMailfromid());
					searchQuery3.append("MB_MAIL_BOX.MI_ID", Sentboxmsgidfromuser);//appending data in threadmsg for particular user in sentbox
					SENTBOX.update(searchQuery3, push2);
					UpdateCommunication(messageParam.getMailfromid(), SENTBOX, Sentboxmsgidfromuser);
			 }else if(inboxdetails==null&&sentboxdetails!=null){
				 // the mail is present in only sentbox of from user
				 // so just append the new mail as a reply in sentbox of from user
				 System.out.println("the mail is present in only sentbox of from user,so just append the new mail as a reply in sentbox of from user");
				    String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Sentboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push2 = (DBObject) JSON.parse(json2);
					BasicDBObject searchQuery3 = new BasicDBObject();
					searchQuery3.append("MB_USER_ID",messageParam.getMailfromid());
					searchQuery3.append("MB_MAIL_BOX.MI_ID", Sentboxmsgidfromuser);//appending data in threadmsg for particular user in sentbox
					SENTBOX.update(searchQuery3, push2);
				 
				 
			 }else if(inboxdetails!=null&&sentboxdetails==null){
				 // the mail is present in only inbox of from user
				 // so append the newmail as a reply in inbox andcopy the whole mail in sentbox
				 System.out.println("the mail is present in only inbox of from user,so append the newmail as a reply in inbox andcopy the whole mail in sentbox");
				    String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+inboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push1 = (DBObject) JSON.parse(json1);
					BasicDBObject searchQuery2 = new BasicDBObject();
					searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
					searchQuery2.append("MB_MAIL_BOX.MI_ID", inboxmsgidfromuser);//appending data in threadmsg for particular user in inbox
					INBOX.update(searchQuery2, push1);
					UpdateCommunication(messageParam.getMailfromid(), INBOX, inboxmsgidfromuser);
					 DBObject statusQuery1 = new BasicDBObject("MI_ID", inboxmsgidfromuser); 
						
				        DBObject elemMatchQuery1 = new BasicDBObject("$elemMatch", statusQuery1);
				        
						BasicDBObject quer = new BasicDBObject();
						quer.append("MB_MAIL_BOX", elemMatchQuery1);
						try{
					    DBCursor result1 = INBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailfromid()),quer);
					    System.out.println(result1.size());
					    DBObject  obj=null;
					    if(result1.hasNext())
					     obj= result1.next();
					   System.out.println(obj.get("MB_MAIL_BOX"));
					   copymail(obj, "sentbox", messageParam.getMailfromid(),copymailfrom);
					   UpdateCommunication(messageParam.getMailfromid(), SENTBOX, Sentboxmsgidfromuser);
					} catch (MongoException e) {
					   		e.printStackTrace();
						    }
					
				 
			 }
		    	
		   
			//inserting data in inbox
			//operations of to user
			BasicDBObject inboxquery = new BasicDBObject();
			inboxquery.append("MB_USER_ID", messageParam.getMailtoid());
		    DBCursor inboxresult = INBOX.find(inboxquery);//checking the document for mailto user exists or not
		    int inboxresultcount=inboxresult.size();
		    System.out.println(inboxresult.size());
		    if(inboxresultcount==0){
		    	
		    	BasicDBObject document = new BasicDBObject();
				document.put("MB_USER_ID", messageParam.getMailtoid());
				document.put("MB_USER_TYPE", "CU");//for time being hardcoding customertype as cu
				List<BasicDBObject> MB_MAIL_INBOX = new ArrayList<BasicDBObject>();
				document.put("MB_MAIL_BOX", MB_MAIL_INBOX);
				INBOX.insert(document);
		    }
		   
		    BasicDBObject quer2=checkmailbox(messageParam.getSubject(),messageParam.getMailfromid().toString());
		    DBCursor cursor2 = SENTBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailtoid()),quer2);
		    		 
		    System.out.println("cursor2"+cursor2);
		    System.out.println("cursor2 count"+cursor2.count());
		    
		    BasicDBObject quer3=checkmailbox(messageParam.getSubject(),messageParam.getMailfromid().toString());
		    DBCursor cursor3 =  INBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailtoid()),quer2);
		        System.out.println(cursor3.size());
			    System.out.println("cursor3"+cursor3);
				System.out.println("cursor3 count"+cursor3.count());
		//check in ARCHIVE of FROM user	
			   
	      BasicDBObject ArchiveTOquery=checkmailbox(messageParam.getSubject(),messageParam.getMailfromid().toString()); 
	      DBCursor ArchivecursorTO =Archive.find(new BasicDBObject("MB_USER_ID",messageParam.getMailtoid()),ArchiveTOquery); 	
				
				 BasicDBObject	getidsentbox=null;
				 String Sentboxmsgid=null;
				 BasicDBList inbxdetails1=null;
				 BasicDBList inbxdetails=null;
				 BasicDBObject	getidinbox=null;
				 String inboxmsgid=null;
				 if(cursor2.count()!=0){
				 while( cursor2.hasNext() )
			        {
					 getidsentbox = ( BasicDBObject ) cursor2.next();
			        	System.out.println("sentboxid"+getidsentbox);
			        }
				 if(getidsentbox!=null){
					inbxdetails = ( BasicDBList ) getidsentbox.get( "MB_MAIL_BOX" );
					 System.out.println("sentboxid"+inbxdetails);
					 if(inbxdetails!=null){
					 for( Iterator< Object > it = inbxdetails.iterator(); it.hasNext(); )
				        {
				            BasicDBObject dbo     = ( BasicDBObject ) it.next();
				            Sentboxmsgid=dbo.getString("MI_ID");
				            System.out.println("sentboxmsgid"+Sentboxmsgid);
				        }
				 } 
				 }
				 }
				 
				 if(cursor3.count()!=0){
				 while( cursor3.hasNext() )
			        {
					 getidinbox = ( BasicDBObject ) cursor3.next();
			        	System.out.println("inboxid"+getidinbox);
			        }
				 if(getidinbox!=null){
				 inbxdetails1  = ( BasicDBList ) getidinbox.get( "MB_MAIL_BOX" );
					 System.out.println("inboxid"+inbxdetails1);
					 if(inbxdetails1!=null){
					 for( Iterator< Object > it = inbxdetails1.iterator(); it.hasNext(); )
				        {
				            BasicDBObject dbo1     = ( BasicDBObject ) it.next();
				            inboxmsgid=dbo1.getString("MI_ID");
				            System.out.println("inboxmsgid"+inboxmsgid);
				        }
				 } 
					 }
				 }
				 
				 DBObject Archivetouser=null;
				 String ArchiveTOmsgid=null;
				 String CommunicationTOuser=null;
				 BasicDBList ArchiveTOdetails=null;
				 if(ArchivecursorTO.count()!=0){
					 
					 while( ArchivecursorTO.hasNext() )
				        {
						 Archivetouser = ( BasicDBObject ) ArchivecursorTO.next();
				        	System.out.println("archive"+Archivetouser);
				        }
					 if(Archivetouser!=null){
						 ArchiveTOdetails = ( BasicDBList ) Archivetouser.get( "MB_MAIL_BOX" );
						 System.out.println("sentboxid"+ArchiveTOdetails);
						 if(ArchiveTOdetails!=null){
						 for( Iterator< Object > it = ArchiveTOdetails.iterator(); it.hasNext(); )
					        {
					            BasicDBObject dbo     = ( BasicDBObject ) it.next();
					            ArchiveTOmsgid=dbo.getString("MI_ID");
					            CommunicationTOuser=dbo.getString("MI_Communication");
					            System.out.println("sentboxmsgid from user"+Archivemsgid);
					        }
					 } 
					 }
					 }
			 if(inbxdetails==null&&inbxdetails1==null){
				 System.out.println("compose mail 0 0");
				 if(ArchiveTOdetails==null){
				 //generate mail as a fresh mail because the mail is not present in sentbox and inbox of the To user
				    String json = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid+"',MI_Communication:'oneway', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
					DBObject push = (DBObject) JSON.parse(json);
					BasicDBObject freshmail = new BasicDBObject();
					freshmail.append("MB_USER_ID", messageParam.getMailtoid());//appending data for particular user
					INBOX.update(freshmail, push);
					
					String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push1 = (DBObject) JSON.parse(json1);
					BasicDBObject searchQuery2 = new BasicDBObject();
					searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
					searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user
					INBOX.update(searchQuery2, push1);
					freshmail2=true;
				 }else if(ArchiveTOdetails!=null){
					// the mail is present in archive but not in sentbox and inbox of TO user
			    		//move the mail to inbox ,if it is replied threaded mail move to sentbox also
			    		copymail(Archivetouser, "inbox", messageParam.getMailtoid(),copymailfrom);
			    		//now append the content to it
			    		String jsona = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+ArchiveTOmsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
						DBObject pusha = (DBObject) JSON.parse(jsona);
						BasicDBObject searchQuerya = new BasicDBObject();
						searchQuerya.append("MB_USER_ID",messageParam.getMailtoid());
						searchQuerya.append("MB_MAIL_BOX.MI_ID", ArchiveTOmsgid);//appending data in threadmsg for particular user in sentbox
						INBOX.update(searchQuerya, pusha);
						
			    		if(CommunicationTOuser.equalsIgnoreCase("twoway")){
			    			copymail(Archivetouser, "sentbox", messageParam.getMailtoid(),copymailfrom);
			    			//now append the content to it
			    			String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
							DBObject push1 = (DBObject) JSON.parse(json1);
							BasicDBObject searchQuery2 = new BasicDBObject();
							searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
							searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user in sentbox
							SENTBOX.update(searchQuery2, push1);
			    		}
			    		
			    		 // now delete from archive
			    		 checkanddeletemail(messageParam.getMailtoid(), ArchiveTOmsgid, Archive);
				 }
			 }else if(inbxdetails!=null&&inbxdetails1!=null){
				 //generate mail as a reply mail because the mail is  present in sentbox and inbox of the To user
				 //now append the content both in sentbox and inbox of To user
				    System.out.println("compose mail 1 1");
				    System.out.println("generate mail as a reply mail because the mail is  present in sentbox and inbox of the To user,now append the content both in sentbox and inbox of To user");
				    String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+inboxmsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push1 = (DBObject) JSON.parse(json1);
					BasicDBObject searchQuery2 = new BasicDBObject();
					searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
					searchQuery2.append("MB_MAIL_BOX.MI_ID", inboxmsgid);//appending data as a reply in inbox of TOuser
					INBOX.update(searchQuery2, push1);
					UpdateCommunication(messageParam.getMailtoid(), INBOX, inboxmsgid);
					UpdateReplyDate(messageParam.getMailtoid(), INBOX, inboxmsgid);
					String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Sentboxmsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push2 = (DBObject) JSON.parse(json2);
					BasicDBObject searchQuery5 = new BasicDBObject();
					searchQuery5.append("MB_USER_ID",messageParam.getMailtoid());
					searchQuery5.append("MB_MAIL_BOX.MI_ID", Sentboxmsgid);//appending data as a reply in sent of TOuser
					SENTBOX.update(searchQuery5, push2);
					UpdateCommunication(messageParam.getMailtoid(), SENTBOX, Sentboxmsgid);
					UpdateReplyDate(messageParam.getMailtoid(), SENTBOX, Sentboxmsgid);
			 }else if(inbxdetails!=null&&inbxdetails1==null){
				//generate mail as a reply mail because the mail is  present in sentbox of To user
				 // now append the mail as replycontent to sentbox of To user
				 //copy the whole mail to inbox of To user,becuase data is not in inbox
				 System.out.println("compose mail 1 0");
				 System.out.println("generate mail as a reply mail because the mail is  present in sentbox of To user, now append the mail as replycontent to sentbox of To user,copy the whole mail to inbox of To user,becuase data is not in inbox");
				    String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Sentboxmsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push2 = (DBObject) JSON.parse(json2);
					BasicDBObject searchQuery5 = new BasicDBObject();
					searchQuery5.append("MB_USER_ID",messageParam.getMailtoid());
					searchQuery5.append("MB_MAIL_BOX.MI_ID", Sentboxmsgid);//appending data as a reply in sent of TOuser
					SENTBOX.update(searchQuery5, push2);
					UpdateCommunication(messageParam.getMailtoid(), SENTBOX, Sentboxmsgid);
					UpdateReplyDate(messageParam.getMailtoid(), SENTBOX, Sentboxmsgid);
					 DBObject statusQuery4 = new BasicDBObject("MI_ID", Sentboxmsgid); 
						
				        DBObject elemMatchQuery4 = new BasicDBObject("$elemMatch", statusQuery4);
				        
						BasicDBObject quer = new BasicDBObject();
						quer.append("MB_MAIL_BOX", elemMatchQuery4);
						try{
					    DBCursor result1 = SENTBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailtoid()),quer);
					    System.out.println(result1.size());
					    DBObject  obj=null;
					    if(result1.hasNext())
					     obj= result1.next();
					   System.out.println(obj.get("MB_MAIL_BOX"));
					   
					   copymail(obj, "inbox", messageParam.getMailtoid(),copymailfrom);
					    UpdateCommunication(messageParam.getMailtoid(), INBOX, Sentboxmsgid);
						UpdateReplyDate(messageParam.getMailtoid(), INBOX, Sentboxmsgid);
				       
					} catch (MongoException e) {
					   		e.printStackTrace();
						    }
  
			 }else if(inbxdetails==null&&inbxdetails1!=null){
				 // the mail is present in inbox and not present in sentbox of To user
				 //append the mail as reply content to inbox of Touser
				 System.out.println("compose mail 0 1");
				 System.out.println("the mail is present in inbox and not present in sentbox of To user,append the mail as reply content to inbox of Touser");
				    String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+inboxmsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push2 = (DBObject) JSON.parse(json2);
					BasicDBObject searchQuery5 = new BasicDBObject();
					searchQuery5.append("MB_USER_ID",messageParam.getMailtoid());
					searchQuery5.append("MB_MAIL_BOX.MI_ID", inboxmsgid);//appending data as a reply in inbox of TOuser
					INBOX.update(searchQuery5, push2);
					UpdateReplyDate(messageParam.getMailtoid(), INBOX, inboxmsgid);
			 }
			 
			//testings following methods
		    //getuserlist(messageParam.getMailfromid(),"CU");
			// getSettings(messageParam.getMailfromid());
			//ChangeStarRating(messageParam.getMailtoid(),msgid,"5");
			 if(freshmail1==true||freshmail2==true){
			  CheckAutoresponse(messageParam.getMailtoid(), msgid, messageParam);
			  freshmail1=false;
			  freshmail2=false;
			 }
	 } catch (MongoException e) {
		e.printStackTrace();
	    }
		return true;
	}
	

	public BasicDBObject checkmailbox(String subject,String userid){
		DBObject statusQuery2 = new BasicDBObject(); 
		statusQuery2.put("MI_SUBJECT",subject);
		statusQuery2.put("MI_FROM_ID", userid);
		DBObject statusQuery12 = new BasicDBObject(); 
		statusQuery12.put("MI_SUBJECT", subject);
		statusQuery12.put("MI_TO_ID", userid);
		BasicDBList condtionalOperator = new BasicDBList();
        condtionalOperator.add(statusQuery2);
        condtionalOperator.add(statusQuery12);
		DBObject statusQuery123 = new BasicDBObject("$or", condtionalOperator);
        DBObject elemMatchQuery2 = new BasicDBObject("$elemMatch", statusQuery123);
		BasicDBObject quer2 = new BasicDBObject();
		quer2.append("MB_MAIL_BOX", elemMatchQuery2);
		quer2.append("_id",false);
		return quer2;
	}
	
	public void copymail(DBObject obj, String appendin, long affectuserid,String from) {
		DBCollection INBOX = null;
		System.out.println(appendin);
	
		if (appendin.equalsIgnoreCase("inbox")) {
			System.out.println("Inside the Inbox Insertion");
			INBOX = mc.getInBox();
		} else if (appendin.equalsIgnoreCase("sentbox")) {
			System.out.println("Inside the Sentbox Insertion");
			INBOX = mc.getSentBox();
		} else if (appendin.equalsIgnoreCase("archive")) {
			System.out.println("Inside the archive Insertion");
			INBOX = mc.getArchive();
		} else if (appendin.equalsIgnoreCase("trash")) {
			System.out.println("Inside the trash Insertion");
			INBOX = mc.getTrashBox();
		}
		else if (appendin.equalsIgnoreCase("draft")) {
			System.out.println("Inside the trash Insertion");
			INBOX = mc.getDraftBox();
		}
		
		

		BasicDBObject query = new BasicDBObject();
		query.append("MB_USER_ID", affectuserid);
		DBCursor result = INBOX.find(query);// checking the document for
											// particular user exists or not
		int resultcount = result.size();
		System.out.println(result.size());
		if (resultcount == 0) {
			// if not exists creating document
			BasicDBObject document = new BasicDBObject();
			document.put("MB_USER_ID", affectuserid);
			document.put("MB_USER_TYPE", "CU");// for time being hardcoding
												// customertype as cu
			List<BasicDBObject> MB_SENT_MAIL_BOX = new ArrayList<BasicDBObject>();
			document.put("MB_MAIL_BOX", MB_SENT_MAIL_BOX);
			INBOX.insert(document);

		}

		BasicDBList mailinboxarray = (BasicDBList) obj.get("MB_MAIL_BOX");
		BasicDBObject dbo = null;
		BasicDBObject dbo1 = null;
		try
		{
		for (Iterator<Object> it = mailinboxarray.iterator(); it.hasNext();) {
			System.out.println("Inside the Iteration");
			dbo = (BasicDBObject) it.next();
			String MI_ID = (String) dbo.get("MI_ID");
			String MI_Communication = (String) dbo.get("MI_Communication");
			String MI_FROM_ID = (String) dbo.get("MI_FROM_ID");
			String MI_TO_ID = (String) dbo.get("MI_TO_ID");
			String MI_SUBJECT = (String) dbo.get("MI_SUBJECT");
			String MI_CONTENT = (String) dbo.get("MI_CONTENT");
			String MI_SENT_DATE_TIME = (String) dbo.get("MI_SENT_DATE_TIME");
			String MI_SENT_DATE = (String) dbo.get("MI_SENT_DATE");
			String MI_SENT_TIME = (String) dbo.get("MI_SENT_TIME");
			String MI_READ_DATE = (String) dbo.get("MI_READ_DATE");
			String MI_STAR_TYPE = (String) dbo.get("MI_STAR_TYPE");
			String MI_SENT_IP = (String) dbo.get("MI_SENT_IP");
			String MI_READ_IP = (String) dbo.get("MI_READ_IP");
			String MI_FROM  = from;
			
			String json = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'" + MI_ID + "', MI_Communication:'" + MI_Communication
					+ "',MI_FROM_ID:'" + MI_FROM_ID + "',MI_TO_ID:'" + MI_TO_ID + "',MI_SUBJECT:'" + MI_SUBJECT
					+ "',MI_CONTENT:'" + MI_CONTENT + "',MI_STAR_TYPE:'" + MI_STAR_TYPE + "',MI_SENT_IP:'" + MI_SENT_IP
					+ "',MI_READ_IP:'" + MI_READ_IP + "',MI_SENT_DATE_TIME:'" + MI_SENT_DATE_TIME + "',MI_SENT_DATE:'"
					+ MI_SENT_DATE + "',MI_FROM:'"+ MI_FROM +"',MI_SENT_TIME:'" + MI_SENT_TIME + "',MI_TRASH_TIME:'" + DateUtil.getDate()+"',MI_READ_DATE:'" + MI_READ_DATE
					+ "',MI_THEREAD_MESSAGE:[]}],$position:0}}}";
			DBObject push = (DBObject) JSON.parse(json);
			BasicDBObject freshmail = new BasicDBObject();
			freshmail.append("MB_USER_ID", affectuserid);
			INBOX.update(freshmail, push);
			System.out.println("Afftected USEr id" + affectuserid);
			BasicDBList threadmsg = (BasicDBList) dbo.get("MI_THEREAD_MESSAGE");
			for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
				dbo1 = (BasicDBObject) it1.next();
				String TMI_ID = (String) dbo1.get("MI_ID");
				String TMI_FROM_ID = (String) dbo1.get("MI_FROM_ID");
				String TMI_TO_ID = (String) dbo1.get("MI_TO_ID");
				String TMI_SUBJECT = (String) dbo1.get("MI_SUBJECT");
				String TMI_CONTENT = (String) dbo1.get("MI_CONTENT");
				String TMI_SENT_DATE_TIME = (String) dbo1.get("MI_SENT_DATE_TIME");
				String TMI_SENT_DATE = (String) dbo1.get("MI_SENT_DATE");
				String TMI_SENT_TIME = (String) dbo1.get("MI_SENT_TIME");
				String TMI_READ_DATE = (String) dbo1.get("MI_READ_DATE");
				String TMI_STAR_TYPE = (String) dbo1.get("MI_STAR_TYPE");
				String TMI_SENT_IP = (String) dbo1.get("MI_SENT_IP");
				String TMI_READ_IP = (String) dbo1.get("MI_READ_IP");
				String json3 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'" + TMI_ID + "', MI_FROM_ID:'"
						+ TMI_FROM_ID + "',MI_TO_ID:'" + TMI_TO_ID + "',MI_SUBJECT:'" + TMI_SUBJECT + "',MI_CONTENT:'"
						+ TMI_CONTENT + "',MI_STAR_TYPE:'" + TMI_STAR_TYPE + "',MI_SENT_IP:'" + TMI_SENT_IP
						+ "',MI_READ_IP:'" + TMI_READ_IP + "',MI_SENT_DATE_TIME:'" + TMI_SENT_DATE_TIME
						+ "',MI_SENT_DATE:'" + TMI_SENT_DATE + "',MI_SENT_TIME:'" + TMI_SENT_TIME + "',MI_READ_DATE:'"
						+ TMI_READ_DATE + "'}}}";
				DBObject push3 = (DBObject) JSON.parse(json3);
				BasicDBObject searchQuery6 = new BasicDBObject();
				searchQuery6.append("MB_USER_ID", affectuserid);
				searchQuery6.append("MB_MAIL_BOX.MI_ID", MI_ID);
				INBOX.update(searchQuery6, push3);
				System.out.println("Thread Iteration");
			}
		}
		}
		catch(NullPointerException npe)
		{
			
		}
	}	
	
	@Override
	public Object getInbox(Long userid,int pageno,String filter) {
		// TODO Auto-generated method stub
		//MongoConfig mc=new MongoConfig();
		DBCollection INBOX = mc.getInBox();
		return getboxes(userid, pageno, INBOX,filter);
	    
	}
	@Override
	public Object getSentMail(Long userid,int pageno,String filter) {
		// TODO Auto-generated method stub
		//MongoConfig mc=new MongoConfig();
		DBCollection SENTBOX = mc.getSentBox();
	    return getboxes(userid, pageno, SENTBOX,filter);
	}
	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Boolean ChangeReaddate(long userid,String msgid){
		    DBCollection INBOX = mc.getInBox();
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
			Date now = new Date();
			String dateString=sdf.format(now);
			System.out.println("Change READ date method() date"+dateString);
			System.out.println("Change READ date method() userid"+userid);
			System.out.println("Change READ date method() msgid"+msgid);
		   /* ObjectId _id = new ObjectId("4e71b07ff391f2b283be2f95");
		    ObjectId arrayId = new ObjectId("4e639a918dca838d4575979c");*/

		    BasicDBObject query = new BasicDBObject();
		    query.put("MB_USER_ID", userid);
		    query.put("MB_MAIL_BOX.MI_ID", msgid);

		    BasicDBObject data = new BasicDBObject();
		    data.put("MB_MAIL_BOX.$.MI_READ_DATE", dateString);

		    BasicDBObject command = new BasicDBObject();
		    command.put("$set", data);

		    INBOX.update(query, command);
		    System.out.println("Change READ date method() date updated");
		return true;
	}
	
	@Override
	public String ChangeStarRating(long userid,String msgid,String rating,String box){
		DBCollection box1 = null ;
		if(box.equalsIgnoreCase("INBOX"))
		{
			  box1 = mc.getInBox();
		}
		else if(box.equalsIgnoreCase("DRAFT"))
		{
			 box1 = mc.getDraftBox();
		}
		else if(box.equalsIgnoreCase("SENT"))
		{
			 box1 = mc.getSentBox();
		}
		else if(box.equalsIgnoreCase("ARCHIVE"))
		{
			 box1 = mc.getArchive();
		}
		else if(box.equalsIgnoreCase("TRASH"))
		{
			 box1 = mc.getTrashBox();
		}
	    //DBCollection INBOX = mc.getInBox();
		BasicDBObject query = new BasicDBObject();
	    query.put("MB_USER_ID", userid);
	    query.put("MB_MAIL_BOX.MI_ID", msgid);
	    BasicDBObject data = new BasicDBObject();
	    data.put("MB_MAIL_BOX.$.MI_STAR_TYPE", rating);
	    BasicDBObject command = new BasicDBObject();
	    command.put("$set", data);
	    box1.update(query, command);
	    System.out.println("Change starrating method() rating updated");
		return rating;
	
	}

	@Override
	public Object getTrashbox(Long userid,int pageno,String filter) {
		// TODO Auto-generated method stub
		DBCollection trashbox = mc.getTrashBox();
	    return getboxes(userid, pageno, trashbox,filter);
	}
	
	@Override
	public Boolean movetotrash(String date, String msgid, String userid, String from) {
		// TODO Auto-generated method stub
		// fetching that particular message document from mailinbox or sentbox
		List<String> msgidList = Arrays.asList(msgid.split(","));
		System.out.println("No of mails to be deleted" + msgidList.size() + "foruser" + userid);
		Iterator itr1 = msgidList.iterator();
		while (itr1.hasNext()) {
			String msgid2 = itr1.next().toString();
			System.out.println("msgids got" + msgid2 + "foruser" + userid);
			trashboxoperations(msgid2, date, userid, from);// calling that
															// method every time
		}
		return true;

	}
	
	@Override
	public Boolean undofromtrash(String date, String msgid, String userid, String from) {
		// TODO Auto-generated method stub
		// fetching that particular message document from mailinbox or sentbox
		List<String> msgidList = Arrays.asList(msgid.split(","));
		System.out.println("No of mails to be deleted" + msgidList.size() + "foruser" + userid);
		Iterator itr1 = msgidList.iterator();
		while (itr1.hasNext()) {
			String msgid2 = itr1.next().toString();
			System.out.println("msgids got" + msgid2 + "foruser" + userid);
			undofromoperations(msgid2, date, userid, from);// calling that
															// method every time
		}
		return true;

	}
	
	//undo fom trash
	public void trashboxoperations(String msgids, String date, String userid, String from) {
		long useridl = Long.parseLong(userid);
		DBCollection INBOX = null;
		DBCollection delete = null;
		String str2 = from;
		if (str2.equalsIgnoreCase("inbox")) {
			INBOX = mc.getInBox();
			delete = mc.getSentBox();
			
		} else if (str2.equalsIgnoreCase("sentbox")) {
			INBOX = mc.getSentBox();
			delete = mc.getInBox();
		}
		else if(str2.equalsIgnoreCase("archive"))
		{
			INBOX = mc.getArchive();
		}
		else if(str2.equalsIgnoreCase("draft"))
		{
			INBOX= mc.getDraftBox();
		}
		System.out.println("msgids in trashboxoperation" + msgids);
		DBCollection trashbox = mc.getTrashBox();
		DBObject statusQuery = new BasicDBObject("MI_ID", msgids);
		DBObject elemMatchQuery = new BasicDBObject("$elemMatch", statusQuery);
		BasicDBObject query = new BasicDBObject();
		query.append("MB_MAIL_BOX", elemMatchQuery);
		DBCursor result = INBOX.find(new BasicDBObject("MB_USER_ID", useridl), query);
		System.out.println(result.size());
		DBObject obj = null;
		if (result.hasNext())
			obj = result.next();
		System.out.println(obj.get("MB_MAIL_BOX"));
		copymail(obj, "trash", useridl,from);

		// deleting that particular msg from sentbox or inbox of that particular
		// user
		BasicDBObject deletequery = new BasicDBObject();
		deletequery.append("MB_USER_ID", useridl);
		BasicDBObject condition = new BasicDBObject("MI_ID", msgids);
		BasicDBObject array = new BasicDBObject("MB_MAIL_BOX", condition);
		BasicDBObject update = new BasicDBObject("$pull", array);
		INBOX.update(deletequery, update);
		System.out.println("rempoved from inbox");
		if (from.equalsIgnoreCase("inbox")) {
			// if it is from inbox by default it is deleted by above code
			// now check checkit in sentbox if the mail available delete that in
			// sentbox
			checkanddeletemail(useridl, msgids, delete);
		} else if (from.equalsIgnoreCase("sentbox")) {
			// if it is from inbox by default it is deleted by above code
			// now check checkit in sentbox if the mail available delete that in
			// sentbox
			checkanddeletemail(useridl, msgids, delete);
		}

	}

	public void undofromoperations(String msgids, String date, String userid, String from) {
		long useridl = Long.parseLong(userid);
		System.out.println("msgids in trashboxoperation" + msgids);
		DBCollection trashbox = mc.getTrashBox();
		DBObject statusQuery = new BasicDBObject("MI_ID", msgids);
		DBObject elemMatchQuery = new BasicDBObject("$elemMatch", statusQuery);
		BasicDBObject query = new BasicDBObject();
		query.append("MB_MAIL_BOX", elemMatchQuery);
		DBCursor result = trashbox.find(new BasicDBObject("MB_USER_ID", useridl), query);
		System.out.println("trash message length ans disnenmdgbjdf");
		System.out.println(result.size());
		DBObject obj = null;
		if (result.hasNext())
		{
			obj = result.next();
			String MI_FROM =null;
			String to = null;
		System.out.println(obj.get("MB_MAIL_BOX"));
		BasicDBList mailinboxarray = (BasicDBList) obj.get("MB_MAIL_BOX");
		BasicDBObject dbo = null;
		for (Iterator<Object> it = mailinboxarray.iterator(); it.hasNext();) {
			System.out.println("Inside the Iteration");
			dbo = (BasicDBObject) it.next();
			 MI_FROM = (String) dbo.get("MI_FROM");
		}
		
		if(MI_FROM.equalsIgnoreCase("inbox"))
		{
			to=MI_FROM;
		}
		else if(MI_FROM.equalsIgnoreCase("sentbox"))
		{
			to=MI_FROM;
		}
		else if(MI_FROM.equalsIgnoreCase("draft"))
		{
			to=MI_FROM;
		}
		else if(MI_FROM.equalsIgnoreCase("archive"))
		{
			to=MI_FROM;
		}
		copymail(obj, to, useridl,from);
		}

		// deleting that particular msg from trashbox of that particular
		// user
		BasicDBObject deletequery = new BasicDBObject();
		deletequery.append("MB_USER_ID", useridl);
		BasicDBObject condition = new BasicDBObject("MI_ID", msgids);
		BasicDBObject array = new BasicDBObject("MB_MAIL_BOX", condition);
		BasicDBObject update = new BasicDBObject("$pull", array);
		trashbox.update(deletequery, update);
		System.out.println("rempoved from inbox");
		

	}

	@Override
	public String savetodraft(MessageParam messageParam) {
		System.out.println("called something so no worries");
		System.out.println(messageParam.getMsgid());
		// TODO Auto-generated method stub
		DBCollection DRAFT = mc.getDraftBox();
		BasicDBObject draftquery = new BasicDBObject();
		draftquery.append("MB_USER_ID", messageParam.getMailfromid());
		DBCursor draftboxresult = DRAFT.find(draftquery);// checking the
															// document for
															// mailto user
															// exists or not
		int draftresultcount = draftboxresult.size();
		System.out.println(draftboxresult.size());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		Date now = new Date();
		String dateString = sdf.format(now).replaceAll("-", "").replaceAll(":", "");
		System.out.println("date" + dateString);
		String msgid = messageParam.getMailfromid() + "" + messageParam.getMailtoid() + dateString;
		try
		{
		if(messageParam.getSubject().equalsIgnoreCase("NO SUBJECT"))
		{
			messageParam.setSubject("null");
		}
		}
		catch(NullPointerException npe)
		{
			
		}
		if (draftresultcount == 0) {
			BasicDBObject document = new BasicDBObject();
			document.put("MB_USER_ID", messageParam.getMailfromid());
			document.put("MB_USER_TYPE", "CU");// for time being hardcoding
												// customertype as cu
			List<BasicDBObject> MB_MAIL_BOX = new ArrayList<BasicDBObject>();
			document.put("MB_MAIL_BOX", MB_MAIL_BOX);
			DRAFT.insert(document);
		}
		String draftjson = "{$push:{MB_MAIL_BOX:{MI_ID:'" + msgid + "', MI_FROM_ID:'" + messageParam.getMailtoid()
		+ "',MI_TO_ID:'" + messageParam.getMailfromid() + "',MI_SUBJECT:'" + messageParam.getSubject()
		+ "',MI_CONTENT:'" + messageParam.getMessage() + "',MI_SENT_IP:'"+ messageParam.getSentip() + "',"
		+ "MI_READ_IP:'0',MI_SENT_DATE_TIME:'" +  messageParam.getDate() + "',MI_SENT_DATE:'" + DateUtil.getDate() + "',"
		+ "MI_SENT_TIME:'" + DateUtil.getTime12()
					+ "'}}}";
		DBObject draftboxpush = (DBObject) JSON.parse(draftjson);
		BasicDBObject searchdraftQuery = new BasicDBObject();
		searchdraftQuery.append("MB_USER_ID", messageParam.getMailfromid());// appending
		DRAFT.update(searchdraftQuery, draftboxpush);

		String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'" + msgid + "', MI_FROM_ID:'"
				+ messageParam.getMailtoid() + "',MI_TO_ID:'" + messageParam.getMailfromid()
				+ "',MI_SUBJECT:'" + messageParam.getSubject() + "',MI_CONTENT:'"
				+ messageParam.getMessage() + "',MI_SENT_IP:'"+ messageParam.getSentip() + "',"
				+ "MI_READ_IP:'0',MI_SENT_DATE_TIME:'" +  messageParam.getDate() + "',MI_SENT_DATE:'" + DateUtil.getDate() + "',"
				+ "MI_SENT_TIME:'" + DateUtil.getTime12()
							+ "'}}}";
		DBObject push1 = (DBObject) JSON.parse(json1);
		BasicDBObject searchQuery2 = new BasicDBObject();
		searchQuery2.append("MB_USER_ID", messageParam.getMailfromid());
		searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);// appending
														// data in
														// threadmsg
														// for
														// particular
														// user in
														// sentbox
		DRAFT.update(searchQuery2, push1);
		return msgid;
	}

	public Object getboxes(long userid, int pageno, DBCollection box, String filter) {
		System.out.println("DBCOLLECTION BPOXES FO PATIULAR FFF");
		System.out.println(box);
		System.out.println(filter);
		
		try
		{
		//checking ttl for trash folder
		if(box.toString().equalsIgnoreCase("Trashbox"))
		{
			DBObject match11 = new BasicDBObject();
			match11.put("MB_USER_ID", userid);
			DBCursor cursor11 = box.find(match11);
			BasicDBObject account11 = null;
			while (cursor11.hasNext()) {
				account11 = (BasicDBObject) cursor11.next();
			}
			MailInbox sib11 = new MailInbox();
			if (account11 == null)
				return sib11;
			String mbusertype11 = (String) account11.get("MB_USER_TYPE");
			sib11.setMB_USER_TYPE(mbusertype11);
			BasicDBList inbxdetails11 = (BasicDBList) account11.get("MB_MAIL_BOX");
			for (Iterator<Object> it = inbxdetails11.iterator(); it.hasNext();) {
				BasicDBObject dbotrash = (BasicDBObject) it.next();
				String d1 = (String) dbotrash.get("MI_TRASH_TIME");
				String d2 = DateUtil.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = null;Date now1 = null;
				 try {
				    	now = sdf.parse(d1);
				    	now1 = sdf.parse(d2);
				    	String newDateString = sdf.format(now);
				    	String newDateString1 = sdf.format(now1);
				    } catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				long diff = now1.getTime() - now.getTime();
				long diffSeconds = diff / 1000;         
				long diffMinutes = diff / (60 * 1000);         
				long diffHours = diff / (60 * 60 * 1000);  
				long diffdays = diffHours/24;
				System.out.println("Time in days: " + diffdays + " days."); 
				if(diffdays >= 1)
				{
			
					String msgids = (String) dbotrash.get("MI_ID");
					// deleting that particular msg from sentbox or inbox of that particular
					// user
					BasicDBObject deletequery = new BasicDBObject();
					deletequery.append("MB_USER_ID", userid);
					BasicDBObject condition = new BasicDBObject("MI_ID", msgids);
					BasicDBObject array = new BasicDBObject("MB_MAIL_BOX", condition);
					BasicDBObject update = new BasicDBObject("$pull", array);
					box.update(deletequery, update);
				}
			}
		}
		
		DBObject match = new BasicDBObject();
		match.put("MB_USER_ID", userid);
		DBCursor cursor = box.find(match);
		System.out.println("getboxes cursor" + cursor);
		BasicDBObject account = null;
		while (cursor.hasNext()) {
			account = (BasicDBObject) cursor.next();
		}
		MailInbox sib = new MailInbox();
		if (account == null)
			return sib;
		String mbusertype = (String) account.get("MB_USER_TYPE");
		sib.setMB_USER_TYPE(mbusertype);
		BasicDBList inbxdetails = (BasicDBList) account.get("MB_MAIL_BOX");
		System.out.println(" inboxdetails " + inbxdetails.size());
		int maxvalue = 10;
		int pagination = maxvalue * (pageno - 1);
		int count = 0;
		if(filter.equalsIgnoreCase("desc"))
		{
			for(int r=inbxdetails.size()-1;r>=0;r--){
			//for (ListIterator<Object> itr = inbxdetails.listIterator(); itr.hasPrevious();) {
				BasicDBObject dbor = (BasicDBObject) inbxdetails.get(r);
				
					String unread = (String) dbor.get("MI_READ_DATE");
					
						MailInboxContent micontnt = new MailInboxContent();
						micontnt.makePojoFromBson(dbor);
						BasicDBList threadmsg = (BasicDBList) dbor.get("MI_THEREAD_MESSAGE");
						for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
							BasicDBObject dbo1 = (BasicDBObject) it1.next();
							micontnt.Threadmsg(dbo1);
							 System.out.println("reverse"+micontnt.getThreadmsg());
						}

						if (count >= pagination && count < (pageno * maxvalue)) {
							sib.al.add(micontnt);
						} else {
							// break;
						}
						count++;
					
		
			}
		}
		else
		{
			for (Iterator<Object> it = inbxdetails.iterator(); it.hasNext();) {
				BasicDBObject dbo = (BasicDBObject) it.next();
				if (filter.equalsIgnoreCase("UNREAD")) {
					String unread = (String) dbo.get("MI_READ_DATE");
					if (unread.equalsIgnoreCase("UNREAD")) {
						MailInboxContent micontnt = new MailInboxContent();
						micontnt.makePojoFromBson(dbo);
						BasicDBList threadmsg = (BasicDBList) dbo.get("MI_THEREAD_MESSAGE");
						for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
							BasicDBObject dbo1 = (BasicDBObject) it1.next();
							micontnt.Threadmsg(dbo1);
							// System.out.println("UNREADthreamsg"+micontnt.getThreadmsg().get(0).getMI_CONTENT());
						}

						if (count >= pagination && count < (pageno * maxvalue)) {
							sib.al.add(micontnt);
						} else {
							// break;
						}
						count++;
					}
				} else if (filter.equalsIgnoreCase("ALL")) {
					
					MailInboxContent micontnt = new MailInboxContent();
					micontnt.makePojoFromBson(dbo);
					BasicDBList threadmsg = (BasicDBList) dbo.get("MI_THEREAD_MESSAGE");
					for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
						//System.out.println(it1.next());
						BasicDBObject dbo1 = (BasicDBObject) it1.next();
						micontnt.Threadmsg(dbo1);
					}
					if (count >= pagination && count < (pageno * maxvalue)) {
						sib.al.add(micontnt);
					} else {
						// break;
					}
					count++;
				} else if (filter.equalsIgnoreCase("STAR")) {
					System.out.println("star  type called");
					String star = (String) dbo.get("MI_STAR_TYPE");
					if ((!"0".equals(star))) {
						MailInboxContent micontnt = new MailInboxContent();
						micontnt.makePojoFromBson(dbo);
						BasicDBList threadmsg = (BasicDBList) dbo.get("MI_THEREAD_MESSAGE");
						for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
							BasicDBObject dbo1 = (BasicDBObject) it1.next();
							micontnt.Threadmsg(dbo1);
							// System.out.println("UNREADthreamsg"+micontnt.getThreadmsg().get(0).getMI_CONTENT());
						}

						if (count >= pagination && count < (pageno * maxvalue)) {
							System.out.println("star type called count call");
							sib.al.add(micontnt);
						} else {
							// break;
						}
						count++;
					}
				}
				else if(filter.equalsIgnoreCase("ASC")){
		             System.out.println("star asdcending type called");
		             
		                    MailInboxContent    micontnt = new MailInboxContent();  
		                    micontnt.makePojoFromBson( dbo );
		                    BasicDBList threadmsg = ( BasicDBList ) dbo.get( "MI_THEREAD_MESSAGE" );
		                    for( Iterator< Object > it1 = threadmsg.iterator(); it1.hasNext(); )
		                   {
		                    BasicDBObject dbo1     = ( BasicDBObject ) it1.next();
		                       micontnt.Threadmsg( dbo1 );
		                       // System.out.println("UNREADthreamsg"+micontnt.getThreadmsg().get(0).getMI_CONTENT());
		                   }
		                    
		                   if(count >= pagination && count < (pageno*maxvalue))
		                {
		                    System.out.println("star type called count call");
		                   sib.al.add(micontnt);
		                }
		                else
		                  {
		                  //break;
		                  }
		                 count++;
		                   
		            } 
				else
				{

				}

			}
		}
		
		
		BasicDBObject match1 = new BasicDBObject();
		match1.put("MB_USER_ID", userid);
		DBCursor cursor1 = box.find(match1);
		System.out.println("getboxes cursor" + cursor1);

		BasicDBObject account1 = null;

		while (cursor1.hasNext()) {
			account1 = (BasicDBObject) cursor1.next();
			System.out.println(account);
		}

		if (account1 != null) {
			BasicDBList inbxdetails1 = (BasicDBList) account1.get("MB_MAIL_BOX");
			sib.setNumberofMails(count);
		}

		return sib;
		}
		catch(NullPointerException npe)
		{
			
		}
		return null;

	}

	@Override
	public Object getDraft(Long userid, int pageno,String filter) {
		// TODO Auto-generated method stub
		DBCollection Draftbox = mc.getDraftBox();
		return getboxes(userid, pageno, Draftbox, filter);
		}

	@Override
	public Boolean Discardfromdraft(String msgid, String userid) {
		// TODO Auto-generated method stub
		System.out.println("DAO--DiscardfromDraft");
		System.out.println(msgid);
		long useridl = Long.parseLong(userid);
		System.out.println(useridl);
		DBCollection Draftbox = mc.getTrashBox();
		List<String> msgidList = Arrays.asList(msgid.split(","));
		System.out.println("No of mails to be deleted" + msgidList.size() + "foruser" + useridl);
		Iterator itr1 = msgidList.iterator();
		while (itr1.hasNext()) {
			String msgid2 = itr1.next().toString();
			System.out.println("msgids got" + msgid2 + "foruser" + userid);
			BasicDBObject deletequery = new BasicDBObject();
			deletequery.append("MB_USER_ID", useridl);
			BasicDBObject condition = new BasicDBObject("MI_ID", msgid2);
			BasicDBObject array = new BasicDBObject("MB_MAIL_BOX", condition);
			BasicDBObject update = new BasicDBObject("$pull", array);
			System.out.println("system out println happyly called");
			Draftbox.update(deletequery, update);
		}
		
		return true;
	}
        public void DiscardfromdraftImpl(String msgid, String userid) {	
		System.out.println("DAO--DiscardfromDraft");
		System.out.println("discarded messages"+msgid);
		long useridl=Long.parseLong(userid);
		DBCollection Draftbox = mc.getDraftBox();
		BasicDBObject deletequery = new BasicDBObject();
		deletequery.append("MB_USER_ID", useridl);
		BasicDBObject condition = new BasicDBObject("MI_ID",msgid);
		BasicDBObject array = new BasicDBObject("MB_MAIL_BOX", condition);
		BasicDBObject update = new BasicDBObject("$pull", array);
		Draftbox.update(deletequery, update);
		
	}
	
	public Boolean Settings(Usersettings settingsParam){
		DBCollection SETTINGS = mc.getSettings();
		String  MS_USER_TYPE=settingsParam.getMS_USER_TYPE().toString();
	    String  MS_PROFILE_PIC=settingsParam.getMS_PROFILE_PIC();
	    String MS_ARCHIVE_DURATION=settingsParam.getMS_ARCHIVE_DURATION();
	    String MS_AUTO_RES=settingsParam.getMS_AUTO_RES();
	    String MS_AUTO_RES_MESS=settingsParam.getMS_AUTO_RES_MESS();
	    String MS_NOTIFICATION=settingsParam.getMS_NOTIFICATION();
	    String MS_STARS=settingsParam.getMS_STARS();
	    long useridl=Long.parseLong(settingsParam.getMB_USER_ID());
	    BasicDBObject query = new BasicDBObject();
	    query.put("MB_USER_ID", useridl);
	    DBCursor settingsresult = SETTINGS.find(query);//checking the document for particular user exists or not
	    int resultcount=settingsresult.size();
	    System.out.println("usersettings");
	    System.out.println(settingsresult.size());
	    if(resultcount==0){
	    	//if not exists creating document
	    	BasicDBObject document = new BasicDBObject();
	    	document.put("MB_USER_ID", useridl);
	    	document.put("MS_USER_TYPE", "");
	    	document.put("MS_PROFILE_PIC","");
	    	document.put("MS_ARCHIVE_DURATION","");
	    	document.put("MS_AUTO_RES", "");
	    	document.put("MS_AUTO_RES_MESS","");
	    	document.put("MS_NOTIFICATION","");
	    	document.put("MS_STARS", "");
	    	SETTINGS.insert(document);
	    	
	    }

	    BasicDBObject data = new BasicDBObject();
	    data.put("MS_USER_TYPE", MS_USER_TYPE);
	    data.put("MS_PROFILE_PIC", MS_PROFILE_PIC);
	    data.put("MS_ARCHIVE_DURATION", MS_ARCHIVE_DURATION);
	    data.put("MS_AUTO_RES", MS_AUTO_RES);
	    data.put("MS_AUTO_RES_MESS", MS_AUTO_RES_MESS);
	    data.put("MS_NOTIFICATION", MS_NOTIFICATION);
	    data.put("MS_STARS", MS_STARS);

	    BasicDBObject command = new BasicDBObject();
	    command.put("$set", data);

	    SETTINGS.update(query, command);
		
		return true;
		
	}

	@Override
	public Boolean movetoarchive(String date, String mids, String userid, String from) {
		// TODO Auto-generated method stub
		List<String> msgidList = Arrays.asList(mids.split(","));
    	System.out.println("No of mails to be archieved"+msgidList.size()+"foruser"+userid);
    	Iterator itr1 =msgidList.iterator();
        while(itr1.hasNext())
        {
        	String msgid2=itr1.next().toString();
        	System.out.println("msgids got"+msgid2+"foruser"+userid);
        
        	archiveoperations(msgid2,date,userid,from);// calling that method every time
        }
    	
		return true;
	}
	public void archiveoperations(String msgids,String date,String userid,String from){
		long useridl=Long.parseLong(userid);
		DBCollection INBOX=null;
		DBCollection delete=null;
		String str2=from;
		if(str2.equalsIgnoreCase("inbox")){
			INBOX=mc.getInBox();
			delete=mc.getSentBox();
		}else if(str2.equalsIgnoreCase("sentbox")){
			 INBOX=mc.getSentBox();
			 delete=mc.getInBox();
		}
		else if(str2.equalsIgnoreCase("draft"))
		{
			INBOX=mc.getDraftBox();
		}
		System.out.println("msgids in archiveoperation"+msgids);
		DBCollection archive=mc.getArchive();
		
        DBObject statusQuery = new BasicDBObject("MI_ID", msgids); 
		
        DBObject elemMatchQuery = new BasicDBObject("$elemMatch", statusQuery);
        
		BasicDBObject query = new BasicDBObject();
		query.append("MB_MAIL_BOX", elemMatchQuery);
	    DBCursor result = INBOX.find(new BasicDBObject("MB_USER_ID",useridl),query);
	    System.out.println(result.size());
	    DBObject  obj=null;
	    if(result.hasNext())
	     obj= result.next();
	
	     copymail(obj, "archive",useridl,copymailfrom );
		// deleting that particular msg from sentbox or inbox of that particular user
		BasicDBObject deletequery = new BasicDBObject();
		deletequery.append("MB_USER_ID", useridl);

		BasicDBObject condition = new BasicDBObject("MI_ID",msgids);
		BasicDBObject array = new BasicDBObject("MB_MAIL_BOX", condition);
		BasicDBObject update = new BasicDBObject("$pull", array);
		INBOX.update(deletequery, update);
		
		if(str2.equalsIgnoreCase("inbox")){
             //if it is from inbox by default it is deleted by above code
			// now check checkit in sentbox if the mail available delete that in sentbox
			checkanddeletemail(useridl, msgids, delete);
		}else if(str2.equalsIgnoreCase("sentbox")){
			//if it is from sentbox by default it is deleted by above code
			// now check checkit in inbox if the mail available delete that in inbox
			checkanddeletemail(useridl, msgids, delete);
		}  
	}
public void checkanddeletemail(long useridl,String msgids,DBCollection delete){
	System.out.println("deletemethod"+useridl+"formsg"+msgids+"Collection"+delete);
	BasicDBObject checkduplicaterecordquery = new BasicDBObject();
    checkduplicaterecordquery.put("MB_USER_ID", useridl);
    checkduplicaterecordquery.put("MB_MAIL_BOX.MI_ID", msgids);
    DBCursor trashresult2 = delete.find(checkduplicaterecordquery); 
    if(trashresult2.count()!=0){
    	BasicDBObject deletequery = new BasicDBObject();
		deletequery.append("MB_USER_ID", useridl);
    	BasicDBObject condition1 = new BasicDBObject("MI_ID",msgids);
		BasicDBObject array1 = new BasicDBObject("MB_MAIL_BOX", condition1);
		BasicDBObject update1 = new BasicDBObject("$pull", array1);
		delete.update(deletequery, update1);
    }
}
	@Override
	public Object getArchive(Long userid,int pageno,String filter) {
		// TODO Auto-generated method stub
		 DBCollection archive = mc.getArchive();
	    return getboxes(userid, pageno, archive,filter);
	}

	@Override
	public Object getSettings(Long userid) {
		// TODO Auto-generated method stub
		System.out.println("In get settings");
		String archiveuserid=userid.toString();
		BasicDBObject match = new BasicDBObject();
        match.put( "MB_USER_ID", userid );
        DBCollection settings = mc.getSettings();
        DBCursor cursor  = settings.find( match );
        BasicDBObject account = null;

        while( cursor.hasNext() ){
            account = ( BasicDBObject ) cursor.next();
        }
       // System.out.println( ( String ) account.get( "MB_USER_TYPE" ) + ":" );
        SettingsContent sib=new SettingsContent();
        if(account!=null){
        
        String mbusertype= ( String ) account.get( "MB_USER_TYPE" );
        sib.setMS_USER_TYPE(mbusertype);
        sib.setMS_ARCHIVE_DURATION(( String ) account.get( "MS_ARCHIVE_DURATION" ));
        sib.setMS_AUTO_RES(( String ) account.get( "MS_AUTO_RES" ));
        sib.setMS_AUTO_RES_MESS(( String ) account.get( "MS_AUTO_RES_MESS" ));
        sib.setMS_NOTIFICATION(( String ) account.get( "MS_NOTIFICATION" ));
        sib.setMS_PROFILE_PIC(( String ) account.get( "MS_PROFILE_PIC" ));
        sib.setMS_STARS(( String ) account.get( "MS_STARS" ));
        System.out.println("settings values"+sib.getMS_ARCHIVE_DURATION()+sib.getMS_AUTO_RES()+sib.getMS_AUTO_RES_MESS()+sib.getMS_NOTIFICATION()+sib.getMS_PROFILE_PIC()+sib.getMS_STARS());
        }
	    return sib;
	}
	
	@SuppressWarnings("unused")
	@Override
	public Boolean reply(String msgid,String from,MessageParam messageParam){
		long touser=messageParam.getMailtoid();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		Date now = new Date();
		String msgdate=sdf.format(now);
		System.out.println("entered to reply msg");
		    DBCollection INBOX = mc.getInBox();
		    DBCollection SENTBOX=mc.getSentBox();
		    if(from.equalsIgnoreCase("sentbox")){
				//operations to do are
		    	//1.append the content to mail in sentbox of from user 
				//2.check it in inbox of from user if it is there just append the content if not just leave 
				//3.check it inbox of TO user if the mail is present just append the content if ist is not there copy the whole mail mail to inbox of TO user
				//4.check it in sentbox of TO user if the mail is present just append the content to mail else leave
				
				String json = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push = (DBObject) JSON.parse(json);
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.append("MB_USER_ID",messageParam.getMailfromid());
				searchQuery.append("MB_MAIL_BOX.MI_ID", msgid);
				SENTBOX.update(searchQuery, push);
				System.out.println("added reply msg to sentbox of FROM user");
				
				BasicDBObject query1 = new BasicDBObject();
				query1.append("MB_USER_ID", messageParam.getMailfromid());
				query1.append("MB_MAIL_BOX.MI_ID", msgid);
			    DBCursor cursor0 = INBOX.find(query1);
			    if(cursor0.count()!=0){
			    	         // mail is presnt in inbox of FROM user
			    	           
			    	             String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					             DBObject push1= (DBObject) JSON.parse(json1);
					             BasicDBObject searchQuery1 = new BasicDBObject();
					             searchQuery1.append("MB_USER_ID",messageParam.getMailfromid());
					             searchQuery1.append("MB_MAIL_BOX.MI_ID", msgid);
					             INBOX.update(searchQuery1, push1);
					             System.out.println("added reply msg to inbox of FROM user");
					             
					             UpdateCommunication(messageParam.getMailtoid(), INBOX, msgid);
					             UpdateCommunication(messageParam.getMailfromid(), INBOX, msgid);
					             
			    }
			    
				// checking in inbox of TO user
			    BasicDBObject query2 = new BasicDBObject();
				query2.append("MB_USER_ID", touser);
				query2.append("MB_MAIL_BOX.MI_ID", msgid);
			    DBCursor cursor1 = INBOX.find(query2);
			    if(cursor1.count()!=0){
			    	         // mail is presnt in inbox of TO user
			    	         
			    	           String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					           DBObject push1= (DBObject) JSON.parse(json1);
					           BasicDBObject searchQuery1 = new BasicDBObject();
					           searchQuery1.append("MB_USER_ID",messageParam.getMailtoid());
					           searchQuery1.append("MB_MAIL_BOX.MI_ID", msgid);
					           INBOX.update(searchQuery1, push1);
					           System.out.println("added reply msg to inbox of TO user");
					           UpdateReplyDate(messageParam.getMailtoid(), INBOX, msgid);
					           
			        }else if(cursor1.count()==0){
			    	           // mail is not present in inbox of TO user
			        	     // check it archive if it is there just copy to inbox and append ,else generate as a fresh mail in inbox
			    	         // delete in archive of TO user 
			        	
				                	try{
				                       replyTOuserinboxupdate(INBOX, messageParam.getMailtoid(), messageParam, msgid);
				                       UpdateReplyDate(messageParam.getMailtoid(), INBOX, msgid);
				                             } catch (MongoException e) {
				   		                           e.printStackTrace();
					                           }
		                             }
			    	                 // checking in sent box of TO user
			    
			                         BasicDBObject query6 = new BasicDBObject();
				                     query6.append("MB_USER_ID", touser);
				                     query6.append("MB_MAIL_BOX.MI_ID", msgid);
			                         DBCursor cursor3 = SENTBOX.find(query6);
			                         if(cursor3.count()!=0){
			    	
			                        	 String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
							             DBObject push1= (DBObject) JSON.parse(json1);
							             BasicDBObject searchQuery1 = new BasicDBObject();
							             searchQuery1.append("MB_USER_ID",messageParam.getMailtoid());
							             searchQuery1.append("MB_MAIL_BOX.MI_ID", msgid);
							             SENTBOX.update(searchQuery1, push1);
							             
							             UpdateCommunication(messageParam.getMailtoid(), SENTBOX, msgid);
							             UpdateCommunication(messageParam.getMailfromid(), SENTBOX, msgid);
							             UpdateReplyDate(messageParam.getMailtoid(), INBOX, msgid);
							             System.out.println("added reply msg to sentbox of TO user");
			                          }
			    	
			    	
			                  }
			    
		if(from.equalsIgnoreCase("inbox")){
			// mail comes from inbox as a reply 
			//1.append the content as a reply in inbox of FROM user
			//2.check the mail in sentbox of FROM user ,if mail is present append the content,if not present copy the whole mail from inbox to sentbox of FROM user
			//3.check the mail in inbox of TO user, if the mail is present append the content,if not prsent copy the whole mail from inbox of FROM user to inbox of TO user
			//4.check the sentbox of TO user,if the mail is present just append the content,else leave as it is
			 String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'192.180.200',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
             DBObject push1= (DBObject) JSON.parse(json1);
             BasicDBObject searchQuery1 = new BasicDBObject();
             searchQuery1.append("MB_USER_ID",messageParam.getMailfromid());
             searchQuery1.append("MB_MAIL_BOX.MI_ID", msgid);
             INBOX.update(searchQuery1, push1);
             System.out.println("added reply msg to inbox of FROM user");
             UpdateCommunication(messageParam.getMailfromid(), INBOX, msgid);
             //check the mail in sentbox of FROM user
             
             BasicDBObject query6 = new BasicDBObject();
             query6.append("MB_USER_ID", messageParam.getMailfromid());
             query6.append("MB_MAIL_BOX.MI_ID", msgid);
             DBCursor cursor0 = SENTBOX.find(query6);
             if(cursor0.count()!=0){
            	 // the mail is present in sentbox of FROM user
            	 String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'192.180.200',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
                 DBObject push2= (DBObject) JSON.parse(json2);
                 BasicDBObject searchQuery2 = new BasicDBObject();
                 searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
                 searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);
                 SENTBOX.update(searchQuery2, push2);
                 System.out.println("added reply msg to sentbox of FROM user");
                 UpdateCommunication(messageParam.getMailfromid(), SENTBOX, msgid);
             }else if(cursor0.count()==0){
            	 // the mail is not present in sentbox of FROM user
            	 //copy the whole mail to sentbox of FROM user
            	 DBObject statusQuery1 = new BasicDBObject("MI_ID", msgid); 
					
                 DBObject elemMatchQuery1 = new BasicDBObject("$elemMatch", statusQuery1);
  
	               BasicDBObject quer = new BasicDBObject();
	               quer.append("MB_MAIL_BOX", elemMatchQuery1);
              	try{
              		System.out.println("Sentbox not present mails");
                      DBCursor result1 = INBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailfromid()),quer);
                      System.out.println(result1.size());
                      DBObject  obj=null;
                      if(result1.hasNext())
                        obj= result1.next();
                        System.out.println(obj.get("MB_MAIL_BOX"));
                        copymail(obj, "sentbox", messageParam.getMailfromid(),copymailfrom);
                        UpdateCommunication(messageParam.getMailfromid(), SENTBOX, msgid);
                           } catch (MongoException e) {
 		                           e.printStackTrace();
	                         }	 
             }
			
             // check the inbox of TO user
             BasicDBObject query7 = new BasicDBObject();
             query7.append("MB_USER_ID", touser);
             query7.append("MB_MAIL_BOX.MI_ID", msgid);
             DBCursor cursor1 = INBOX.find(query7);
             if(cursor1.count()!=0){
            	 
            	// the mail is present in inbox of TO user
            	 String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'192.180.200',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
                 DBObject push2= (DBObject) JSON.parse(json2);
                 BasicDBObject searchQuery2 = new BasicDBObject();
                 searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
                 searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);
                 INBOX.update(searchQuery2, push2);
                 System.out.println("added reply msg to inbox of TO user");
                 UpdateCommunication(messageParam.getMailtoid(), INBOX, msgid);
                 UpdateReplyDate(messageParam.getMailtoid(), INBOX, msgid);
             }else if(cursor1.count()==0){
            	 // the mail is not present in inbox of TO user
            	 //copy the whole mail to inbox of TO user 
              	try{
              		 replyTOuserinboxupdate(INBOX, messageParam.getMailtoid(), messageParam, msgid);
              		 UpdateCommunication(messageParam.getMailtoid(), INBOX, msgid);
              		UpdateReplyDate(messageParam.getMailtoid(), INBOX, msgid);
                           } catch (MongoException e) {
 		                           e.printStackTrace();
	                         }	 
             }
			
             // check the sentbox of TO user
             
            BasicDBObject query9 = new BasicDBObject();
             query9.append("MB_USER_ID", touser);
             query9.append("MB_MAIL_BOX.MI_ID", msgid);
             DBCursor cursor3 = SENTBOX.find(query9);
             if(cursor3.count()!=0){
             String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'192.180.200',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
             DBObject push2= (DBObject) JSON.parse(json2);
             BasicDBObject searchQuery2 = new BasicDBObject();
             searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
             searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);
             SENTBOX.update(searchQuery2, push2);
             UpdateCommunication(messageParam.getMailtoid(), SENTBOX, msgid);
             UpdateReplyDate(messageParam.getMailtoid(), INBOX, msgid);
             System.out.println("added reply msg to sentbox of TO user");
             }	
		}
			return true;	
		}
	public void UpdateCommunication(long affetceduser,DBCollection INBOX,String msgid){
		String date = DateUtil.getDate();
		String time = DateUtil.getTime12();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		Date now = new Date();
		String msgdate=sdf.format(now);
		BasicDBObject query = new BasicDBObject();
 	    query.put("MB_USER_ID",affetceduser);
 	    query.put("MB_MAIL_BOX.MI_ID", msgid);
 	    BasicDBObject data = new BasicDBObject();
 	    data.put("MB_MAIL_BOX.$.MI_Communication", "twoway");
 	    data.append("MB_MAIL_BOX.$.MI_SENT_DATE_TIME", msgdate);
 	    data.append("MB_MAIL_BOX.$.MI_SENT_TIME", time);
 	   data.append("MB_MAIL_BOX.$.MI_SENT_DATE", date);
	    
 	
 	    BasicDBObject command = new BasicDBObject();
 	    command.put("$set", data);
 	
 	    INBOX.update(query, command);
	}	
	public void UpdateReplyDate(long affetceduser,DBCollection INBOX,String msgid){
		BasicDBObject query = new BasicDBObject();
 	    query.put("MB_USER_ID",affetceduser);
 	    query.put("MB_MAIL_BOX.MI_ID", msgid);
 	    BasicDBObject data = new BasicDBObject();
 	    data.put("MB_MAIL_BOX.$.MI_READ_DATE", "UNREAD");
 	    BasicDBObject command = new BasicDBObject();
 	    command.put("$set", data);
 	
 	    INBOX.update(query, command);
	}	
	public void replyTOuserinboxupdate(DBCollection INBOX,long affecteduser,MessageParam messageParam,String msgid){
		DBCollection Archive =mc.getArchive();
		DBObject statusQuery1 = new BasicDBObject("MI_ID", msgid); 	
        DBObject elemMatchQuery1 = new BasicDBObject("$elemMatch", statusQuery1);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		Date now = new Date();
		String msgdate=sdf.format(now);
          BasicDBObject quer = new BasicDBObject();
          quer.append("MB_MAIL_BOX", elemMatchQuery1);
		DBCursor result1 = Archive.find(new BasicDBObject("MB_USER_ID",affecteduser),quer);
        System.out.println(result1.size());
        DBObject  obj=null;
       BasicDBList inbxdetails=null;
        if(result1.hasNext())
          obj= result1.next();
         
          if(obj!=null){
				inbxdetails = ( BasicDBList ) obj.get( "MB_MAIL_BOX" );
				 System.out.println(""+inbxdetails);
          }
				 if(inbxdetails!=null)
                    {
					 
					 for( Iterator< Object > it = inbxdetails.iterator(); it.hasNext(); )
				        {
				            BasicDBObject dbo1     = ( BasicDBObject ) it.next();
				            String  Communication=dbo1.getString("MI_Communication");
				                   if(Communication.equalsIgnoreCase("twoway"));{
				                	   copymail(obj, "sentbox", messageParam.getMailtoid(),copymailfrom);
				                      }
				            System.out.println("inboxmsgid"+Communication);
				        }
        	       copymail(obj, "inbox", messageParam.getMailtoid(),copymailfrom);
        	  
        	   String json1i = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
	           DBObject pushi1= (DBObject) JSON.parse(json1i);
	           BasicDBObject searchQueryi1 = new BasicDBObject();
	           searchQueryi1.append("MB_USER_ID",messageParam.getMailtoid());
	           searchQueryi1.append("MB_MAIL_BOX.MI_ID", msgid);
	           INBOX.update(searchQueryi1, pushi1);
	           System.out.println("added reply msg to inbox of TO user");
	           
        	  checkanddeletemail(messageParam.getMailtoid(), msgid, Archive);
        	  
          }else{
        	  // so the mail is not present in inbox,and archive if the mail is present in sentbox copy that to inbox,else generate as fresh mail
        	  DBCollection SENTBOX =mc.getSentBox();
        	  if(true){
        		  //check in sentbox if present ,copy that to inbox
        		  
        		  BasicDBObject query6 = new BasicDBObject();
                  query6.append("MB_USER_ID", messageParam.getMailtoid());
                  query6.append("MB_MAIL_BOX.MI_ID", msgid);
                  DBCursor cursor0 = SENTBOX.find(query6);
                  DBObject  obj1=null;
                  if(cursor0.hasNext())
                      obj1= cursor0.next();
                     
                  if(cursor0.count()!=0){
                 	 // the mail is present in sentbox of FROM user
                	  // copymail
                	  System.out.println("mail not present in box,and archive,...coping to inbox from sentbox");
           	          copymail(obj1, "inbox", messageParam.getMailtoid(),copymailfrom);

                 	 String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'192.180.200',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
                      DBObject push2= (DBObject) JSON.parse(json2);
                      BasicDBObject searchQuery2 = new BasicDBObject();
                      searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
                      searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);
                      INBOX.update(searchQuery2, push2);
                      System.out.println("added reply msg to inbox of TO user");
                      UpdateCommunication(messageParam.getMailtoid(), INBOX, msgid);
        		  
                  } 
        		  
        		  
        	  }else{

          		//generate as a fresh mail
          	  BasicDBObject inboxquery = new BasicDBObject();
    		  	inboxquery.append("MB_USER_ID", messageParam.getMailtoid());
    		    DBCursor inboxresult = INBOX.find(inboxquery);//checking the document for mailto user exists or not
    		    int inboxresultcount=inboxresult.size();
    		    System.out.println(inboxresult.size());
    		    if(inboxresultcount==0){
    		    	
    		    	BasicDBObject document = new BasicDBObject();
    				document.put("MB_USER_ID", messageParam.getMailtoid());
    				document.put("MB_USER_TYPE", "CU");//for time being hardcoding customertype as cu
    				List<BasicDBObject> MB_MAIL_INBOX = new ArrayList<BasicDBObject>();
    				document.put("MB_MAIL_BOX", MB_MAIL_INBOX);
    				INBOX.insert(document);
    		    }
    		  String jsoni = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
  		   	DBObject pushi = (DBObject) JSON.parse(jsoni);
  			BasicDBObject freshmail = new BasicDBObject();
  			freshmail.append("MB_USER_ID", messageParam.getMailtoid());//appending data for particular user
  			INBOX.update(freshmail, pushi);
  			
  			String jsoni2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
  			DBObject pushi3 = (DBObject) JSON.parse(jsoni2);
  			BasicDBObject searchQuery2 = new BasicDBObject();
  			searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
  			searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user
  			INBOX.update(searchQuery2, pushi3);
          	  
        	  }
          }
		
	}
	
	public void Autoresponse(long useridl,String msgid,MessageParam messageParam,String Content){
		System.out.println("entered to Autoreply msg");
	    DBCollection INBOX = mc.getInBox();
	    DBCollection SENTBOX = mc.getSentBox();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
		Date now = new Date();
		String msgdate=sdf.format(now);
	   // appending autoresponse content as a reply in INBOX and SENTBOX of FROM user
	    BasicDBObject inboxquery = new BasicDBObject();
		inboxquery.append("MB_USER_ID", messageParam.getMailfromid());
	    DBCursor inboxresult = INBOX.find(inboxquery);//checking the document for from user exists or not
	    int inboxresultcount=inboxresult.size();
	    System.out.println(inboxresult.size());
	    if(inboxresultcount==0){
	    	BasicDBObject document = new BasicDBObject();
			document.put("MB_USER_ID", messageParam.getMailfromid());
			document.put("MB_USER_TYPE", "CU");//for time being hardcoding customertype as cu
			INBOX.insert(document);
	    }
	    String json = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
		DBObject push = (DBObject) JSON.parse(json);
		BasicDBObject freshmail = new BasicDBObject();
		freshmail.append("MB_USER_ID", messageParam.getMailfromid());//appending data for particular user in inbox
		INBOX.update(freshmail, push);
		
		String jsoni = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
		DBObject pushi = (DBObject) JSON.parse(jsoni);
		BasicDBObject searchQueryi = new BasicDBObject();
		searchQueryi.append("MB_USER_ID",messageParam.getMailfromid());
		searchQueryi.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user in inbox
		INBOX.update(searchQueryi, pushi);
		
		String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailtoid()+"',MI_TO_ID:'"+messageParam.getMailfromid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+Content+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
		DBObject push1 = (DBObject) JSON.parse(json1);
		BasicDBObject searchQuery2 = new BasicDBObject();
		searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
		searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user in inbox
		INBOX.update(searchQuery2, push1);
		
		String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailtoid()+"',MI_TO_ID:'"+messageParam.getMailfromid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+Content+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
		DBObject push2 = (DBObject) JSON.parse(json2);
		BasicDBObject searchQuery3 = new BasicDBObject();
		searchQuery3.append("MB_USER_ID",messageParam.getMailfromid());
		searchQuery3.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user in sentbox
		SENTBOX.update(searchQuery2, push2);
		
		
		 // appending autoresponse content as a reply in INBOX and SENTBOX of TO user
		String json3 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailtoid()+"',MI_TO_ID:'"+messageParam.getMailfromid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+Content+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
		DBObject push3 = (DBObject) JSON.parse(json3);
		BasicDBObject searchQuery4 = new BasicDBObject();
		searchQuery4.append("MB_USER_ID",messageParam.getMailtoid());
		searchQuery4.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user in inbox
		INBOX.update(searchQuery4, push3);
		
		BasicDBObject sentboxquery = new BasicDBObject();
		sentboxquery.append("MB_USER_ID", messageParam.getMailtoid());
	    DBCursor sentboxresult = SENTBOX.find(sentboxquery);//checking the document for to user exists or not
	    int sentboxresultcount=sentboxresult.size();
	    System.out.println(sentboxresult.size());
	    if(sentboxresultcount==0){
	    	BasicDBObject document = new BasicDBObject();
			document.put("MB_USER_ID", messageParam.getMailtoid());
			document.put("MB_USER_TYPE", "CU");//for time being hardcoding customertype as cu
			SENTBOX.insert(document);
	    }
	    String json4 = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
		DBObject push4 = (DBObject) JSON.parse(json4);
		BasicDBObject freshmail4 = new BasicDBObject();
		freshmail4.append("MB_USER_ID", messageParam.getMailtoid());//appending data for particular user in sentbox
		SENTBOX.update(freshmail4, push4);
		
		String jsons = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
		DBObject pushs = (DBObject) JSON.parse(jsons);
		BasicDBObject searchQuerys = new BasicDBObject();
		searchQuerys.append("MB_USER_ID",messageParam.getMailtoid());
		searchQuerys.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user in sentbox
		SENTBOX.update(searchQuerys, pushs);
		
		String json5 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailtoid()+"',MI_TO_ID:'"+messageParam.getMailfromid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+Content+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
		DBObject push5 = (DBObject) JSON.parse(json5);
		BasicDBObject searchQuery5 = new BasicDBObject();
		searchQuery5.append("MB_USER_ID",messageParam.getMailtoid());
		searchQuery5.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user in inbox
		SENTBOX.update(searchQuery5, push5);
		System.out.println("added to threaded msg");
	}
	public void CheckAutoresponse(long useridl,String msgid,MessageParam messageParam){
		System.out.println("entered to Checkautoresponse");
		SettingsContent sib= (SettingsContent) getSettings(useridl);
	    String autores=sib.getMS_AUTO_RES();
	    String Content=sib.getMS_AUTO_RES_MESS();
	    System.out.println("Autoresponse"+autores);
	    if(null!=autores){
	    if(autores.equalsIgnoreCase("Enabled")){
	    	Autoresponse(messageParam.getMailfromid(),msgid,messageParam,Content);
	    }
	    }
		System.out.println("finished to Checkautoresponse");
	}

	public Boolean SaveTips(AdminTips admin_tips){
			DBCollection TIPS = mc.getTips();
			BasicDBObject document = new BasicDBObject();
	    	document.put("Tip_name", admin_tips.getTip_Name());
	    	document.put("Tip_description", admin_tips.getTip_Description());
	    	System.out.println("called dao function3");
	    	TIPS.insert(document);
	    	return true;
		
	}

	@Override
	public Object GetTips() {
		DBCollection TIPS = mc.getTips();
		DBCursor cursor = TIPS.find();
		AdminTips sib=new AdminTips();
		while (cursor.hasNext()) {
		    BasicDBObject obj = (BasicDBObject) cursor.next();
		   AdminTips sib1=new AdminTips();
		    sib1.setTip_Name((String) obj.get("Tip_name"));
		    sib1.setTip_Description((String) obj.get("Tip_description"));
		    sib.al.add(sib1);
		}
		return sib;
	}

	@Override
	public Object GetTipsbyid(String tip_name) {
		
		DBCollection TIPS = mc.getTips();
		DBObject match = new BasicDBObject();
        match.put("Tip_name", tip_name);
		DBCursor cursor  = TIPS.find(match);
        System.out.println("getboxes cursor"+cursor);
        AdminTips sib=new AdminTips();
        BasicDBObject account = null;
        while( cursor.hasNext() ){
            account = ( BasicDBObject ) cursor.next();
        }
        if(account!=null){
        	String mbusertype= ( String ) account.get( "Tip_name" );
	        sib.setTip_Name(mbusertype);
	        sib.setTip_Description(( String ) account.get( "Tip_description" ));
        }
        else
        {
        	System.out.println("else");
        }
        return sib;
	}
	@Override
	public Boolean updatetips(AdminTips admin_tips) {
		DBCollection TIPS = mc.getTips();
		 BasicDBObject query = new BasicDBObject();
		    query.put("Tip_name", admin_tips.getTip_Name());
		    DBCursor settingsresult = TIPS.find(query);//checking the document for particular user exists or not
		    int resultcount=settingsresult.size();
		    System.out.println("usersettings");
		    System.out.println(settingsresult.size());
		BasicDBObject document = new BasicDBObject();
    	document.put("Tip_name", admin_tips.getTip_Name());
    	document.put("Tip_description", admin_tips.getTip_Description());
    	System.out.println("called dao function3");
    	BasicDBObject command = new BasicDBObject();
	    command.put("$set", document);
	    TIPS.update(query, command);
    	return true;
	}

	@Override
	public Boolean deletetips(String tip_name) {
		System.out.println("delete called");
		DBCollection TIPS = mc.getTips();
		BasicDBObject document = new BasicDBObject();  
		 document.put("Tip_name", tip_name);  
		 TIPS.remove(document);  
		/*BasicDBObject deletequery = new BasicDBObject();
		deletequery.append("Tip_name", tip_name);
		BasicDBObject update1 = new BasicDBObject("$pull", deletequery);
		TIPS.update(deletequery,update1);*/
		return true;
	}

	@Override
	public Collection<String>  getrooms(long userid) {
		
		// TODO Auto-generated method stub
		
		DBCollection Activerooms = mc.getChatmessages();
		DBCursor cursorroms= Activerooms.find();
		ArrayList<String> room=new ArrayList<String>();
		Collection<String> rooms=new ArrayList() ;
		System.out.println(cursorroms.size());
		for(int i=0;i<cursorroms.size();i++){
		 if(cursorroms.hasNext()){
			 System.out.println("get rooms function called so we have to check wither the data is pesent r not");
			 
		 BasicDBObject test=(BasicDBObject) cursorroms.next();
		 String teststr=test.get("ROOM_NAME").toString();
		 if(teststr.split("-")[0].equalsIgnoreCase(String.valueOf(userid))||teststr.split("-")[1].equalsIgnoreCase(String.valueOf(userid))){
			 String testuser=test.get("TO_USER").toString();
			 String teststr1=teststr+","+testuser;
			room.add(teststr1);
		}
		 System.out.println("rooms available"+test.get("ROOM_NAME").toString());
		 }
		}
		return room;
	}

	@Override
	public Boolean createchatrooms(String roomname,String from_user,String to_user) {
		DBCollection CHATBOX = mc.getChatmessages();
		BasicDBObject query = new BasicDBObject();
			query.append("ROOM_NAME",roomname);
			query.append("FROM_USER",from_user);
			query.append("TO_USER",to_user);
			CHATBOX.insert(query);
			
			//creating array to store data
			String json = "{$push:{CHAT_MESSAGES:{$each:[]}}}";
			DBObject push = (DBObject) JSON.parse(json);
			BasicDBObject freshmail = new BasicDBObject();
			freshmail.append("ROOM_NAME", roomname);
			/*BasicDBObject searchQuery5 = new BasicDBObject();
			searchQuery5.append("MB_USER_ID",messageParam.getMailtoid());
			searchQuery5.append("MB_MAIL_BOX.MI_ID", msgid);*/
			CHATBOX.update(freshmail, push);
		return true;
	}

	/*@Override
	public Boolean setrooms(Collection<String> rooms) {
		// TODO Auto-generated method stub
		System.out.println("QURYDAO setrooms");
		DBCollection Activerooms = mc.getActiverooms();
		BasicDBObject document = new BasicDBObject();  
		 document.put("Activerooms", rooms.iterator().hasNext());  
		 Activerooms.insert(document);
		return true;
	}

	@Override
	public Boolean Storechatmsgs(String msgs,String chatroom,long user) {
		// TODO Auto-generated method stub
		DBCollection chatmsgs = mc.getChatmessages();
		BasicDBObject document = new BasicDBObject();
		document.put("user", user);
		DBCursor cursormsgs=chatmsgs.find(document);
		if(cursormsgs.count()==0){
			BasicDBObject document1 = new BasicDBObject();
			document1.put("user", user);
			 chatmsgs.insert(document1);
			    String json = "{$push:{CHAT:{$each:[{chatroom:'"+chatroom+"',Messages:'"+msgs+"'}]}}}";
				DBObject push = (DBObject) JSON.parse(json);
				BasicDBObject freshmail = new BasicDBObject();
				freshmail.append("user",user);//appending data for particular user
				chatmsgs.update(freshmail, push);
		}else{
			    String json = "{$push:{CHAT:{$each:[{chatroom:'"+chatroom+"',Messages:'"+msgs+"'}]}}}";
				DBObject push = (DBObject) JSON.parse(json);
				BasicDBObject freshmail = new BasicDBObject();
				freshmail.append("user",user);//appending data for particular user
				chatmsgs.update(freshmail, push);
		}
		
		return true;
	}

	@Override
	public Boolean Readstatus(String status,long loginid) {
		// TODO Auto-generated method stub
		DBCollection readstatus = mc.getReadStatus();
		BasicDBObject document = new BasicDBObject();  
		 document.put("user", loginid); 
		 DBCursor obj= readstatus.find(document);
		 if(obj.count()==0){
			 BasicDBObject document1 = new BasicDBObject();  
			 document1.put("user", loginid);
			 document1.put("status", status);
		 readstatus.insert(document1);
		 }else{
			 BasicDBObject query = new BasicDBObject();  
			 query.put("user", loginid);
			 BasicDBObject updatestatus = new BasicDBObject();  
			 updatestatus.put("status",status);
			 BasicDBObject command = new BasicDBObject();
			    command.put("$set", updatestatus);
			    readstatus.update(query, command);
		 }
		return true;
	}
	@Override
	public String getReadstatus(String status) {
		// TODO Auto-generated method stub
		String userstatus="";
		long id=Long.parseLong(status);
		DBCollection readstatus = mc.getReadStatus();
		BasicDBObject document = new BasicDBObject(); 
		
		 document.put("user", id); 
		 
		DBCursor obj= readstatus.find(document);
		BasicDBObject account = null;
        while( obj.hasNext() ){
            account = ( BasicDBObject ) obj.next();
        }
        if(account!=null){
        	userstatus= ( String ) account.get( "status" );
        }
		return userstatus;
	}

	@Override
	public Boolean loginregister(Long loginCredentialid) {
		// TODO Auto-generated method stub
		
		DBCollection loginregister = mc.getLoggedinusers();
		String json1 = "{$push:{Users.$.Onlineusers:{users:'"+loginCredentialid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getMessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";

		String json = "{$push:{Users:{$each:[{'"+loginCredentialid+"'}]}}}";
		DBObject push = (DBObject) JSON.parse(json);
		BasicDBObject freshmail = new BasicDBObject();
		loginregister.update(freshmail, push);
		
		
		BasicDBObject document = new BasicDBObject(); 
		
		 document.put("user", loginCredentialid); 
		 DBCursor obj= loginregister.find(document);
		 getusers();
			if(obj.count()==0){
				BasicDBObject document1 = new BasicDBObject(); 
				 document1.put("user", loginCredentialid);
				 loginregister.insert(document1);
			}
		return true;
	}

	@Override
	public Collection<String> getusers() {
		// TODO Auto-generated method stub
		Collection<String> rooms1=new ArrayList() ;
		rooms1.add("");
		DBCollection loginregister = mc.getLoggedinusers();
		BasicDBObject document = new BasicDBObject(); 
		 DBCursor obj= loginregister.find(document);
		 BasicDBObject account = null;
		 while( obj.hasNext() ){
	            account = ( BasicDBObject ) obj.next();
	        }
	        if(account!=null){
	        String	userstatus= ( String ) account.get( "user" );
	        System.out.println("users"+userstatus);
	        rooms1.add(userstatus);
	        }
		return null;
	}
*/


@Override
public Object getsearchmail(Long loginCredentialid, String search, String filter, String box,ArrayList rec_id) {
	int pageno = 1;
	ArrayList final_array = new ArrayList<Long>();
	DBCollection box1 = null;
	if (box.equalsIgnoreCase("INBOX")) {
		box1 = mc.getInBox();
	} else if (box.equalsIgnoreCase("DRAFT")) {
		box1 = mc.getDraftBox();
	} else if (box.equalsIgnoreCase("SENT")) {
		box1 = mc.getSentBox();
	} else if (box.equalsIgnoreCase("ARCHIVE")) {
		box1 = mc.getArchive();
	} else if (box.equalsIgnoreCase("TRASH")) {
		box1 = mc.getTrashBox();
	}
	String sub;
	String context;
	DBObject match = new BasicDBObject();
	match.put("MB_USER_ID", loginCredentialid);
	DBCursor cursor = box1.find(match);
	
	BasicDBObject account = null;
	while (cursor.hasNext()) {
		System.out.println("inside iteration");
		account = (BasicDBObject) cursor.next();
	}
	MailInbox sib = new MailInbox();
	if (account == null)
		return sib;
	String mbusertype = (String) account.get("MB_USER_TYPE");
	sib.setMB_USER_TYPE(mbusertype);
	BasicDBList inbxdetails = (BasicDBList) account.get("MB_MAIL_BOX");
	int maxvalue = 10;
	int pagination = maxvalue * (pageno - 1);
	int count = 0;
	for (Iterator<Object> it = inbxdetails.iterator(); it.hasNext();) {
		BasicDBObject dbo = (BasicDBObject) it.next();
		 sub = (String) dbo.get("MI_SUBJECT");
		 context = (String) dbo.get("MI_CONTENT");
		search = search.toLowerCase();
		context= context.toLowerCase();
		sub = sub.toLowerCase();
			if(box.equalsIgnoreCase("INBOX"))
			{
				String recpitent_id = (String) dbo.get("MI_FROM_ID");
				if(rec_id.size() == 0)
				{
						if (sub.contains(search) || context.contains(search)) {
							MailInboxContent micontnt = new MailInboxContent();
							micontnt.makePojoFromBson(dbo);
							BasicDBList threadmsg = (BasicDBList) dbo.get("MI_THEREAD_MESSAGE");
							for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
								BasicDBObject dbo1 = (BasicDBObject) it1.next();
								micontnt.Threadmsg(dbo1);
							}
							if (count >= pagination && count < (pageno * maxvalue)) {
								sib.al.add(micontnt);
							} 
							count++;
						}
						else
						{

						}
				}
				else
				{
					Long keyValue;
					//ArrayList mapIterator = rec_id.
					innerloop:
			        for (int i=0;i<rec_id.size();i++) 
			        {

			        	  keyValue=  (Long) rec_id.get(i);
			           // Long keyValue = (Long) mapEntry.getValue();

			           long final_rec = Long.parseLong(recpitent_id);
			                if (final_rec == keyValue || sub.contains(search) || context.contains(search)) {
									MailInboxContent micontnt = new MailInboxContent();
									micontnt.makePojoFromBson(dbo);
									BasicDBList threadmsg = (BasicDBList) dbo.get("MI_THEREAD_MESSAGE");
									for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
										BasicDBObject dbo1 = (BasicDBObject) it1.next();
										micontnt.Threadmsg(dbo1);
									}
									if (count >= pagination && count < (pageno * maxvalue)) {
										sib.al.add(micontnt);
									} 
									count++;
			                		break innerloop;
							}
							else
							{

							}
			            
			            System.out.println();
			        }
				}
			}
			else if(box.equalsIgnoreCase("SENT") || (box.equalsIgnoreCase("DRAFT")))
			{
				String recpitent_id = (String) dbo.get("MI_TO_ID");
				if(rec_id.size() == 0)
				{
					if (sub.contains(search) || context.contains(search)) {
						MailInboxContent micontnt = new MailInboxContent();
						micontnt.makePojoFromBson(dbo);
						BasicDBList threadmsg = (BasicDBList) dbo.get("MI_THEREAD_MESSAGE");
						for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
							BasicDBObject dbo1 = (BasicDBObject) it1.next();
							micontnt.Threadmsg(dbo1);
						}
						if (count >= pagination && count < (pageno * maxvalue)) {
							sib.al.add(micontnt);
						} 
						count++;
					}
					else
					{
						
					}

				}
				else
				{
					Long keyValue;
				
				//ArrayList mapIterator = rec_id.
				innerloop:
		        for (int i=0;i<rec_id.size();i++) 
		        {
		        	
		          keyValue=  (Long) rec_id.get(i);
		          long final_rec = Long.parseLong(recpitent_id);
		                if (final_rec == keyValue || sub.contains(search) || context.contains(search)) {
		                	
		       				MailInboxContent micontnt = new MailInboxContent();
								micontnt.makePojoFromBson(dbo);
								BasicDBList threadmsg = (BasicDBList) dbo.get("MI_THEREAD_MESSAGE");
								for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
									BasicDBObject dbo1 = (BasicDBObject) it1.next();
									micontnt.Threadmsg(dbo1);
								}
								if (count >= pagination && count < (pageno * maxvalue)) {
									sib.al.add(micontnt);
								} 
								count++;
		                		break innerloop;
		                }
				}
				
			}
			}
			else if((box.equalsIgnoreCase("ARCHIVE")) || (box.equalsIgnoreCase("TRASH")))
			{
				String to_recpitent_id = (String) dbo.get("MI_TO_ID");
				String from_recpitent_id = (String) dbo.get("MI_FROM_ID");
				
				if(rec_id.size() == 0)
				{
					if (sub.contains(search) || context.contains(search)) {
						MailInboxContent micontnt = new MailInboxContent();
						micontnt.makePojoFromBson(dbo);
						BasicDBList threadmsg = (BasicDBList) dbo.get("MI_THEREAD_MESSAGE");
						for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
							BasicDBObject dbo1 = (BasicDBObject) it1.next();
							micontnt.Threadmsg(dbo1);
						}
						if (count >= pagination && count < (pageno * maxvalue)) {
							sib.al.add(micontnt);
						} 
						count++;
					}
					else
					{
					}

				}
				else
				{Long keyValue;
				
				//ArrayList mapIterator = rec_id.
				innerloop:
		        for (int i=0;i<rec_id.size();i++) 
		        {
		        	  keyValue=  (Long) rec_id.get(i);
		            long final_to_rec = Long.parseLong(to_recpitent_id);
		            long final_from_rec = Long.parseLong(from_recpitent_id);
		                if (final_to_rec == keyValue || final_from_rec == keyValue   || sub.contains(search) || context.contains(search)) {
		                	
		                		MailInboxContent micontnt = new MailInboxContent();
								micontnt.makePojoFromBson(dbo);
								BasicDBList threadmsg = (BasicDBList) dbo.get("MI_THEREAD_MESSAGE");
								for (Iterator<Object> it1 = threadmsg.iterator(); it1.hasNext();) {
									BasicDBObject dbo1 = (BasicDBObject) it1.next();
									micontnt.Threadmsg(dbo1);
								}
								if (count >= pagination && count < (pageno * maxvalue)) {
									sib.al.add(micontnt);
									System.out.println("count"+count);
								} 
								count++;
		                		break innerloop;
		                }
				}
				
			}
			
				

				}
}
	sib.setNumberofMails(count);
	return sib;
}

@Override
public Object getsortfrom(Long loginCredentialid, String search, String filter, String box, ArrayList rec_id1) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Boolean savechatmessages(String message, String author, String chat_room) {
	DBCollection ChatBOX = mc.getChatmessages();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
	Date now = new Date();
	String msgdate=sdf.format(now);
	String dateString=sdf.format(now).replaceAll("-", "")
            .replaceAll(":", "");
	try {
		BasicDBObject query = new BasicDBObject();
		query.append("ROOM_NAME", chat_room);
	    DBCursor result = ChatBOX.find(query);//checking the document for particular user exists or not
	    int resultcount=result.size();
	    if(resultcount==0){
	    }
	    String json5 = "{$push:{CHAT_MESSAGES:{SENDER:'"+author+"',MESSAGE:'"+message+"'}}}";
		DBObject push5 = (DBObject) JSON.parse(json5);
		BasicDBObject searchQuery5 = new BasicDBObject();
		searchQuery5.append("ROOM_NAME", chat_room);
		System.out.println("fsdgffdsfdjfgsdgfdsgfsdfsdsfsdfdsfdsgjhsdfsdgfjhsdgjhgdsfdgjsdgjhsdgfsgd");
		ChatBOX.update(searchQuery5, push5);
		}
	catch(Exception e)
		{
			
		}
	return true;
}

@Override
public Collection<Object> getroomswithmessages(Long loginCredentialid) {
	System.out.println(loginCredentialid);
	DBCollection chatbox = mc.getChatmessages();
	Collection<Object> obj = new ArrayList() ;
	DBObject match = new BasicDBObject();
	match.put("ROOM_NAME", new BasicDBObject("$regex", loginCredentialid.toString()));
	DBCursor cursor = chatbox.find(match);
	BasicDBObject account = null;
	while (cursor.hasNext()) {
		account = (BasicDBObject) cursor.next();
		String a = account.getString("ROOM_NAME").split("-")[0];
		String a1 = account.getString("ROOM_NAME").split("-")[1];
		System.out.println("romm name " + account.getString("ROOM_NAME"));
		if(loginCredentialid.toString().equalsIgnoreCase(a) || loginCredentialid.toString().equalsIgnoreCase(a1))
		{
			obj.add(account);
			System.out.println("dglfsadfgsdkfhksedhjksdhkhgkgdshkghskdfghskdghkjdshgk");
			System.out.println("romm name " + account.getString("ROOM_NAME"));
		}
	
		
	}
	return obj;
	}

@Override
public StringBuilder getchatroombyid(String chatroom_id, String author, Long author_id, String partner_name, String partner_id) {
	DBCollection chatbox = mc.getChatmessages();
	DBObject match = new BasicDBObject();
	BasicDBList match2;
	DBObject match3;
	match.put("ROOM_NAME",chatroom_id );
	DBCursor cursor = chatbox.find(match);
	StringBuilder sb=new StringBuilder();
	BasicDBObject account = null;
	String temp_msf = null;
	while (cursor.hasNext()) {
		account = (BasicDBObject) cursor.next();
		String a = account.getString("ROOM_NAME").split("-")[0];
		String a1 = account.getString("ROOM_NAME").split("-")[1];
		match2=(BasicDBList) account.get("CHAT_MESSAGES");
		for(int i=0;i<match2.size();i++){
			System.out.println("sdfgdgfsdghjdgfgdsgfsdygfyhsdgyhgs");
			match3=(DBObject) match2.get(i);
			if(match3.get("SENDER").toString().equalsIgnoreCase(author_id.toString()))
			{
				 temp_msf = author+" : " +match3.get("MESSAGE");
			}
			else
			{
				 temp_msf = partner_name+" : " +match3.get("MESSAGE");
			}
			sb.append(temp_msf+"<br>") ;
			sb.append(System.getProperty("line.separator"));
		}
}
	return sb;
}


//chat messages
@Override
public Boolean postMessage1(final MessageParam messageParam) {
	System.out.println("Inside Service Persistebce");
	DBCollection INBOX = mc.getNotification();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS");
	Date now = new Date();
	String msgdate=sdf.format(now);
	String dateString=sdf.format(now).replaceAll("-", "")
            .replaceAll(":", "");
	String msgid=messageParam.getMailfromid()+""+messageParam.getMailtoid()+dateString;
	String msgid1=messageParam.getMailtoid()+""+messageParam.getMailfromid()+dateString;
	
	try {

		/**** Insert ****/
		
		BasicDBObject query = new BasicDBObject();
		query.append("MB_USER_ID", messageParam.getMailfromid());
	    DBCursor result = INBOX.find(query);//checking the document for particular user exists or not
	    int resultcount=result.size();
	    System.out.println(result.size());
	    if(resultcount==0){
	    	//if not exists creating document
	    	System.out.println("fresh document from user");
	    	BasicDBObject document = new BasicDBObject();
			document.put("MB_USER_ID", messageParam.getMailfromid());
			document.put("MB_USER_TYPE", "CU");//for time being hardcoding customertype as cu
			INBOX.insert(document);
	    	
			
			String json = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid+"',MI_Communication:'oneway', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
			DBObject push = (DBObject) JSON.parse(json);
			BasicDBObject freshmail = new BasicDBObject();
			freshmail.append("MB_USER_ID", messageParam.getMailfromid());//appending data for particular user
			INBOX.update(freshmail, push);
			
			String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
			DBObject push1 = (DBObject) JSON.parse(json1);
			BasicDBObject searchQuery2 = new BasicDBObject();
			searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
			searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user
			INBOX.update(searchQuery2, push1);
			
			
	    }
	    else
	    {
	    	//operations of from user in inbox
	    	
	    	
	    	
	    	
	    	DBObject statusQuery2 = new BasicDBObject(); 
			statusQuery2.put("MI_SUBJECT",messageParam.getSubject());
	        DBObject elemMatchQuery2 = new BasicDBObject("$elemMatch", statusQuery2);
			BasicDBObject quer2 = new BasicDBObject();
			quer2.append("MB_MAIL_BOX", elemMatchQuery2);
			quer2.append("_id",false);
		    DBCursor cursor1 =INBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailfromid()),quer2);
			   
				 BasicDBObject	getidsentboxfromuser=null;
				 BasicDBList sentboxdetails=null;
				 String Sentboxmsgidfromuser=null;
				
				 if(cursor1.count()!=0){
				 while( cursor1.hasNext() )
			        {
					 getidsentboxfromuser = ( BasicDBObject ) cursor1.next();
			        	System.out.println("sentboxid"+getidsentboxfromuser);
			        }
				 if(getidsentboxfromuser!=null){
					 sentboxdetails = ( BasicDBList ) getidsentboxfromuser.get( "MB_MAIL_BOX" );
					 System.out.println("sentboxid"+sentboxdetails);
					 if(sentboxdetails!=null){
					 for( Iterator< Object > it = sentboxdetails.iterator(); it.hasNext(); )
				        {
				            BasicDBObject dbo     = ( BasicDBObject ) it.next();
				            Sentboxmsgidfromuser=dbo.getString("MI_ID");
				            System.out.println("sentboxmsgid from user"+Sentboxmsgidfromuser);
				        }
				 } 
				 }
				 if(sentboxdetails!=null){
				 
				 System.out.println("update object from user");
				 
					String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Sentboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push1 = (DBObject) JSON.parse(json1);
					BasicDBObject searchQuery2 = new BasicDBObject();
					searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
					searchQuery2.append("MB_MAIL_BOX.MI_ID", Sentboxmsgidfromuser);//appending data in threadmsg for particular user
					INBOX.update(searchQuery2, push1);
				 }else{
					 System.out.println("new object from user");
					 //no object present,so fresh object
					 String json = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid+"',MI_Communication:'oneway', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
						DBObject push = (DBObject) JSON.parse(json);
						BasicDBObject freshmail = new BasicDBObject();
						freshmail.append("MB_USER_ID", messageParam.getMailfromid());
						//freshmail.append("MI_SUBJECT",messageParam.getSubject());//appending data for particular user
						INBOX.update(freshmail, push);
						
						String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
						DBObject push1 = (DBObject) JSON.parse(json1);
						BasicDBObject searchQuery2 = new BasicDBObject();
						searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
						searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user
						INBOX.update(searchQuery2, push1);
				 }
				 }
	    }
	    
	    
	    //insert
	    
	    BasicDBObject query1 = new BasicDBObject();
		query1.append("MB_USER_ID", messageParam.getMailtoid());
	    DBCursor result1 = INBOX.find(query1);//checking the document for particular user exists or not
	    int resultcount1=result1.size();
	    System.out.println(result1.size());
	    if(resultcount1==0){
	    	//if not exists creating document
	    	BasicDBObject document = new BasicDBObject();
			document.put("MB_USER_ID", messageParam.getMailtoid());
			document.put("MB_USER_TYPE", "CU");//for time being hardcoding customertype as cu
			INBOX.insert(document);
			
			 	String json = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid1+"',MI_Communication:'oneway', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
				DBObject push = (DBObject) JSON.parse(json);
				BasicDBObject freshmail = new BasicDBObject();
				freshmail.append("MB_USER_ID", messageParam.getMailtoid());//appending data for particular user
				INBOX.update(freshmail, push);
				
				String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid1+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push1 = (DBObject) JSON.parse(json1);
				BasicDBObject searchQuery2 = new BasicDBObject();
				searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
				searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid1);//appending data in threadmsg for particular user
				INBOX.update(searchQuery2, push1);
	    	
	    }
	    else
	    {
	    //  operations of from user in inbox
	    	DBObject statusQuery2 = new BasicDBObject(); 
			statusQuery2.put("MI_SUBJECT",messageParam.getSubject());
	        DBObject elemMatchQuery2 = new BasicDBObject("$elemMatch", statusQuery2);
			BasicDBObject quer2 = new BasicDBObject();
			quer2.append("MB_MAIL_BOX", elemMatchQuery2);
			quer2.append("_id",false);
		    DBCursor cursor1 =INBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailtoid()),quer2);
		    
				 BasicDBObject	getidsentboxfromuser=null;
				
				 BasicDBList sentboxdetails=null;
				
				 String Sentboxmsgidfromuser=null;
				 
				 if(cursor1.count()!=0){
				 while( cursor1.hasNext() )
			        {
					 getidsentboxfromuser = ( BasicDBObject ) cursor1.next();
			        	System.out.println("sentboxid"+getidsentboxfromuser);
			        }
				 if(getidsentboxfromuser!=null){
					 sentboxdetails = ( BasicDBList ) getidsentboxfromuser.get( "MB_MAIL_BOX" );
					 System.out.println("sentboxid"+sentboxdetails);
					 if(sentboxdetails!=null){
					 for( Iterator< Object > it = sentboxdetails.iterator(); it.hasNext(); )
				        {
				            BasicDBObject dbo     = ( BasicDBObject ) it.next();
				            Sentboxmsgidfromuser=dbo.getString("MI_ID");
				            System.out.println("sentboxmsgid from user"+Sentboxmsgidfromuser);
				        }
				 } 
				 }if(sentboxdetails!=null){
					 
					 System.out.println("update object from user");
					 
						String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Sentboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
						DBObject push1 = (DBObject) JSON.parse(json1);
						BasicDBObject searchQuery2 = new BasicDBObject();
						searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
						searchQuery2.append("MB_MAIL_BOX.MI_ID", Sentboxmsgidfromuser);//appending data in threadmsg for particular user
						INBOX.update(searchQuery2, push1);
					 }else{
						 System.out.println("new object from user");
						 //no object present,so fresh object
						 String json = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid1+"',MI_Communication:'oneway', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
							DBObject push = (DBObject) JSON.parse(json);
							BasicDBObject freshmail = new BasicDBObject();
							freshmail.append("MB_USER_ID", messageParam.getMailtoid());
							//freshmail.append("MI_SUBJECT",messageParam.getSubject());//appending data for particular user
							INBOX.update(freshmail, push);
							
							String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid1+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
							DBObject push1 = (DBObject) JSON.parse(json1);
							BasicDBObject searchQuery2 = new BasicDBObject();
							searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
							searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid1);//appending data in threadmsg for particular user
							INBOX.update(searchQuery2, push1);
					 }
					 }
		    
				  
				 
		    
			
	    }
	  
	   /* String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+inboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
		DBObject push1 = (DBObject) JSON.parse(json1);
		BasicDBObject searchQuery2 = new BasicDBObject();
		searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
		searchQuery2.append("MB_MAIL_BOX.MI_ID", inboxmsgidfromuser);//appending data in threadmsg for particular user in inbox
		INBOX.update(searchQuery2, push1);*/
	    	//check in ARCHIVE of FROM user	
	    /*DBCollection Archive =mc.getArchive();
	    BasicDBObject Archivequery=checkmailbox(messageParam.getSubject(),messageParam.getMailtoid().toString()); 
	    DBCursor Archivecursorcursor =Archive.find(new BasicDBObject("MB_USER_ID",messageParam.getMailfromid()),Archivequery);*/ 
	    
	 /*   //  operations of from user in sentbox  
	    BasicDBObject quer1=checkmailbox(messageParam.getSubject(),messageParam.getMailtoid().toString());
	    DBCursor cursor1 = INBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailfromid()),quer1);
	    */
	   /* System.out.println(cursor0);//checking the same mail in inbox of from user
	    System.out.println(cursor1);//checking the same mail in sentbox of from user
	    
	     BasicDBObject	getidinboxfromuser=null;
		 BasicDBObject	getidsentboxfromuser=null;
		 BasicDBList inboxdetails=null;
		 BasicDBList sentboxdetails=null;
		 BasicDBList Archivedetails=null;
		 String Sentboxmsgidfromuser=null;
		 String inboxmsgidfromuser=null;
		 if(cursor1.count()!=0){
		 while( cursor1.hasNext() )
	        {
			 getidsentboxfromuser = ( BasicDBObject ) cursor1.next();
	        	System.out.println("sentboxid"+getidsentboxfromuser);
	        }
		 if(getidsentboxfromuser!=null){
			 sentboxdetails = ( BasicDBList ) getidsentboxfromuser.get( "MB_MAIL_BOX" );
			 System.out.println("sentboxid"+sentboxdetails);
			 if(sentboxdetails!=null){
			 for( Iterator< Object > it = sentboxdetails.iterator(); it.hasNext(); )
		        {
		            BasicDBObject dbo     = ( BasicDBObject ) it.next();
		            Sentboxmsgidfromuser=dbo.getString("MI_ID");
		            System.out.println("sentboxmsgid from user"+Sentboxmsgidfromuser);
		        }
		 } 
		 }
		 }
		
		 if(cursor0.count()!=0){
		 while( cursor0.hasNext() )
	        {
			 getidinboxfromuser = ( BasicDBObject ) cursor0.next();
	        	System.out.println("inboxid"+getidinboxfromuser);
	        }
		 if(getidinboxfromuser!=null){
			 inboxdetails  = ( BasicDBList ) getidinboxfromuser.get( "MB_MAIL_BOX" );
			 System.out.println("inboxid"+inboxdetails);
			 if(inboxdetails!=null){
			 for( Iterator< Object > it = inboxdetails.iterator(); it.hasNext(); )
		        {
		            BasicDBObject dbo1     = ( BasicDBObject ) it.next();
		            inboxmsgidfromuser=dbo1.getString("MI_ID");
		            System.out.println("inboxmsgid"+inboxmsgidfromuser);
		        }
		 } 
			 }
		 }
		 DBObject Archivefromuser=null;
		 String Archivemsgid=null;
		 String Communication=null;
		 if(Archivecursorcursor.count()!=0){
			 
			 while( Archivecursorcursor.hasNext() )
		        {
				 Archivefromuser = ( BasicDBObject ) Archivecursorcursor.next();
		        	System.out.println("archive"+Archivefromuser);
		        }
			 if(Archivefromuser!=null){
				 Archivedetails = ( BasicDBList ) Archivefromuser.get( "MB_MAIL_BOX" );
				 System.out.println("sentboxid"+Archivedetails);
				 if(Archivedetails!=null){
				 for( Iterator< Object > it = Archivedetails.iterator(); it.hasNext(); )
			        {
			            BasicDBObject dbo     = ( BasicDBObject ) it.next();
			            Archivemsgid=dbo.getString("MI_ID");
			            Communication=dbo.getString("MI_Communication");
			            System.out.println("sentboxmsgid from user"+Archivemsgid);
			        }
			 } 
			 }
			 }
	    
	    if(inboxdetails==null&&sentboxdetails==null){
	    	if(Archivedetails==null){
			 //generate mail as a fresh mail because the mail is not there in inbox ,sentbox and Archive of from user
	    	//mail should be in sentboxonly
	    	System.out.println("0 0");
	    	System.out.println("generate mail as a fresh mail because the mail is not there in inbox and sentbox of from user,mail should be in sentboxonly");
			    String json = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid+"',MI_Communication:'oneway', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
				DBObject push = (DBObject) JSON.parse(json);
				BasicDBObject freshmail = new BasicDBObject();
				freshmail.append("MB_USER_ID", messageParam.getMailfromid());//appending data for particular user in sentbox
				SENTBOX.update(freshmail, push);
				
				String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push1 = (DBObject) JSON.parse(json1);
				BasicDBObject searchQuery2 = new BasicDBObject();
				searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
				searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user in sentbox
				SENTBOX.update(searchQuery2, push1);
				freshmail1=true;
	    	}else if(Archivedetails!=null){
	    		// the mail is present in archive but not in sentbox and inbox of FROM user
	    		//move the mail to sentbox ,if it is replied threaded mail move to inbox also
	    		
	    		copymail(Archivefromuser, "sentbox", messageParam.getMailfromid(),copymailfrom);
	    		//now append the content to it
	    		String jsona = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Archivemsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject pusha = (DBObject) JSON.parse(jsona);
				BasicDBObject searchQuerya = new BasicDBObject();
				searchQuerya.append("MB_USER_ID",messageParam.getMailfromid());
				searchQuerya.append("MB_MAIL_BOX.MI_ID", Archivemsgid);//appending data in threadmsg for particular user in sentbox
				SENTBOX.update(searchQuerya, pusha);
				
	    		if(Communication.equalsIgnoreCase("twoway")){
	    			//now append the content to it
	    			String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Archivemsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject push1 = (DBObject) JSON.parse(json1);
					BasicDBObject searchQuery2 = new BasicDBObject();
					searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
					searchQuery2.append("MB_MAIL_BOX.MI_ID", Archivemsgid);//appending data in threadmsg for particular user in sentbox
					INBOX.update(searchQuery2, push1);
	    		}
	    		
	    		// now delete from archive
	    		 checkanddeletemail(messageParam.getMailfromid(), Archivemsgid, Archive);
	    		
	    	}
		 }else if(inboxdetails!=null&&sentboxdetails!=null){
			 // the same mail is present in both inbox and sentbox of from user
			 // so just append the newmail as a reply in both sentbox and inbox of from user
			    System.out.println("the same mail is present in both inbox and sentbox of from user,so just append the newmail as a reply in both sentbox and inbox of from user");
			    String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+inboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push1 = (DBObject) JSON.parse(json1);
				BasicDBObject searchQuery2 = new BasicDBObject();
				searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
				searchQuery2.append("MB_MAIL_BOX.MI_ID", inboxmsgidfromuser);//appending data in threadmsg for particular user in inbox
				INBOX.update(searchQuery2, push1);
				UpdateCommunication(messageParam.getMailfromid(), INBOX, inboxmsgidfromuser);
				
				String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Sentboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push2 = (DBObject) JSON.parse(json2);
				BasicDBObject searchQuery3 = new BasicDBObject();
				searchQuery3.append("MB_USER_ID",messageParam.getMailfromid());
				searchQuery3.append("MB_MAIL_BOX.MI_ID", Sentboxmsgidfromuser);//appending data in threadmsg for particular user in sentbox
				SENTBOX.update(searchQuery3, push2);
				UpdateCommunication(messageParam.getMailfromid(), SENTBOX, Sentboxmsgidfromuser);
		 }else if(inboxdetails==null&&sentboxdetails!=null){
			 // the mail is present in only sentbox of from user
			 // so just append the new mail as a reply in sentbox of from user
			 System.out.println("the mail is present in only sentbox of from user,so just append the new mail as a reply in sentbox of from user");
			    String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Sentboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push2 = (DBObject) JSON.parse(json2);
				BasicDBObject searchQuery3 = new BasicDBObject();
				searchQuery3.append("MB_USER_ID",messageParam.getMailfromid());
				searchQuery3.append("MB_MAIL_BOX.MI_ID", Sentboxmsgidfromuser);//appending data in threadmsg for particular user in sentbox
				SENTBOX.update(searchQuery3, push2);
			 
			 
		 }else if(inboxdetails!=null&&sentboxdetails==null){
			 // the mail is present in only inbox of from user
			 // so append the newmail as a reply in inbox andcopy the whole mail in sentbox
			 System.out.println("the mail is present in only inbox of from user,so append the newmail as a reply in inbox andcopy the whole mail in sentbox");
			    String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+inboxmsgidfromuser+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push1 = (DBObject) JSON.parse(json1);
				BasicDBObject searchQuery2 = new BasicDBObject();
				searchQuery2.append("MB_USER_ID",messageParam.getMailfromid());
				searchQuery2.append("MB_MAIL_BOX.MI_ID", inboxmsgidfromuser);//appending data in threadmsg for particular user in inbox
				INBOX.update(searchQuery2, push1);
				UpdateCommunication(messageParam.getMailfromid(), INBOX, inboxmsgidfromuser);
				 DBObject statusQuery1 = new BasicDBObject("MI_ID", inboxmsgidfromuser); 
					
			        DBObject elemMatchQuery1 = new BasicDBObject("$elemMatch", statusQuery1);
			        
					BasicDBObject quer = new BasicDBObject();
					quer.append("MB_MAIL_BOX", elemMatchQuery1);
					try{
				    DBCursor result1 = INBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailfromid()),quer);
				    System.out.println(result1.size());
				    DBObject  obj=null;
				    if(result1.hasNext())
				     obj= result1.next();
				   System.out.println(obj.get("MB_MAIL_BOX"));
				   copymail(obj, "sentbox", messageParam.getMailfromid(),copymailfrom);
				   UpdateCommunication(messageParam.getMailfromid(), SENTBOX, Sentboxmsgidfromuser);
				} catch (MongoException e) {
				   		e.printStackTrace();
					    }
				
			 
		 }
	    	
	   
		//inserting data in inbox
		//operations of to user
		BasicDBObject inboxquery = new BasicDBObject();
		inboxquery.append("MB_USER_ID", messageParam.getMailtoid());
	    DBCursor inboxresult = INBOX.find(inboxquery);//checking the document for mailto user exists or not
	    int inboxresultcount=inboxresult.size();
	    System.out.println(inboxresult.size());
	    if(inboxresultcount==0){
	    	
	    	BasicDBObject document = new BasicDBObject();
			document.put("MB_USER_ID", messageParam.getMailtoid());
			document.put("MB_USER_TYPE", "CU");//for time being hardcoding customertype as cu
			List<BasicDBObject> MB_MAIL_INBOX = new ArrayList<BasicDBObject>();
			document.put("MB_MAIL_BOX", MB_MAIL_INBOX);
			INBOX.insert(document);
	    }
	   
	    BasicDBObject quer2=checkmailbox(messageParam.getSubject(),messageParam.getMailfromid().toString());
	    DBCursor cursor2 = SENTBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailtoid()),quer2);
	    		 
	    System.out.println("cursor2"+cursor2);
	    System.out.println("cursor2 count"+cursor2.count());
	    
	    BasicDBObject quer3=checkmailbox(messageParam.getSubject(),messageParam.getMailfromid().toString());
	    DBCursor cursor3 =  INBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailtoid()),quer2);
	        System.out.println(cursor3.size());
		    System.out.println("cursor3"+cursor3);
			System.out.println("cursor3 count"+cursor3.count());
	//check in ARCHIVE of FROM user	
		   
      BasicDBObject ArchiveTOquery=checkmailbox(messageParam.getSubject(),messageParam.getMailfromid().toString()); 
      DBCursor ArchivecursorTO =Archive.find(new BasicDBObject("MB_USER_ID",messageParam.getMailtoid()),ArchiveTOquery); 	
			
			 BasicDBObject	getidsentbox=null;
			 String Sentboxmsgid=null;
			 BasicDBList inbxdetails1=null;
			 BasicDBList inbxdetails=null;
			 BasicDBObject	getidinbox=null;
			 String inboxmsgid=null;
			 if(cursor2.count()!=0){
			 while( cursor2.hasNext() )
		        {
				 getidsentbox = ( BasicDBObject ) cursor2.next();
		        	System.out.println("sentboxid"+getidsentbox);
		        }
			 if(getidsentbox!=null){
				inbxdetails = ( BasicDBList ) getidsentbox.get( "MB_MAIL_BOX" );
				 System.out.println("sentboxid"+inbxdetails);
				 if(inbxdetails!=null){
				 for( Iterator< Object > it = inbxdetails.iterator(); it.hasNext(); )
			        {
			            BasicDBObject dbo     = ( BasicDBObject ) it.next();
			            Sentboxmsgid=dbo.getString("MI_ID");
			            System.out.println("sentboxmsgid"+Sentboxmsgid);
			        }
			 } 
			 }
			 }
			 
			 if(cursor3.count()!=0){
			 while( cursor3.hasNext() )
		        {
				 getidinbox = ( BasicDBObject ) cursor3.next();
		        	System.out.println("inboxid"+getidinbox);
		        }
			 if(getidinbox!=null){
			 inbxdetails1  = ( BasicDBList ) getidinbox.get( "MB_MAIL_BOX" );
				 System.out.println("inboxid"+inbxdetails1);
				 if(inbxdetails1!=null){
				 for( Iterator< Object > it = inbxdetails1.iterator(); it.hasNext(); )
			        {
			            BasicDBObject dbo1     = ( BasicDBObject ) it.next();
			            inboxmsgid=dbo1.getString("MI_ID");
			            System.out.println("inboxmsgid"+inboxmsgid);
			        }
			 } 
				 }
			 }
			 
			 DBObject Archivetouser=null;
			 String ArchiveTOmsgid=null;
			 String CommunicationTOuser=null;
			 BasicDBList ArchiveTOdetails=null;
			 if(ArchivecursorTO.count()!=0){
				 
				 while( ArchivecursorTO.hasNext() )
			        {
					 Archivetouser = ( BasicDBObject ) ArchivecursorTO.next();
			        	System.out.println("archive"+Archivetouser);
			        }
				 if(Archivetouser!=null){
					 ArchiveTOdetails = ( BasicDBList ) Archivetouser.get( "MB_MAIL_BOX" );
					 System.out.println("sentboxid"+ArchiveTOdetails);
					 if(ArchiveTOdetails!=null){
					 for( Iterator< Object > it = ArchiveTOdetails.iterator(); it.hasNext(); )
				        {
				            BasicDBObject dbo     = ( BasicDBObject ) it.next();
				            ArchiveTOmsgid=dbo.getString("MI_ID");
				            CommunicationTOuser=dbo.getString("MI_Communication");
				            System.out.println("sentboxmsgid from user"+Archivemsgid);
				        }
				 } 
				 }
				 }
		 if(inbxdetails==null&&inbxdetails1==null){
			 System.out.println("compose mail 0 0");
			 if(ArchiveTOdetails==null){
			 //generate mail as a fresh mail because the mail is not present in sentbox and inbox of the To user
			    String json = "{$push:{MB_MAIL_BOX:{$each:[{MI_ID:'"+msgid+"',MI_Communication:'oneway', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'0',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD',MI_THEREAD_MESSAGE:[]}],$sort: { MI_SENT_DATE_TIME: -1 }}}}";
				DBObject push = (DBObject) JSON.parse(json);
				BasicDBObject freshmail = new BasicDBObject();
				freshmail.append("MB_USER_ID", messageParam.getMailtoid());//appending data for particular user
				INBOX.update(freshmail, push);
				
				String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push1 = (DBObject) JSON.parse(json1);
				BasicDBObject searchQuery2 = new BasicDBObject();
				searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
				searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user
				INBOX.update(searchQuery2, push1);
				freshmail2=true;
			 }else if(ArchiveTOdetails!=null){
				// the mail is present in archive but not in sentbox and inbox of TO user
		    		//move the mail to inbox ,if it is replied threaded mail move to sentbox also
		    		copymail(Archivetouser, "inbox", messageParam.getMailtoid(),copymailfrom);
		    		//now append the content to it
		    		String jsona = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+ArchiveTOmsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
					DBObject pusha = (DBObject) JSON.parse(jsona);
					BasicDBObject searchQuerya = new BasicDBObject();
					searchQuerya.append("MB_USER_ID",messageParam.getMailtoid());
					searchQuerya.append("MB_MAIL_BOX.MI_ID", ArchiveTOmsgid);//appending data in threadmsg for particular user in sentbox
					INBOX.update(searchQuerya, pusha);
					
		    		if(CommunicationTOuser.equalsIgnoreCase("twoway")){
		    			copymail(Archivetouser, "sentbox", messageParam.getMailtoid(),copymailfrom);
		    			//now append the content to it
		    			String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+msgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
						DBObject push1 = (DBObject) JSON.parse(json1);
						BasicDBObject searchQuery2 = new BasicDBObject();
						searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
						searchQuery2.append("MB_MAIL_BOX.MI_ID", msgid);//appending data in threadmsg for particular user in sentbox
						SENTBOX.update(searchQuery2, push1);
		    		}
		    		
		    		 // now delete from archive
		    		 checkanddeletemail(messageParam.getMailtoid(), ArchiveTOmsgid, Archive);
			 }
		 }else if(inbxdetails!=null&&inbxdetails1!=null){
			 //generate mail as a reply mail because the mail is  present in sentbox and inbox of the To user
			 //now append the content both in sentbox and inbox of To user
			    System.out.println("compose mail 1 1");
			    System.out.println("generate mail as a reply mail because the mail is  present in sentbox and inbox of the To user,now append the content both in sentbox and inbox of To user");
			    String json1 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+inboxmsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push1 = (DBObject) JSON.parse(json1);
				BasicDBObject searchQuery2 = new BasicDBObject();
				searchQuery2.append("MB_USER_ID",messageParam.getMailtoid());
				searchQuery2.append("MB_MAIL_BOX.MI_ID", inboxmsgid);//appending data as a reply in inbox of TOuser
				INBOX.update(searchQuery2, push1);
				UpdateCommunication(messageParam.getMailtoid(), INBOX, inboxmsgid);
				UpdateReplyDate(messageParam.getMailtoid(), INBOX, inboxmsgid);
				String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Sentboxmsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push2 = (DBObject) JSON.parse(json2);
				BasicDBObject searchQuery5 = new BasicDBObject();
				searchQuery5.append("MB_USER_ID",messageParam.getMailtoid());
				searchQuery5.append("MB_MAIL_BOX.MI_ID", Sentboxmsgid);//appending data as a reply in sent of TOuser
				SENTBOX.update(searchQuery5, push2);
				UpdateCommunication(messageParam.getMailtoid(), SENTBOX, Sentboxmsgid);
				UpdateReplyDate(messageParam.getMailtoid(), SENTBOX, Sentboxmsgid);
		 }else if(inbxdetails!=null&&inbxdetails1==null){
			//generate mail as a reply mail because the mail is  present in sentbox of To user
			 // now append the mail as replycontent to sentbox of To user
			 //copy the whole mail to inbox of To user,becuase data is not in inbox
			 System.out.println("compose mail 1 0");
			 System.out.println("generate mail as a reply mail because the mail is  present in sentbox of To user, now append the mail as replycontent to sentbox of To user,copy the whole mail to inbox of To user,becuase data is not in inbox");
			    String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+Sentboxmsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push2 = (DBObject) JSON.parse(json2);
				BasicDBObject searchQuery5 = new BasicDBObject();
				searchQuery5.append("MB_USER_ID",messageParam.getMailtoid());
				searchQuery5.append("MB_MAIL_BOX.MI_ID", Sentboxmsgid);//appending data as a reply in sent of TOuser
				SENTBOX.update(searchQuery5, push2);
				UpdateCommunication(messageParam.getMailtoid(), SENTBOX, Sentboxmsgid);
				UpdateReplyDate(messageParam.getMailtoid(), SENTBOX, Sentboxmsgid);
				 DBObject statusQuery4 = new BasicDBObject("MI_ID", Sentboxmsgid); 
					
			        DBObject elemMatchQuery4 = new BasicDBObject("$elemMatch", statusQuery4);
			        
					BasicDBObject quer = new BasicDBObject();
					quer.append("MB_MAIL_BOX", elemMatchQuery4);
					try{
				    DBCursor result1 = SENTBOX.find(new BasicDBObject("MB_USER_ID",messageParam.getMailtoid()),quer);
				    System.out.println(result1.size());
				    DBObject  obj=null;
				    if(result1.hasNext())
				     obj= result1.next();
				   System.out.println(obj.get("MB_MAIL_BOX"));
				   
				   copymail(obj, "inbox", messageParam.getMailtoid(),copymailfrom);
				    UpdateCommunication(messageParam.getMailtoid(), INBOX, Sentboxmsgid);
					UpdateReplyDate(messageParam.getMailtoid(), INBOX, Sentboxmsgid);
			       
				} catch (MongoException e) {
				   		e.printStackTrace();
					    }

		 }else if(inbxdetails==null&&inbxdetails1!=null){
			 // the mail is present in inbox and not present in sentbox of To user
			 //append the mail as reply content to inbox of Touser
			 System.out.println("compose mail 0 1");
			 System.out.println("the mail is present in inbox and not present in sentbox of To user,append the mail as reply content to inbox of Touser");
			    String json2 = "{$push:{MB_MAIL_BOX.$.MI_THEREAD_MESSAGE:{MI_ID:'"+inboxmsgid+"', MI_FROM_ID:'"+messageParam.getMailfromid()+"',MI_TO_ID:'"+messageParam.getMailtoid()+"',MI_SUBJECT:'"+messageParam.getSubject()+"',MI_CONTENT:'"+messageParam.getChatmessage()+"',MI_STAR_TYPE:'0',MI_SENT_IP:'"+messageParam.getSentip()+"',MI_READ_IP:'193.180.200',MI_SENT_DATE_TIME:'"+msgdate+"',MI_SENT_DATE:'"+DateUtil.getDate()+"',MI_SENT_TIME:'"+DateUtil.getTime12()+"',MI_READ_DATE:'UNREAD'}}}";
				DBObject push2 = (DBObject) JSON.parse(json2);
				BasicDBObject searchQuery5 = new BasicDBObject();
				searchQuery5.append("MB_USER_ID",messageParam.getMailtoid());
				searchQuery5.append("MB_MAIL_BOX.MI_ID", inboxmsgid);//appending data as a reply in inbox of TOuser
				INBOX.update(searchQuery5, push2);
				UpdateReplyDate(messageParam.getMailtoid(), INBOX, inboxmsgid);
		 }
		 if(freshmail1==true||freshmail2==true){
		  CheckAutoresponse(messageParam.getMailtoid(), msgid, messageParam);
		  freshmail1=false;
		  freshmail2=false;
		 }*/
 } catch (MongoException e) {
	e.printStackTrace();
    }
	
	return true;
	
}

@Override
public Boolean deletechatmessagesbyid(String chatroom_id) {
	// TODO Auto-generated method stub
	DBCollection chatbox = mc.getChatmessages();
	DBObject match = new BasicDBObject();
	match.put("ROOM_NAME",chatroom_id );
	WriteResult cursor = chatbox.remove(match);
	System.out.println(cursor);
	return true;
}

@Override
public Object getNotify(Long userid, int pageno, String filter) {

	//MongoConfig mc=new MongoConfig();
	DBCollection INBOX = mc.getNotification();
	return getboxes(userid, pageno, INBOX,filter);
    

}

@Override
public String updatetodraft(MessageParam messageParam) {
	
		// TODO Auto-generated method stub
		DBCollection DRAFT = mc.getDraftBox();
		BasicDBObject deletequery = new BasicDBObject();
		deletequery.append("MB_USER_ID", messageParam.getMailfromid());
		BasicDBObject condition = new BasicDBObject("MI_ID", messageParam.getMsgid());
		BasicDBObject array = new BasicDBObject("MB_MAIL_BOX", condition);
		BasicDBObject update = new BasicDBObject("$pull", array);
		DRAFT.update(deletequery, update);
		
		//calling save draft function
		String msgids = savetodraft(messageParam);
		return msgids;
	}

}

