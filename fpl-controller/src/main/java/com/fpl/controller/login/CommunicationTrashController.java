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
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
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

public class CommunicationTrashController extends AjaxBaseController {

	Folder trash;

	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request,
			HttpServletResponse response) {
		final Map<String, String> resMap = new HashMap<String, String>();
		Properties props = new Properties();

		props.setProperty("mail.store.protocol", "imaps");
		MailView inboxInfo = null;
		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect("imap.gmail.com", "yourMailid@gmail.com",
					"password");
			Folder[] folderList = store.getFolder("[Gmail]").list();
			for (int i = 0; i < folderList.length; i++) {
				System.out.println(folderList[i].getFullName());
			}
			List<MailView> list = new ArrayList<MailView>();
			trash = store.getFolder("[Gmail]/Trash");
			trash.open(Folder.READ_ONLY);

			Message messages[] = trash
					.search(new FlagTerm(new Flags(), true));

			Address[] a;
			for (Message msg : messages) {

				inboxInfo = new MailView();
				if ((a = msg.getRecipients(Message.RecipientType.TO)) != null) {
					for (int j = 0; j < a.length; j++) {
						inboxInfo.setFromAddress(a[j].toString());
					}
				}
				Object content = msg.getContent();
				if (content instanceof String) {
					String mp = (String) content;
					inboxInfo.setContent(mp);

				} else if (content instanceof Multipart) {
					Multipart mp = (Multipart) content;
					int count = mp.getCount();
					for (int i = 0; i < count; i++) {
						String part = dumpPart(mp.getBodyPart(i));
						inboxInfo.setContent(part);
					}
				}
				list.add(inboxInfo);
			}
			try {
				final FPLCommunicationResponse serviceResponse = communicationResponse(
						list, request);
				resMap.put("res", JsonUtil.toJsonString(serviceResponse));
				trash.close(true);
				store.close();
			} catch (Exception ex) {
				System.out.println("Exception arise at the time of read mail");
				ex.printStackTrace();
			}
		} catch (Exception mex) {
			mex.printStackTrace();
		}

		return new ModelAndView("fplajax_login", resMap);
	}

	public FPLCommunicationResponse communicationResponse(
			List<MailView> inboxInfo, HttpServletRequest request) {
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
		InputStream is = p.getInputStream();
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
