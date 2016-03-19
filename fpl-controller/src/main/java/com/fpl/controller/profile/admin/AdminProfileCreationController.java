package com.fpl.controller.profile.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.controller.support.RequestToPageViewTransfer;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.admin.AdminProfilePV;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.services.profile.IAdminRegisterService;

public class AdminProfileCreationController extends FPLAbstractController {
	
	private IAdminRegisterService registerService;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		ModelAndView model = null;
		final AdminProfilePV adminProfilePV = new AdminProfilePV();
		final PersonalDataPV personalData = RequestToPageViewTransfer.getPageView(PersonalDataPV.class, request);
		adminProfilePV.setPersonalDataPV(personalData);
		final AddressPV addressPV = RequestToPageViewTransfer.getPageView(AddressPV.class, request);
		adminProfilePV.setAddressPV(addressPV);
		final FPLServiceResponse serviceResponse = registerService.insertAdminProfile(adminProfilePV);
		if(serviceResponse.getStatus().equals("failure")) {
			model = new ModelAndView(FPLPageName.PROFILE.getPageName());
			model.addObject("adminParam", adminProfilePV);
			model.addObject(ERROR_MESSAGE, serviceResponse.getReason());
		} else {
			model = new ModelAndView(FPLPageName.PROFILESETTINGS.getPageName());
			model.addObject(SUCCESS_MESSAGE, serviceResponse.getReason());
		}
		return model;
	}

	/**
	 * @param registerService the registerService to set
	 */
	public void setRegisterService(final IAdminRegisterService registerService) {
		this.registerService = registerService;
	}
}
 