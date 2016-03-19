package com.fpl.controller.policy;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.fpl.company.Company;
import com.fpl.controller.support.AjaxBaseController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.core.policy.PolicyListPageView;
import com.fpl.domain.Domain;
import com.fpl.login.UserLoginInfo;
import com.fpl.persistence.company.ICompanyDAO;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.policy.PolicySearchParam;
import com.fpl.util.JsonUtil;


public class InitNewPolicyCreationController extends AjaxBaseController{
	
	@Autowired
	@Qualifier("fpl.profile.companyDAO")
	private ICompanyDAO companyDAO;
	
	private IDomainDAO domainDAO;
	
	@Override
	public ModelAndView executeAjaxCall(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stubfinal 
		Collection<Domain> domains = domainDAO.getAllEntities();
		System.out.println("Domains"+domains.size());
		Collection<String> cmpnylist=companyDAO.getCompanyList();
		System.out.println("cmpny"+cmpnylist.size());
		final Map<String, String> resMap =  new HashMap<String, String>();
		final Map map = new HashMap();
		final UserLoginInfo loginInfo = (UserLoginInfo) get(request,USER_LOGIN_KEY);
		String uniqueID=UUID.randomUUID().toString();
		Date today=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		map.put("policytype",domains);
		map.put("Recorddate", sdf.format(today));
		map.put("companylist", cmpnylist);
		map.put("uniqueID", uniqueID);
		map.put("fname", loginInfo.getFirstname());
		resMap.put("res", JsonUtil.toJsonString(map));
		return new ModelAndView("fplajax_domain", resMap);
		
	}
	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}
	
}
