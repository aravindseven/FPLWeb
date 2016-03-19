package com.fpl.controller.profile.customer;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.login.UserLoginInfo;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.customer.CustomerPV;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.util.DateUtil;

public class InitCustomerProfileCreationController extends FPLAbstractController {

	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView(FPLPageName.PROFILE.getPageName());
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		final CustomerPV customerPV = new CustomerPV();
		customerPV.setValidationDate(DateUtil.getFormattedDate(new Date(), "dd/MM/yyyy"));
		customerPV.setState(ProfileState.DEACTIVE);
		customerPV.setStatus(ProfileStatus.PENDING);
		final PersonalDataPV personalDataPV = new PersonalDataPV();
		personalDataPV.setEmail(loginInfo.getEmail());
		personalDataPV.setMobile(loginInfo.getMobileNumber());
		customerPV.setPrimaryPersonalData(personalDataPV);
		model.addObject("customerParam", customerPV);
		return model;
	}
}
