package com.fpl.controller.login;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.StringWriter;
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
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLCommunicationResponse;
import com.fpl.common.MailView;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.util.JsonUtil;

public class CommunicationInboxMessageController extends AjaxBaseController {
	Folder inbox;

	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request,
			HttpServletResponse response) {
		/* Set the mail properties */
		final Map<String, String> resMap =  new HashMap<String, String>();
		//List<String> resList = new ArrayList<String>();
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		MailView inboxInfo = null;
		try {
			/* Create the session and get the store for read the mail. */
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "mailId",
					"Password");

			/* Mention the folder name which you want to read. */
			inbox = store.getFolder("Inbox");
			System.out.println("No of Unread Messages : "
					+ inbox.getUnreadMessageCount());
			
			
			/* Open the inbox using store. */
			inbox.open(Folder.READ_ONLY);

			/* Get the messages which is unread in the Inbox */
			Message messages[] = inbox.search(new FlagTerm(
					new Flags(), true));

			Address[] a;
			List<MailView> list = new ArrayList<MailView>();
			for (Message message : messages) {
				inboxInfo = new MailView();
				if ((a = message.getFrom()) != null) {
					for (int j = 0; j < a.length; j++) {
						inboxInfo.setFromAddress(a[j].toString());
					}
				}
				Multipart mp = (Multipart) message.getContent();
				int count = mp.getCount();
				for(int i = 0; i < count; i++) {
					String part = dumpPart(mp.getBodyPart(i));
					inboxInfo.setContent(part);
				}
				list.add(inboxInfo);
			}
			/*for (InboxInfo inboxInfo : list) {
				System.out.println(inboxInfo);
			}*/
			/* Use a suitable FetchProfile */
			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);
			fp.add(FetchProfile.Item.CONTENT_INFO);
			inbox.fetch(messages, fp);

			try {
				final FPLCommunicationResponse serviceResponse = communicationResponse(list,request);
				//resList.add(JsonUtil.toJsonString(serviceResponse));
				resMap.put("res", JsonUtil.toJsonString(serviceResponse));
				//printAllMessages(messages);
				inbox.close(true);
				store.close();
			} catch (Exception ex) {
				System.out.println("Exception arise at the time of read mail");
				ex.printStackTrace();
			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(3);
		}
		return new ModelAndView("fplajax_login",resMap);
	}
	
	public FPLCommunicationResponse communicationResponse(List<MailView> inboxInfo,HttpServletRequest request) {
		final FPLCommunicationResponse response = new FPLCommunicationResponse();
		response.setStatus("success");
		try {
			response.setReason("your message sent successfully");
			response.setInboxInfo(inboxInfo);
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}

	public String dumpPart(Part p) throws Exception {
		// Dump input stream ..
		InputStream is = p.getInputStream();
		// If "is" is not already buffered, wrap a BufferedInputStream
		// around it.
		if (!(is instanceof BufferedInputStream)) {
			is = new BufferedInputStream(is);
		}
		int c;
		StringWriter sw = new StringWriter();
		while ((c = is.read()) != -1) {
			sw.write(c);
			
		}
		System.out.println(sw.toString());
		return sw.toString();
	}
}
