package com.fpl.controller.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.login.CommunicationInfo;
import com.fpl.util.JsonUtil;

public class CommunicationMessageController extends AjaxBaseController {

	final ResourceBundle resourceBundle = ResourceBundle.getBundle("com.fpl.config.communicationmessage");
	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request,
			HttpServletResponse response) {
		
		final Map<String, String> resMap = new HashMap<String, String>();
		final String newMessageJson = request
				.getParameter("communicationMessage");
		System.out.println(newMessageJson);
		final CommunicationInfo info = JsonUtil.convertPojo(newMessageJson,
				CommunicationInfo.class);
		System.out.println(info.getContent() + " " + info.getRecipient() + " "
				+ info.getSubject());

		final String username = resourceBundle.getString("COMMUNICATION_MESSAGE_MAIL_ID");
		final String password = resourceBundle.getString("COMMUNICATION_MESSAGE_PASSWORD");

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", resourceBundle.getString("COMMUNICATION_MESSAGE_HOST"));
		props.put("mail.smtp.port", resourceBundle.getString("COMMUNICATION_MESSAGE_PORT"));

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(resourceBundle.getString("COMMUNICATION_MESSAGE_MAIL_ID")));

			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(info.getRecipient()));
			message.setSubject(info.getSubject());
			message.setText(info.getContent());

			Transport.send(message);
			final FPLServiceResponse serviceResponse = communicationResponse(request);
			System.out.println(JsonUtil.toJsonString(serviceResponse));
			resMap.put("res", JsonUtil.toJsonString(serviceResponse));
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		return new ModelAndView("fplajax_login", resMap);
	}

	public FPLServiceResponse communicationResponse(HttpServletRequest request) {
		final FPLServiceResponse response = new FPLServiceResponse();
		response.setStatus("success");
		try {
			response.setReason(resourceBundle.getString("COMMUNICATION_MESSAGE_RECEIVED"));
		} catch (final Exception e) {
			e.printStackTrace();
			response.setStatus("failure");
			response.setReason(e.getMessage());
		}
		return response;
	}
}
