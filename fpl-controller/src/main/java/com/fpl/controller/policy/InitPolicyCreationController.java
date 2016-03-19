package com.fpl.controller.policy;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.core.policy.IPolicyFinder;
import com.fpl.domain.Domain;
import com.fpl.persistence.domain.IDomainDAO;
import com.fpl.policy.NewPolicyParam;
import com.fpl.util.StringUtil;

public class InitPolicyCreationController extends FPLAbstractController {
	
	private IDomainDAO domainDAO;
	private IPolicyFinder policyFinder;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView(FPLPageName.POLICY.getPageName());
		NewPolicyParam policyParam = new NewPolicyParam();
		policyParam.setAdvanceAlert("0");
		final String reqId = request.getParameter("EreocrdRequestNumber");
		System.out.println("reqId --> "+ reqId);
		policyParam.setRequestId(reqId);
		final String policyId = request.getParameter("policyId");
		if(StringUtil.isNotEmpty(policyId)) {
			policyParam = policyFinder.getNewPolicyParam(Long.valueOf(policyId));
		}
		final Collection<Domain> domains = domainDAO.getAllEntities();
		model.addObject("DomainList", domains);
		model.addObject("newPolicyParam", policyParam);
		model.addObject("PolicyDisplayCriteria", "Create");		
		return model;
	}

	/**
	 * @param domainDAO the domainDAO to set
	 */
	public void setDomainDAO(final IDomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}

	/**
	 * @param policyFinder the policyFinder to set
	 */
	public void setPolicyFinder(final IPolicyFinder policyFinder) {
		this.policyFinder = policyFinder;
	}
}
