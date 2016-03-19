package com.fpl.controller.communication;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.MailView;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.util.DateUtil;
import com.fpl.util.JsonUtil;

public class GetMailController extends AjaxBaseController {

	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		/* Set the mail properties */
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		final Map result = new HashMap();
		Store store = null;
		Folder inbox = null;
		try {
			/* Create the session and get the store for read the mail. */
			final Session session = Session.getDefaultInstance(props, null);
			store = session.getStore("imaps");
			final String mailId = "murali4professional@gmail.com";
			final String password = "murali1984";
			store.connect("imap.gmail.com", mailId, password);

			/* Mention the folder name which you want to read. */
			final String folderName = request.getParameter("FolderName");
			inbox = store.getFolder(folderName);
			
			/* Open the inbox using store. */
			inbox.open(Folder.READ_ONLY);
			final String messageNumber = request.getParameter("messageNumber");
			final Message message = inbox.getMessage(Integer.parseInt(messageNumber));
			final MailView mailView = new MailView();
			mailView.setSubject(message.getSubject());
			mailView.setRecivedDate(DateUtil.getFormattedDate(message.getReceivedDate()));
			if (message.getFrom() != null) {
				for (final Address address : message.getFrom()) {
					mailView.setFromAddress(address.toString());
				}
			}
			final List<String> toAddress = new ArrayList<String>();
			if (message.getRecipients(RecipientType.TO) != null) {
				for (final Address address : message.getRecipients(RecipientType.TO)) {
					toAddress.add(address.toString());
				}
			}
			mailView.setToAddress(toAddress);
			final List<String> ccAddress = new ArrayList<String>();
			if (message.getRecipients(RecipientType.CC) != null) {
				for (final Address address : message.getRecipients(RecipientType.CC)) {
					ccAddress.add(address.toString());
				}
			}
			mailView.setCcAddress(ccAddress);
			final Multipart mp = (Multipart) message.getContent();
			final int count = mp.getCount();
			final StringBuilder contentbBuilder = new StringBuilder();
			for(int i = 0; i < count; i++) {
				final String part = dumpPart(mp.getBodyPart(i));
				contentbBuilder.append(part);
			}
			mailView.setContent(contentbBuilder.toString());
			result.put("status", "success");
			result.put("Output", mailView);
			resMap.put("res", JsonUtil.convertJSONSingleStr(result));
		} catch(final Exception e){
			e.printStackTrace();
			result.put("status", "failure");
			result.put("Output", e.getMessage());
			resMap.put("res", JsonUtil.convertJSONSingleStr(result));
		}  finally {
			try {
				if(inbox != null) {
					inbox.close(true);
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
	
	public String dumpPart(final Part p) throws Exception {
		InputStream is = null;
		try {
			is = p.getInputStream();
			if (!(is instanceof BufferedInputStream)) {
				is = new BufferedInputStream(is);
			}
			int c;
			final StringWriter sw = new StringWriter();
			while ((c = is.read()) != -1) {
				sw.write(c);
				
			}
			return sw.toString();
		} finally {
			is.close();
		}
	}
}
