package com.fpl.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.crypt.CryptFixedKeyType;
import com.fpl.crypt.Encryption;
import com.fpl.login.LoginCredentialServices;
import com.fpl.login.PasswordResetParam;

public class InitPasswordResetController extends FPLAbstractController {

	private LoginCredentialServices credentialServices;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		ModelAndView model = null;
		final String encryptedUserOtp = request.getParameter("uoid");
		final String encryptedEmail = request.getParameter("pmi");
		try {
			final String otp = Encryption.decrypt(encryptedUserOtp, CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
			final String email = Encryption.decrypt(encryptedEmail, CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
			final boolean isvalid = credentialServices.isValidOTP(otp,null);
			if(isvalid) {
				final PasswordResetParam passwordResetPV = new PasswordResetParam();
				passwordResetPV.setOtp(otp);
				passwordResetPV.setEmail(email);
				model = new ModelAndView(FPLPageName.PASSWORD_RESET2.getPageName());
				model.addObject("passwordRestParam", passwordResetPV);
				model.addObject(SUCCESS_MESSAGE, "change the password before link expires");
			} else {
				System.out.println("linkexpired");
				model = new ModelAndView(FPLPageName.PASSWORD_RESET2.getPageName());
				model.addObject(ERROR_MESSAGE, "This link has expired or invalid. Please try again");
				System.out.println("model"+model);
			}
		} catch (final Exception e) {
			model = new ModelAndView(FPLPageName.PASSWORD_RESET2.getPageName());
			model.addObject(ERROR_MESSAGE, e.getMessage());
		}
		return model;
	}

	/**
	 * @param credentialServices the credentialServices to set
	 */
	public void setCredentialServices(final LoginCredentialServices credentialServices) {
		this.credentialServices = credentialServices;
	}

}
