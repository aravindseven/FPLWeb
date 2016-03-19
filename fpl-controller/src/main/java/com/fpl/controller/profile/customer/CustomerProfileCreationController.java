package com.fpl.controller.profile.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.controller.support.RequestToPageViewTransfer;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.customer.CustomerPV;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.services.profile.ICustomerRegisterService;

public class CustomerProfileCreationController extends FPLAbstractController {
	
	private ICustomerRegisterService registerService;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		ModelAndView model = null;
		final CustomerPV customerPV = new CustomerPV();
		final PersonalDataPV primaryPersonalData = RequestToPageViewTransfer.getPageView(PersonalDataPV.class, request, "primary");
		customerPV.setPrimaryPersonalData(primaryPersonalData);
		final PersonalDataPV seconPersonalData = RequestToPageViewTransfer.getPageView(PersonalDataPV.class, request, "secondary");
		customerPV.setSecondaryPersonalData(seconPersonalData);
		final AddressPV addressPV = RequestToPageViewTransfer.getPageView(AddressPV.class, request, "primary");
		final AddressPV permanentAddressPV = RequestToPageViewTransfer.getPageView(AddressPV.class, request, "secondary");
		customerPV.setAddressPV(addressPV);
		customerPV.setPermanentAddressPV(permanentAddressPV);
		customerPV.setState(ProfileState.DEACTIVE);
		customerPV.setStatus(ProfileStatus.PENDING);
		final FPLServiceResponse serviceResponse = registerService.insertCustomer(customerPV);
		if(serviceResponse.getStatus().equals("failure")) {
			model = new ModelAndView(FPLPageName.PROFILE.getPageName());
			model.addObject("customerParam", customerPV);
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
	public void setRegisterService(final ICustomerRegisterService registerService) {
		this.registerService = registerService;
	}
}
