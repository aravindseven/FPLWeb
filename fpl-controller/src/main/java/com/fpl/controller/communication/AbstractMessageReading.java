package com.fpl.controller.communication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.MailView;
import com.fpl.util.DateUtil;
import com.fpl.util.JsonUtil;

public class AbstractMessageReading {

	public ModelAndView executeInternal(final HttpServletRequest request, final HttpServletResponse response, final String folderName) {
		/* Set the mail properties */
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map result = new HashMap();
		final Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		Session session = null;
		Store store = null;
		Folder folder = null;
		try {
			/* Create the session and get the store for read the mail. */
			session = Session.getDefaultInstance(props, null);
			store = session.getStore("imaps");
			final String mailId = "murali4professional@gmail.com";
			final String password = "murali1984";
			store.connect("imap.gmail.com", mailId, password);

			/* Mention the folder name which you want to read. */
			folder = store.getFolder(folderName);
			System.out.println("No of Unread Messages : "+ folder.getUnreadMessageCount());
			
			/* Open the inbox using store. */
			folder.open(Folder.READ_ONLY);

			/* Get the messages which is unread in the Inbox */
			final Message messages[] = folder.search(new FlagTerm(new Flags(), true));
			/* Use a suitable FetchProfile */
			final FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);
			//fp.add(FetchProfile.Item.CONTENT_INFO);
			folder.fetch(messages, fp);

			final List<MailView> list = new ArrayList<MailView>();
			for(final Message message : messages) {
				final MailView inboxInfo = new MailView();
				inboxInfo.setSubject(message.getSubject());
				inboxInfo.setRecivedDate(DateUtil.getFormattedDate(message.getReceivedDate()));
				inboxInfo.setMessageNumber(message.getMessageNumber());
				if (message.getFrom() != null) {
					for (final Address address : message.getFrom()) {
						inboxInfo.setFromAddress(address.toString());
					}
				}
				list.add(inboxInfo);
			}
			result.put("status", "success");
			result.put("Output", list);
			resMap.put("res", JsonUtil.toJsonString(result));
		} catch (final Exception e) {
			result.put("status", "failure");
			result.put("Output", e.getMessage());
			resMap.put("res", JsonUtil.toJsonString(result));
		} finally {
			try {
				if(folder != null) {
					folder.close(true);
				}
			} catch (final MessagingException e) {
				e.printStackTrace();
			}
			try {
				if(store != null && store.isConnected()) {
					store.close();
				}
			} catch (final MessagingException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("fplajax_login",resMap);
	}
}
