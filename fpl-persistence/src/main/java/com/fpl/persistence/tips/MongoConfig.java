package com.fpl.persistence.tips;

import java.net.UnknownHostException;


import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.mongodb.MongoClient;


public class MongoConfig {
	public DBCollection SENTBOX;
	public DBCollection INBOX;
	public DBCollection trashbox;
	public DBCollection Draftbox;
	public DBCollection Settings;
	public DBCollection Archive;
	public DBCollection Tips;
	public MongoConfig(){
		MongoClient mongo;
		try {
			mongo = new MongoClient("localhost", 27017);
			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("fplonline");

			/**** Get collection / table from 'fplonline' ****/
			// if collection doesn't exists, MongoDB will create it for you
			
			//inserting data in sentbox
			this.SENTBOX = db.getCollection("Mailsentbox");
			this.INBOX = db.getCollection("Mailinbox");
			this.trashbox = db.getCollection("Trashbox");
			this.Draftbox=db.getCollection("Draftbox");
			this.Settings=db.getCollection("Settings");
			this.Archive=db.getCollection("Archive");
			this.Tips=db.getCollection("Tips");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DBCollection getSentBox(){
		return this.SENTBOX;
	}
	public DBCollection getInBox(){
		return this.INBOX;
	}
	public DBCollection getTrashBox(){
		return this.trashbox;
	}
	public DBCollection getDraftBox(){
		return this.Draftbox;
	}
	public DBCollection getSettings(){
		return this.Settings;
	}
	public DBCollection getArchive(){
		return this.Archive;
	}
	public DBCollection getTips() {
		return this.Tips;
	}
}
