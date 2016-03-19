package com.fpl.persistence.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.fpl.login.LoginRecordTO;
import com.fpl.persistence.communication.MongoConfig;
import com.fpl.util.DateUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class loginAuditDAO implements ILoginAuditDAO {
	private MongoConfig cfg= new MongoConfig() ;
	

	@Override
	public boolean Loginlog(LoginRecordTO lr) {
		// TODO Auto-generated method stub
		try{
			DBCollection Login=cfg.getLoginAudit();
			BasicDBObject query = new BasicDBObject();
			query.append("MB_USER_LOGIN", "fplonline");
			DBCursor result = Login.find(query);// checking the document for
													// particular user exists or
													// not
			int resultcount = result.size();
			System.out.println(result.size());
			if (resultcount == 0) {
				// if not exists creating document
				BasicDBObject document = new BasicDBObject();
				document.put("MB_USER_LOGIN", "fplonline");
				// for time being hardcoding
													// customertype as cu
				Login.insert(document);

			}
			
			
			System.out.println("inside try daos");
		  
		  String sessionId=lr.getSessionId();
		  String userId=lr.getUserId()  ;	
		  String devicetype=lr.getDevicetype() ; 				
		  String  deviceid=lr.getDeviceid()  ; 				
		  String loginresult=lr.getLoginresult()  ;			
		  String resultreason=lr.getResultreason() ;  		    
		  long logincrediantialid=lr.getLogincrediantialid() ;  			    
		  String ipaddress=lr.getIpaddress() ;  				
		  String logindate=lr.getLogindate();				
		  String logoutdate=lr.getLogoutdate();
		
		String jsona = "{$push:{LR_LOGIN_AUDIT:{Session_Id:'" + sessionId
				+ "', User_Id:'" + userId + "',Device_type:'"
				+ devicetype + "',Device_id:'" +deviceid
				+ "',Login_result:'" + loginresult + "',Result_reason:'" + resultreason + "',Login_Crediantialid:'"
				+ logincrediantialid + "',Ip_address:'" + ipaddress
				+ "',Login_date:'" + logindate + "',Logout_date:'" + logoutdate+ "'}}}";
		DBObject pusha = (DBObject) JSON.parse(jsona);
		BasicDBObject searchQuerya = new BasicDBObject();
		searchQuerya.append("MB_USER_LOGIN", "fplonline");
		                                                       
		Login.update(searchQuerya, pusha);
		}catch(Exception e){
			System.out.println("inside daos");
			e.printStackTrace();
		}
		
		
		
		return false;
	}
/*public final void setmongoconfig(final MongoConfig cfg){
	this.cfg=cfg;
}*/


	@Override
	public boolean sessionlog(long userid, String sessionid) {
		// TODO Auto-generated method stub
		try{
			System.out.println("insidesessionlocked"+userid+""+sessionid);
			DBCollection sessions=cfg.getSessions();
			
			BasicDBObject query = new BasicDBObject();
			query.append("MB_USER",userid );
			DBCursor result = sessions.find(query);
			
			int resultcount = result.size();
			System.out.println(result.size());
			if (resultcount == 0) {
				// if not exists creating document
				BasicDBObject document = new BasicDBObject();
				document.put("MB_USER", userid);
				sessions.insert(document);
			}
			String jsona = "{$push:{LR_LOCKED_SESSIONS:'" + sessionid+ "'}}";
			DBObject pusha = (DBObject) JSON.parse(jsona);
			BasicDBObject searchQuerya = new BasicDBObject();
			searchQuerya.append("MB_USER", userid);                                                       
			sessions.update(searchQuerya, pusha);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}


	@SuppressWarnings("deprecation")
	@Override
	public HashMap<String,Integer> getSessionlog(long userid,String sessionid) {
		HashMap<String,Integer> sessionmap= new HashMap<String, Integer>();
		int value=0;
		String locked=null;
		// TODO Auto-generated method stub
		try{
			DBCollection sessions=cfg.getSessions();
			
			BasicDBObject query = new BasicDBObject();
			query.append("MB_USER",userid );
			DBCursor result = sessions.find(query);
			BasicDBList lockedsess=null;
			BasicDBObject Activesess=null;
			if(result.hasNext()){
				DBObject obj=result.next();
				lockedsess = (BasicDBList) obj.get("LR_LOCKED_SESSIONS");
				Activesess=(BasicDBObject) obj.get("LR_ATTEMPT_SESSIONS");
			}if(null!=lockedsess){
				 if(lockedsess.contains(sessionid)){
				  locked="true";
				 }
			}
			if(null!=Activesess){
				
				System.out.println("keys available"+Activesess.getString(sessionid));
				
				//System.out.println("keys available1"+attemp.get(sessionid));
				if(null!=(Activesess.getString(sessionid))){
					value= Activesess.getInt(sessionid);
					System.out.println("keys available1"+value);
						}	    
			    }
			
							
			sessionmap.put(locked, value);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sessionmap;
	}


	
	@Override
	public boolean sessionlog(long userid, String sessionid, int attempts) {
		// TODO Auto-generated method stub
		try{
			System.out.println("insidesessionlog"+userid+""+sessionid+""+attempts);
			DBCollection sessions=cfg.getSessions();
			
			BasicDBObject query = new BasicDBObject();
			query.append("MB_USER",userid );
			DBCursor result = sessions.find(query);
			
			int resultcount = result.size();
			System.out.println(result.size());
			if (resultcount == 0) {
				// if not exists creating document
				BasicDBObject document = new BasicDBObject();
				document.put("MB_USER", userid);
				sessions.insert(document);
			}
			BasicDBObject Activesess=null;
			if(result.hasNext()){
				DBObject obj=result.next();
				Activesess=(BasicDBObject) obj.get("LR_ATTEMPT_SESSIONS");
			}
			
			if(null!=Activesess){
				
					//System.out.println("keys available1"+attemp.get(sessionid));
					if(null!=(Activesess.getString(sessionid))){
						String key1="LR_ATTEMPT_SESSIONS."+sessionid;
						String key2="LR_ATTEMPT_SESSIONS.$."+sessionid;
						System.out.println("insidesessionupdate"+userid+""+sessionid+""+attempts);
						/*BasicDBObject queryset = new BasicDBObject();
						queryset.put("MB_USER", userid);
						queryset.put(key1, sessionid);
						BasicDBObject data = new BasicDBObject();
						data.put(key2, attempts);
						BasicDBObject command = new BasicDBObject();
						command.put("$set", data);
						sessions.update(query, command);*/
						
						String jsona = "{$set:{"+key1+" :" + attempts+"}}";
						DBObject pusha = (DBObject) JSON.parse(jsona);
						BasicDBObject searchQuerya = new BasicDBObject();
						searchQuerya.append("MB_USER", userid);                                                       
						sessions.update(searchQuerya, pusha);
						
					}else{
						String key1="LR_ATTEMPT_SESSIONS."+sessionid;
						System.out.println("inside new session push"+userid+""+sessionid+""+attempts);
						String jsona = "{$set:{"+key1+" :" + attempts+ "}}";
						DBObject pusha = (DBObject) JSON.parse(jsona);
						BasicDBObject searchQuerya = new BasicDBObject();
						searchQuerya.append("MB_USER", userid);                                                       
						sessions.update(searchQuerya, pusha);
						}
				
			}else{
				String key1="LR_ATTEMPT_SESSIONS."+sessionid;
				System.out.println("inside new session push"+userid+""+sessionid+""+attempts);
				String jsona = "{$set:{"+key1+" :" + attempts+ "}}";
				DBObject pusha = (DBObject) JSON.parse(jsona);
				BasicDBObject searchQuerya = new BasicDBObject();
				searchQuerya.append("MB_USER", userid);                                                       
				sessions.update(searchQuerya, pusha);
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
