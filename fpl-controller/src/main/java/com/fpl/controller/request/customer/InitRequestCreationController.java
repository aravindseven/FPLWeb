package com.fpl.controller.request.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.controller.support.UserSessionKey;

public class InitRequestCreationController extends FPLAbstractController {
	
	//private IDomainDAO domainDAO;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView(FPLPageName.REQUEST.getPageName());
		/*final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final Collection<Domain> domainList = domainDAO.getAllEntities();
		model.addObject("DomainList", domainList);
		final NewRequestPV newRequestPV = new NewRequestPV();
		newRequestPV.setCountry(loginInfo.getCountry());
		model.addObject("NewRequestPV", newRequestPV);*/
		model.addObject("RequestDisplayCriteria", "Create");
		remove(request, UserSessionKey.NEW_REQUEST_PV);
		return model;
	}

	/**
	 * @param domainDAO the domainDAO to set
	 */
	/*public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}*/
}