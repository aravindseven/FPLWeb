package com.fpl.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationHandlerInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		System.out.println("AuthenticationHandlerInterceptor handler --> "+ handler.getClass().getSimpleName());
		System.out.println(request.getSession().getAttribute("UserLoginInfo"));
		return true;
	}
}
