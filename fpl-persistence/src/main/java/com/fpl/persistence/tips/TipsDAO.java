package com.fpl.persistence.tips;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class TipsDAO implements ITipsDAO {

	private JdbcTemplate jdbcTemplate;
	MongoConfig mc=new MongoConfig();
	
	
	
	
		
	public Boolean SaveTips(String admin_tips){
		System.out.println("called dao function");
		DBCollection TIPS = mc.getTips();
		String AD_TIPS=admin_tips;
	    //BasicDBObject query = new BasicDBObject();
	   // query.put("MB_USER_ID", useridl);
	   //DBCursor settingsresult = SETTINGS.find(query);//checking the document for particular user exists or not
	   // int resultcount=settingsresult.size();
	    	//if not exists creating document
	    	BasicDBObject document = new BasicDBObject();
	    	System.out.println("called dao function2");
	    	document.put("AD_TIPS", AD_TIPS);
	    	System.out.println("called dao function3");
	    	TIPS.insert(document);
	    	System.out.println(TIPS);
	    	return true;
		
	}

}

