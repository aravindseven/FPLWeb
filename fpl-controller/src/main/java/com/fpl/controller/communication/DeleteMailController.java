package com.fpl.controller.communication;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.util.JsonUtil;

public class DeleteMailController extends AjaxBaseController {

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
			inbox.open(Folder.READ_WRITE);
			final String messageNumber = request.getParameter("messageNumber");
			final Message message = inbox.getMessage(Integer.parseInt(messageNumber));
			message.setFlag(Flags.Flag.DELETED, true);
			result.put("status", "success");
			result.put("reason", "Mail Deleted");
			resMap.put("res", JsonUtil.convertJSONSingleStr(result));
		} catch(final Exception e){
			e.printStackTrace();
			result.put("status", "failure");
			result.put("reason", e.getMessage());
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

}
