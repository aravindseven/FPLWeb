package com.fpl.controller.support;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.fpl.login.UserLoginInfo;

/**
 * @author Yaazhsoft
 *
 */
public abstract class AjaxBaseController implements Controller {
	
	private static final Log log = LogFactory.getLog(AjaxBaseController.class);
	protected final static UserSessionKey USER_LOGIN_KEY = UserSessionKey.USER_LOGIN_INFO;
	 private static Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
	public <SessionValue> void put(final HttpServletRequest request, final UserSessionKey key, final SessionValue val){
		System.out.println("putsessionvalue"+"key"+key.getKey()+"value"+val);
		//request.getSession().setMaxInactiveInterval(5);
		
		request.getSession().setAttribute(key.getKey(), val);
		map.put(request.getSession().getId(), request.getSession());
	}
	
	public <SessionValue> SessionValue get(final HttpServletRequest request, final UserSessionKey key){
		log.debug("Getting Session Value for ==>"+key);
		final Object result = request.getSession().getAttribute(key.getKey());
		System.out.println("getsessionvalue"+"key"+key.getKey());
		System.out.println("getsessionvalue"+"key"+request.getSession());
		log.debug("Result ==>"+result);
		
		return (SessionValue)result;
	}
	
	public void remove(final HttpServletRequest request, final UserSessionKey key){
		System.out.println("expired called in ajaxbase remove()");
		
		if(map.get(request.getSession().getId())!=null){ 
			map.remove(request.getSession().getId());
			}
		request.getSession().removeAttribute(key.getKey());
	}
	public void remove(final String sessionid, final UserSessionKey key){
		System.out.println("expired called in ajaxbase remove()2");
		System.out.println("called1S");
		if(map.get(sessionid)!=null){ 
		HttpSession sess=map.get(sessionid);
		final UserSessionKey[] sessionKeys = UserSessionKey.values();
		for(final UserSessionKey keys : sessionKeys) {
			sess.removeAttribute(keys.getKey());
		}
		}
	}
	protected void clearAll(final HttpServletRequest request){
		System.out.println("expired called in ajaxbase clearAll()");
		final UserSessionKey[] sessionKeys = UserSessionKey.values();
		final HttpSession session = request.getSession();
		for(final UserSessionKey key : sessionKeys) {
			session.removeAttribute(key.getKey());
		}
		
		if(map.get(request.getSession().getId())!=null){ 
			map.remove(request.getSession().getId());
			}
	}
	
	public abstract ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response);
	
	@Override
	public final ModelAndView handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final ModelAndView modelAndView = executeAjaxCall(request,response);
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		if(loginInfo != null) {
			modelAndView.addObject(USER_LOGIN_KEY.getKey(), loginInfo);
		}
		return modelAndView;
	}
}
