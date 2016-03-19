package com.fpl.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.controller.support.RequestToPageViewTransfer;
import com.fpl.login.LoginCredentialServices;
import com.fpl.login.PasswordResetParam;

public class PasswordResetController extends FPLAbstractController {

	private LoginCredentialServices loginServices;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		ModelAndView model = null;
		final PasswordResetParam passReset = RequestToPageViewTransfer.getPageView(PasswordResetParam.class, request);
		System.out.println(passReset);
		final FPLServiceResponse serviceResponse = loginServices.resetPassword(passReset);
		if(serviceResponse.getStatus().equals("failure")) {
			model = new ModelAndView(FPLPageName.PASSWORD_RESET2.getPageName());
			model.addObject("passwordRestParam", passReset);
			model.addObject(ERROR_MESSAGE, serviceResponse.getReason());
		} else {
			model = new ModelAndView(FPLPageName.PASSWORD_RESET2.getPageName());
			model.addObject(SUCCESS_MESSAGE, serviceResponse.getReason());
		}
		return model;
	}
	
	/**
	 * @param loginServices the loginServices to set
	 */
	public void setLoginServices(final LoginCredentialServices loginServices) {
		this.loginServices = loginServices;
	}
}
