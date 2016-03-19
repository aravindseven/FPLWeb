package com.fpl.aop;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fpl.login.UserLoginInfo;
import com.fpl.login.entity.LoginSupport;
import com.fpl.persistence.login.ILoginSupportDAO;

public class HttpSessionManager implements HttpSessionListener {
	
	private ILoginSupportDAO loginSupportDAO;
	
	@Override
	public void sessionCreated(final HttpSessionEvent sessionEvent) {
		System.out.printf("Session ID %s created at %s%n", sessionEvent.getSession().getId(), new Date());
		if(loginSupportDAO == null) {
			registerLoginSupportDAO(sessionEvent);
		}
	}

	@Override
	public void sessionDestroyed(final HttpSessionEvent sessionEvent) {
		System.out.println("session destroy event"+sessionEvent.getSession().getId());
		System.out.printf("Session ID %s destroyed at %s%n", sessionEvent.getSession().getId(), new Date());
		final UserLoginInfo loginInfo = (UserLoginInfo) sessionEvent.getSession().getAttribute("UserLoginInfo");
		if(loginInfo != null) {
			final LoginSupport loginSupport = loginSupportDAO.getByLoginCredentialId(loginInfo.getLoginCredentialid());
			loginSupport.setOnline(Integer.valueOf(1));
			loginSupport.setBrowser_Active("false"); 
			loginSupportDAO.update(loginSupport, true);
		}
		sessionEvent.getSession().invalidate();
		System.out.println("session destroy event"+sessionEvent.getSession().getId());
	}
	
	private void registerLoginSupportDAO(final HttpSessionEvent event) {
		final HttpSession session = event.getSession();
		final WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		this.loginSupportDAO = (ILoginSupportDAO) applicationContext.getBean("fpl.login.loginSupportDAO");
	}
}
