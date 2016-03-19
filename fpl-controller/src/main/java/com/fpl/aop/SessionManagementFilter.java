package com.fpl.aop;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.fpl.controller.support.FPLPageName;

public class SessionManagementFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
		System.out.println("SessionManagementFilter --> doFilter ");
		if(request.getSession(false) == null /*||  request.getSession(false).getAttribute("UserLoginInfo") == null*/) {
			request.setAttribute("ErrorMessage", "Your session has been expired!!");
			response.sendRedirect(FPLPageName.ERROR.getPageName());
		} else {
			filterChain.doFilter(request, response);
		}
	}
}
