package com.fpl.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.login.LoginCredentialServices;

public class ActivateUserController extends FPLAbstractController {
	
	private LoginCredentialServices loginServices;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		ModelAndView model = new ModelAndView(FPLPageName.FIRST_TIME_PASSWORD_RESET.getPageName());		
		final String encryptedUserOtp = request.getParameter("uoid");
		final String password = request.getParameter("newPassword");
		final String retype = request.getParameter("confirmPassword");
		System.out.println("pass"+password+"confpass"+retype);
		FPLServiceResponse serviceResponse = null;
		try {
			if((password!=null && password.trim().length()>0) && (retype!=null && retype.trim().length()>0)){
				if(password.trim().equals(retype.trim())){
					final String otp = Encryption.decrypt((String)request.getSession().getAttribute("ACTIVATE_USER_OTP"), 
								CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
					loginServices.activateUser(otp, password);
					model = new ModelAndView(FPLPageName.INDEX.getPageName());
					return model;
				}
			}else{
				request.getSession().setAttribute("ACTIVATE_USER_OTP", encryptedUserOtp);
				model = new ModelAndView(FPLPageName.FIRST_TIME_PASSWORD_RESET.getPageName());
				return model;
			}
		} catch (final Exception e) {
			e.printStackTrace();
			serviceResponse = new FPLServiceResponse();
			serviceResponse.setReason(e.getMessage());
			serviceResponse.setStatus("failure");
		}
		final String status = "failure".equalsIgnoreCase(serviceResponse.getStatus()) ? ERROR_MESSAGE : SUCCESS_MESSAGE;
		model.addObject(status, serviceResponse.getReason());
		return model;
	};

	/**
	 * @param loginServices the loginServices to set
	 */
	public final void setLoginServices(final LoginCredentialServices loginServices) {
		this.loginServices = loginServices;
	}
}
