package com.fpl.controller.policy;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.controller.support.RequestToPageViewTransfer;
import com.fpl.core.policy.IPolicyPersister;
import com.fpl.domain.Domain;
import com.fpl.login.UserLoginInfo;
import com.fpl.login.UserType;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.policy.NewPolicyParam;

public class NewPolicyCreationController extends FPLAbstractController {
	
	private IPolicyPersister policyPersister;
	private IDomainDAO domainDAO;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		ModelAndView model = new ModelAndView(FPLPageName.POLICY.getPageName());
		final UserLoginInfo loginInfo = get(request, USER_LOGIN_KEY);
		final NewPolicyParam policyParam  = RequestToPageViewTransfer.getPageView(NewPolicyParam.class, request);
		policyParam.setLoginId(loginInfo.getLoginCredentialid()+"");
		if(loginInfo.getUserType().equals(UserType.CUST_CORPORATE) || loginInfo.getUserType().equals(UserType.CUST_INDIVIDUAL)) {
			policyParam.setStatus("ST_10");
		} else {
			policyParam.setStatus("ST_11");
		}
		try {
			policyPersister.persistPolicy(policyParam);
			model = new ModelAndView("initPolicy.do");
		} catch (final Exception e) {
			e.printStackTrace();
			final Collection<Domain> domains = domainDAO.getAllEntities();
			model.addObject("PolicyDisplayCriteria", "Create");
			model.addObject("newPolicyParam", policyParam);
			model.addObject("DomainList", domains);
			model.addObject(ERROR_MESSAGE, e.getMessage());
		}
		return model;
	}

	/**
	 * @param policyPersister the policyPersister to set
	 */
	public void setPolicyPersister(final IPolicyPersister policyPersister) {
		this.policyPersister = policyPersister;
	}

	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}
}
