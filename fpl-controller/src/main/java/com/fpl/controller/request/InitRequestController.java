package com.fpl.controller.request;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.core.request.IRequestFinder;
import com.fpl.core.request.RequestListPV;
import com.fpl.domain.Domain;
import com.fpl.login.UserLoginInfo;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.persistence.status.IStatusDAO;
import com.fpl.status.Status;

public class InitRequestController extends FPLAbstractController {

	private Map<String, IRequestFinder> requestFinderMap;
	private IStatusDAO statusDAO;
	private IDomainDAO domainDAO;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView(FPLPageName.REQUEST.getPageName());
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		final IRequestFinder requestFinder = requestFinderMap.get(loginInfo.getUserType().getUser());
		final Collection<RequestListPV> requestList = requestFinder.getRequestList(loginInfo.getLoginCredentialid());
		model.addObject("RequestList", requestList);
		final Collection<Status> reqestStatuList = statusDAO.getStatusList(1L);
		model.addObject("ReqestStatuList", reqestStatuList);
		final Collection<Domain> domains = domainDAO.getAllEntities();
		model.addObject("DomainList", domains);
		model.addObject("RequestDisplayCriteria", "Search");
		return model;
	}

	/**
	 * @param requestFinderMap the requestFinderMap to set
	 */
	public void setRequestFinderMap(final Map<String, IRequestFinder> requestFinderMap) {
		this.requestFinderMap = requestFinderMap;
	}

	/**
	 * @param statusDAO the statusDAO to set
	 */
	public void setStatusDAO(final IStatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}
}
