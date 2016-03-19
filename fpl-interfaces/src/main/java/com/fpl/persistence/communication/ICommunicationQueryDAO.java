package com.fpl.persistence.communication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fpl.core.communication.MessageParam;
import com.fpl.core.communication.UserInfo;
import com.fpl.core.communication.UserSearchParam;
import com.fpl.core.communication.Usersettings;
import com.fpl.core.tips.AdminTips;
import com.fpl.login.UserType;

public interface ICommunicationQueryDAO {

	List<UserInfo> getUserList(UserSearchParam searchParam);
	Boolean postMessage(MessageParam searchParam);
	Boolean Settings(Usersettings settingsParam);
	String savetodraft(MessageParam searchParam);
	Boolean Discardfromdraft(String msgid,String userid);
	Boolean ChangeReaddate(long userid,String msgid);
	Boolean movetotrash(String date,String mids,String userid,String from);
	Boolean movetoarchive(String date,String mids,String userid,String from);
	Object getInbox(Long userid,int pageno,String filter);
	Object getNotify(Long userid,int pageno,String filter);
	Object getTrashbox(Long userid,int pageno,String filter);
	Object getArchive(Long userid,int pageno,String filter);
	Object getSentMail(Long userId,int pageno,String filter);
	Object getSettings(Long userid);
	String ChangeStarRating(long userid, String msgid, String rating,String box);
	Boolean reply(String msgid, String from, MessageParam messageParam);
	Boolean SaveTips(AdminTips admin_tips);
	Boolean deletetips(String tip_name);
	Boolean updatetips(AdminTips admin_tips);
	Object GetTips();
	Object GetTipsbyid(String tip_name);
	Boolean createchatrooms(String roomname,String from_user,String to_user);
	Collection<String> getrooms(long userid);
	Object getsearchmail(Long loginCredentialid, String search, String filter, String box, ArrayList rec_id);
	Object getsortfrom(Long loginCredentialid, String search, String filter, String box, ArrayList rec_id1);
	Object getDraft(Long userid, int pageno, String filter);
	Boolean undofromtrash(String date, String mids, String userid, String from);
	Collection<Object> getroomswithmessages(Long loginCredentialid);
	StringBuilder getchatroombyid(String chatroom_id, String string, Long long1, String string2, String partner_id);
	Boolean postMessage1(MessageParam messageParam);
	Boolean deletechatmessagesbyid(String chatroom_id);
	Boolean savechatmessages(String message, String author, String chat_room);
	String updatetodraft(MessageParam messageParam);
	
	
}
