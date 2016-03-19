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
import com.fpl.login.UserLoginInfo;

public class SecondaryEmailVerifierController extends FPLAbstractController {
	
	private LoginCredentialServices loginServices;

	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView(FPLPageName.INDEX.getPageName());
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final String encryptedUserOtp = request.getParameter("uoid");
		System.out.println(encryptedUserOtp);
		FPLServiceResponse serviceResponse;
		try {
			final String otp = Encryption.decrypt(encryptedUserOtp, CryptFixedKeyType.DEFAULT_KEY.getKeyIdentifier());
			System.out.println(otp);
			serviceResponse = loginServices.verifySecondaryEmail(otp);
			if("success".equals(serviceResponse.getStatus())) {
				loginInfo.setSecEmailVerified(true);
			}
			model.addObject("reason", serviceResponse.getReason());
		} catch (final Exception e) {
			e.printStackTrace();
			model.addObject("reason", e.getMessage());
		}
		return model;
	}
	
	/**
	 * @param loginServices the loginServices to set
	 */
	public final void setLoginServices(final LoginCredentialServices loginServices) {
		this.loginServices = loginServices;
	}

}
