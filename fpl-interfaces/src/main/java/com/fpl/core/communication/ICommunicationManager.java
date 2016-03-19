package com.fpl.core.communication;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fpl.core.tips.AdminTips;
import com.fpl.login.UserType;

public interface ICommunicationManager {

	Collection<UserInfo> getUserList(UserSearchParam searchParam);
	Boolean postMessage(MessageParam messageParam);
	Boolean Settings(Usersettings settingsParam);
	String savetodraft(MessageParam messageParam);
	Boolean Discardfromdraft(String msgid,String userid);
	Boolean ChangeReaddate(Long loginCredentialid,String msgid);
	Object getInbox(Long loginCredentialid,int pageno,String filter);
	
	Boolean movetotrash(String date,String mids,String userid,String from);
	Boolean movetoarchive(String date,String mids,String userid,String from);
	Object getTrashbox(Long loginCredentialid,int pageno,String filter);
	Object getArchive(Long loginCredentialid,int pageno,String filter);
	Object getSentMail(Long loginCredentialid,int pageno,String filter);
	Object getSettings(Long loginCredentialid);
	String ChangeStarRating(long userid, String msgid, String rating,String box);
	Boolean reply(String msgid, String from, MessageParam messageParam);
	Boolean SaveTips(AdminTips admin_tips);
	Boolean updatetips(AdminTips admin_tips);
	Boolean deletetips(String tip_name);
	Object GetTips();
	Object GetTipsbyid(String tip_name);
	Boolean createchatrooms(String roomname,String from_user,String to_user);
	Collection<String> getrooms(long userid);
	Object getsearchmail(Long loginCredentialid, String search, String filter, String box, ArrayList rec_id);
	Object getsortfrom(Long loginCredentialid, String search, String filter, String box, ArrayList rec_id1);
	Object getDraft(Long loginCredentialid, int pageno, String filter);
	Boolean undofromtrash(String date, String mids, String userid, String from);
	Collection<Object> getroomswithmessages(Long loginCredentialid);
	StringBuilder getchatroombyid(String chatroom_id, String string, Long long1, String string2, String partner_id);
	Boolean postMessage1(MessageParam messageParam);
	Boolean deletechatmessagesbyid(String chatroom_id);
	Boolean savechatmessages(String message, String author, String chat_room);
	Object getNotify(Long loginCredentialid, int parseInt, String filter);
	String updatetodraft(MessageParam messageParam);
	
}
