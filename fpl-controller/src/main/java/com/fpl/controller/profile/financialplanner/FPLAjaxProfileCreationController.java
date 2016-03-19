package com.fpl.controller.profile.financialplanner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.profile.ProfileState;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.address.AddressPV;
import com.fpl.profile.financialplanner.FinancialPlannerPV;
import com.fpl.profile.personal.PersonalDataPV;
import com.fpl.services.profile.IFPLRegisterService;
import com.fpl.util.JsonUtil;
import com.fpl.util.StringUtil;

public class FPLAjaxProfileCreationController extends AjaxBaseController {
	
	private IFPLRegisterService registerService;
	
	
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String financialPlannerJson = request.getParameter("financialPlannerJson");
		System.out.println(financialPlannerJson);
		final FinancialPlannerPV financialPlannerPV = JsonUtil.convertPojo(financialPlannerJson, FinancialPlannerPV.class);
		final String personalDataJson = request.getParameter("personalDataJson");
		System.out.println(personalDataJson);
		final PersonalDataPV personalData = JsonUtil.convertPojo(personalDataJson, PersonalDataPV.class);
		financialPlannerPV.setPersonalDataPV(personalData);
		
		final String secondaryDataJson = request.getParameter("secondaryDataJson");
		System.out.println(secondaryDataJson);
		final PersonalDataPV secondaryData = JsonUtil.convertPojo(secondaryDataJson, PersonalDataPV.class);
		
		if(StringUtil.isEmpty(secondaryData.getMobile()))
		{
			secondaryData.setMobile("000");
		}
		financialPlannerPV.setSecondaryDataPV(secondaryData);
		
		
		final String addressJson = request.getParameter("addressJson");
		System.out.println(addressJson);
		final AddressPV addressPV = JsonUtil.convertPojo(addressJson, AddressPV.class);
		financialPlannerPV.setAddressPV(addressPV);
		final String permanentAddressJson = request.getParameter("permanentAddressJson");
		System.out.println(permanentAddressJson);
		final AddressPV permanentAddressPV = JsonUtil.convertPojo(permanentAddressJson, AddressPV.class);
		final String alternativeemail = request.getParameter("alternativeemailid");
		financialPlannerPV.setPermanentAddressPV(permanentAddressPV);
		financialPlannerPV.setState(ProfileState.DEACTIVE.getValue());
		financialPlannerPV.setStatus(ProfileStatus.PENDING.getStatus());
		financialPlannerPV.setDomainList(Arrays.asList(financialPlannerPV.getCoverage()));
		financialPlannerPV.setAlternativeemail(alternativeemail);
		final FPLServiceResponse serviceResponse = registerService.insertFinancialPlanner(financialPlannerPV);
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_login", resMap);
	}
	
	/**
	 * @param registerService the registerService to set
	 */
	public void setRegisterService(final IFPLRegisterService registerService) {
		this.registerService = registerService;
	}
}
