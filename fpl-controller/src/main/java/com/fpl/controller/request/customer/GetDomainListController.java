package com.fpl.controller.request.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.domain.Domain;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.util.JsonUtil;

public class GetDomainListController extends AjaxBaseController {
	
	private IDomainDAO domainDAO;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Collection<Domain> domains =  domainDAO.getAllEntities();
		resMap.put("res", JsonUtil.toJsonString(domains));
		return new ModelAndView("fplajax_domain", resMap);
	}

	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}
}
