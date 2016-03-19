package com.fpl.controller.request;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.AjaxBaseController;
import com.fpl.core.request.IRequestFinder;
import com.fpl.core.request.RequestListPV;
import com.fpl.core.request.SearchRequestParam;
import com.fpl.domain.Domain;
import com.fpl.login.UserLoginInfo;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.persistence.status.IStatusDAO;
import com.fpl.status.Status;
import com.fpl.util.JsonUtil;

public class SearchRequestController extends AjaxBaseController {

	private Map<String, IRequestFinder> requestFinderMap;
	private IStatusDAO statusDAO;
	private IDomainDAO domainDAO;
	
	@Override
	public ModelAndView executeAjaxCall(final HttpServletRequest request, final HttpServletResponse response) {
		final Map<String, String> resMap =  new HashMap<String, String>();
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		
		
		final String requestJson = request.getParameter("requestJson");
		System.out.println(requestJson);
		final SearchRequestParam requestParam = JsonUtil.convertPojo(requestJson, SearchRequestParam.class);

		requestParam.setId(loginInfo.getLoginCredentialid()+"");
		final IRequestFinder requestFinder = requestFinderMap.get(loginInfo.getUserType().getUser());
		final Collection<RequestListPV> requestList = requestFinder.getRequestList(requestParam);
		
		final Collection<Status> reqestStatuList = statusDAO.getStatusList(1L);
		
		final Collection<Domain> domains = domainDAO.getAllEntities();
		
		final Map jsonMap = new HashMap();
		jsonMap.put("DOMAIN", domains);
		jsonMap.put("SearchRequestParam", requestParam);
		jsonMap.put("RequestStatusList",reqestStatuList);
		jsonMap.put("RequestList",requestList);
		
		resMap.put("res", JsonUtil.toJsonString(jsonMap));
		return new ModelAndView("fplajax_domain", resMap);

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
