package com.fpl.controller.profile.company;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.common.FPLServiceResponse;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.profile.ProfileStatus;
import com.fpl.profile.company.CompanyPV;
import com.fpl.services.profile.ICompanyRegisterService;
import com.fpl.util.JsonUtil;

public class CompanyProfileCreationController extends AjaxBaseController {
	
	private ICompanyRegisterService registerService;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final String companyJson = request.getParameter("companyJson");
		System.out.println(companyJson);
		final CompanyPV companyPV = JsonUtil.convertPojo(companyJson, CompanyPV.class);
		companyPV.setStatus(ProfileStatus.PENDING.getStatus());
		final FPLServiceResponse serviceResponse = registerService.insertCompany(companyPV);
		resMap.put("res", JsonUtil.toJsonString(serviceResponse));
		return new ModelAndView("fplajax_CompanyProfileCreation", resMap);
	}
	
	/**
	 * @param registerService the registerService to set
	 */
	public void setRegisterService(final ICompanyRegisterService registerService) {
		this.registerService = registerService;
	}
}
