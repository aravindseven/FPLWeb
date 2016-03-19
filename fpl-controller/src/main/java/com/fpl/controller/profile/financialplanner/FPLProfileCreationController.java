package com.fpl.controller.profile.financialplanner;

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
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.services.profile.IFPLRegisterService;

public class FPLProfileCreationController extends FPLAbstractController {
	
	private IFPLRegisterService registerService;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		ModelAndView model = null;
		final FinancialPlannerPV financialPlannerPV = RequestToPageViewTransfer.getPageView(FinancialPlannerPV.class, request);
		final PersonalDataPV personalData = RequestToPageViewTransfer.getPageView(PersonalDataPV.class, request);
		financialPlannerPV.setPersonalDataPV(personalData);
		final AddressPV addressPV = RequestToPageViewTransfer.getPageView(AddressPV.class, request);
		financialPlannerPV.setAddressPV(addressPV);
		final AddressPV permanentAddressPV = RequestToPageViewTransfer.getPageView(AddressPV.class, request, "secondary");
		financialPlannerPV.setPermanentAddressPV(permanentAddressPV);
		financialPlannerPV.setState(ProfileState.DEACTIVE.getValue());
		financialPlannerPV.setStatus(ProfileStatus.PENDING.getStatus());
		final FPLServiceResponse serviceResponse = registerService.insertFinancialPlanner(financialPlannerPV);
		if(serviceResponse.getStatus().equals("failure")) {
			model = new ModelAndView(FPLPageName.PROFILE.getPageName());
			model.addObject("financialPlannerParam", financialPlannerPV);
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
	public void setRegisterService(final IFPLRegisterService registerService) {
		this.registerService = registerService;
	}
}
