package com.fpl.core.communication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.fpl.core.tips.AdminTips;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.login.UserType;
import com.fpl.persistence.communication.ICommunicationQueryDAO;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.profile.customer.Customer;

public class CommunicationManager implements ICommunicationManager {

	private ICommunicationQueryDAO communicationQueryDAO;
	private IFinancialPlannerDAO financialPlannerDAO;
	private ICustomerDAO customerDAO;
	
	@Override
	public Collection<UserInfo> getUserList(final UserSearchParam searchParam) {
		final List<UserInfo> userInfos = communicationQueryDAO.getUserList(searchParam);
		for(final ListIterator<UserInfo> iterator = userInfos.listIterator(); iterator.hasNext();) {
			final UserInfo userInfo = iterator.next();
			if(userInfo.getUserType().getUser().startsWith("cust")) {
				final Customer customer =  customerDAO.getCustomerLoginId(userInfo.getLoginId());
				if(customer != null) {
					userInfo.setFirstName(customer.getPersonalData().getName());
					userInfo.setLastName(customer.getPersonalData().getLastNameOrRNumber());
					final String userName = customer.getPersonalData().getName()+" "+customer.getPersonalData().getLastNameOrRNumber();
					userInfo.setUser(userName);
				} else {
					iterator.remove();
				}
			} else {
				final FinancialPlanner financialPlanner = financialPlannerDAO.getFinancialPlannerByLoginId(userInfo.getLoginId());
				if(financialPlanner != null) {
					userInfo.setFirstName(financialPlanner.getPersonalData().getName());
					userInfo.setLastName(financialPlanner.getPersonalData().getLastNameOrRNumber());
					userInfo.setUser(financialPlanner.getPersonalData().getName());
				} else {
					iterator.remove();
				}
			}
		}
		return userInfos;
	}

	/**
	 * @param communicationQueryDAO the communicationQueryDAO to set
	 */
	public void setCommunicationQueryDAO(final ICommunicationQueryDAO communicationQueryDAO) {
		this.communicationQueryDAO = communicationQueryDAO;
	}

	/**
	 * @param financialPlannerDAO the financialPlannerDAO to set
	 */
	public void setFinancialPlannerDAO(final IFinancialPlannerDAO financialPlannerDAO) {
		this.financialPlannerDAO = financialPlannerDAO;
	}

	/**
	 * @param customerDAO the customerDAO to set
	 */
	public void setCustomerDAO(final ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public Boolean postMessage(MessageParam messageParam) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.postMessage(messageParam);
	}
	
	@Override
	public Object getInbox(Long userId,int pageno,String filter) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.getInbox(userId,pageno,filter);
	}
	
	@Override
	public Object getTrashbox(Long userId,int pageno,String filter) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.getTrashbox(userId,pageno,filter);
	}
	@Override
	public Object getSentMail(Long userId,int pageno,String filter) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.getSentMail(userId,pageno,filter);
	}

	@Override
	public Boolean movetotrash(String date, String mids,String userid,String from) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.movetotrash(date,mids,userid,from);
	}
	
	@Override
	public Boolean undofromtrash(String date, String mids,String userid,String from) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.undofromtrash(date,mids,userid,from);
	}

	@Override
	public String savetodraft(MessageParam messageParam) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.savetodraft(messageParam);
	}

	@Override
	public Object getDraft(Long loginCredentialid,int pageno,String filter) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.getDraft(loginCredentialid,pageno, filter);
		
		
	}

	@Override
	public Boolean ChangeReaddate(Long loginCredentialid, String msgid) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.ChangeReaddate(loginCredentialid,msgid);
	}

	@Override
	public Boolean Discardfromdraft(String msgid, String userid) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.Discardfromdraft(msgid,userid);
	}

	@Override
	public Boolean Settings(Usersettings settingsParam) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.Settings(settingsParam);
	}
	
	//tips
	@Override
	public Boolean SaveTips(AdminTips admin_tips) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.SaveTips(admin_tips);
	}

	@Override
	public Boolean movetoarchive(String date, String mids, String userid, String from) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.movetoarchive(date,mids,userid,from);
	}

	@Override
	public Object getArchive(Long loginCredentialid,int pageno,String filter) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.getArchive(loginCredentialid,pageno,filter);
	}

	
	@Override
	public Object getSettings(Long loginCredentialid) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.getSettings(loginCredentialid);
	}

	@Override
	public String ChangeStarRating(long userid, String msgid, String rating,String box) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.ChangeStarRating(userid,msgid,rating,box);
	}

	@Override
	public Boolean reply(String msgid, String from, MessageParam messageParam) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.reply(msgid, from, messageParam);
	}

	@Override
	public Object GetTips() {
		// TODO Auto-generated method stub
		return communicationQueryDAO.GetTips();
	}

	@Override
	public Object GetTipsbyid(String tip_name) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.GetTipsbyid(tip_name);
	}

	@Override
	public Boolean updatetips(AdminTips admin_tips) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.updatetips(admin_tips);	}

	@Override
	public Boolean deletetips(String tip_name) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.deletetips(tip_name);
	}
	
	
	@Override
	public Boolean createchatrooms(String roomname,String from_user,String to_user) {
		// TODO Auto-generated method stub
		System.out.println("Inside Service");
		return communicationQueryDAO.createchatrooms(roomname,from_user,to_user);
	}

	
	@Override
	public Collection<String> getrooms(long userid) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.getrooms(userid);
	}
	/*@Override
	public boolean setrooms(Collection<String>rooms) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.setrooms(rooms);
	}

	@Override
	public Boolean Storechatmsgs(String msgs,String chatroom,long user) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.Storechatmsgs(msgs,chatroom,user);
	}

	@Override
	public Boolean Readstatus(String status,long loginid) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.Readstatus(status,loginid);
	}

	@Override
	public String getReadstatus(String readstatus) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.getReadstatus(readstatus);
	}

	@Override
	public Boolean loginregister(Long loginCredentialid) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.loginregister(loginCredentialid);
	}

	@Override
	public Collection<String> getusers() {
		// TODO Auto-generated method stub
		return communicationQueryDAO.getusers();
	}*/
	
	@Override
	public Object getsearchmail(Long loginCredentialid, String search, String filter, String box,ArrayList rec_id) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.getsearchmail(loginCredentialid,search,filter,box,rec_id);
	}
	
	@Override
	public Object getsortfrom(Long loginCredentialid, String search, String filter, String box,ArrayList rec_id) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.getsortfrom(loginCredentialid,search,filter,box,rec_id);
	}

	@Override
	public Boolean savechatmessages(String message, String author,String chat_room) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.savechatmessages(message,author,chat_room);
	}

	@Override
	public Collection<Object> getroomswithmessages(Long loginCredentialid) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.getroomswithmessages(loginCredentialid);
	}

	@Override
	public StringBuilder getchatroombyid(String chatroom_id, String string, Long long1, String string2, String partner_id) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.getchatroombyid(chatroom_id,string,long1,string2,partner_id);
	}

	@Override
	public Boolean postMessage1(MessageParam messageParam) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.postMessage1(messageParam);
	}

	@Override
	public Boolean deletechatmessagesbyid(String chatroom_id) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.deletechatmessagesbyid(chatroom_id);
	}

	@Override
	public Object getNotify(Long loginCredentialid, int parseInt, String filter) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.getNotify(loginCredentialid,parseInt,filter);
	}

	@Override
	public String updatetodraft(MessageParam messageParam) {
		// TODO Auto-generated method stub
		return communicationQueryDAO.updatetodraft(messageParam);
	}
	
	
}
