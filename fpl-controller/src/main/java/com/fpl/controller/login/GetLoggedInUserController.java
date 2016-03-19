package com.fpl.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;

public class GetLoggedInUserController extends AjaxBaseController {

	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		resMap.put("res", "[{\"user\":\"Venkatesan Elangovan\", \"id\": \"1\", \"email\": \"venkatesan.e@fuzzy15s.com\",\"status\":\"online\"},{\"user\":\"Suresh Kumar\", \"email\": \"venkatesan.e@yaazhsoft.com\",\"status\":\"online\", \"id\": \"2\"}]");
		return new ModelAndView("fplajax_login", resMap);
	}
}
