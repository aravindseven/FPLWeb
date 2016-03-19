package com.fpl.controller.admin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.country.Country;
import com.fpl.persistence.country.ICountryDAO;
import com.fpl.util.JsonUtil;

public class GetCountryProfileController extends AjaxBaseController {
	
	@Autowired
	@Qualifier("fpl.subscrption.CountryDAO")
	private ICountryDAO countryDAO;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Collection<Country> countries = countryDAO.getAllEntities();
		resMap.put("res", JsonUtil.convertJSONSingleStr(countries));
		return new ModelAndView("fplajax_GetCountryProfile",resMap);
	}
}
