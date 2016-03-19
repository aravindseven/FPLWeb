package com.fpl.controller.profile.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;

public class InitSearchProfileController extends FPLAbstractController {

	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request,final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView(FPLPageName.ADMIN_MANAGE_PROFILE.getPageName());
		return model;
	}
}
