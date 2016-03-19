package com.fpl.controller.profile.company;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.profile.company.ICompanyFinder;
import com.fpl.profile.company.CompanyPV;
import com.fpl.util.JsonUtil;

public class GetCompanyProfileController extends AjaxBaseController {
	
	@Autowired
	private ICompanyFinder companyFinder;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map result =  new HashMap();
		try {
			final String location = request.getParameter("location");
			final CompanyPV companyPV = companyFinder.getCompany(location);
			result.put("Status", "Success");
			result.put("Output", companyPV);
		} catch (final Exception e) {
			result.put("Status", "Failure");
			result.put("Output", e.getMessage());
			e.printStackTrace();
		}
		resMap.put("res", JsonUtil.convertJSONSingleStr(result));
		return new ModelAndView("fplajax_CompanyProfile",resMap);
	}
}
