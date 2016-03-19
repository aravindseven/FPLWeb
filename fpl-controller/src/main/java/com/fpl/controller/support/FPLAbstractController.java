package com.fpl.controller.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.fpl.login.UserLoginInfo;

public abstract class FPLAbstractController extends AbstractController {

	protected final static String ERROR_MESSAGE = "ErrorMessage";
	protected final static String SUCCESS_MESSAGE = "SuccessMessage";
	protected final static UserSessionKey USER_LOGIN_KEY = UserSessionKey.USER_LOGIN_INFO;
	
	protected abstract ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response);
	
	@Override
	protected ModelAndView handleRequestInternal(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		ModelAndView modelAndView;
		try {
			modelAndView = executeRequest(request, response);
			final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
			modelAndView.addObject(USER_LOGIN_KEY.getKey(), loginInfo);
		} catch (final Exception e) {
			e.printStackTrace();
			modelAndView = new ModelAndView(FPLPageName.PASSWORD_RESET2.getPageName());
			modelAndView.addObject("ErrorMessage", e.getMessage());
		}
		return modelAndView;
	}

	protected <SessionValue> void put(final HttpServletRequest request, final UserSessionKey key, final SessionValue val) {
		request.getSession().setAttribute(key.getKey(), val);
	}

	protected void clearAll(final HttpServletRequest request){
		final UserSessionKey[] sessionKeys = UserSessionKey.values();
		final HttpSession session = request.getSession();
		for(final UserSessionKey key : sessionKeys) {
			session.removeAttribute(key.getKey());
		}
	}
	
	protected <SessionValue> SessionValue get(final HttpServletRequest request, final UserSessionKey key){
		final Object result = request.getSession().getAttribute(key.getKey());
		return (SessionValue)result;
	}
	
	protected void remove(final HttpServletRequest request, final UserSessionKey key){
		request.getSession().removeAttribute(key.getKey());
	}
}
