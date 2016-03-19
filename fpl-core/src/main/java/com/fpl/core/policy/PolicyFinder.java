package com.fpl.core.policy;

import java.util.ArrayList;
import java.util.Collection;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.communication.UserInfo;
import com.fpl.financialplanner.FinancialPlanner;
import com.fpl.login.UserType;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.financialplanner.IFinancialPlannerDAO;
import com.fpl.persistence.login.ILoginQueryDAO;
import com.fpl.persistence.policy.IPolicyDAO;
import com.fpl.persistence.policy.IPolicyDAO2;
import com.fpl.policy.NewPolicyParam;
import com.fpl.policy.Policy;
import com.fpl.policy.Policy2;
import com.fpl.policy.PolicySearchParam;
import com.fpl.profile.customer.Customer;
import com.fpl.util.DateUtil;

public class PolicyFinder implements IPolicyFinder {
	
	private IPolicyDAO policyDAO;
	private IPolicyDAO2 policyDAO2;
	private IFinancialPlannerDAO financialPlannerDAO;
	private ICustomerDAO customerDAO;
	private AbstractTransformer transformer;
	private ILoginQueryDAO loginQueryDAO;
	
	@Override
	public Collection<PolicyListPageView> getPolicyList(final PolicySearchParam searchParam) {
		if(searchParam.getUserType().getUser().startsWith("cust")) {
			final Customer customer = customerDAO.getCustomerLoginId(searchParam.getLoginId());
			searchParam.setCustomerId(customer.getId());
		} else if(searchParam.getUserType().getUser().startsWith("fp")) {
			final FinancialPlanner planner = financialPlannerDAO.getFinancialPlannerByLoginId(searchParam.getLoginId());
			searchParam.setFplId(planner.getId());
		}
		final Collection<Policy2> policies = policyDAO2.getPolicyList(searchParam);
		return new AbstractTransformer<Policy2, PolicyListPageView>() {
			@Override
			public PolicyListPageView transform(final Policy2 policy) {
				final PolicyListPageView pageView = new PolicyListPageView();
				pageView.setId(policy.getId());
				pageView.setPolicyDate(policy.getPolicyDate());
				//pageView.setPolicyDate(DateUtil.getFormattedDate(policy.getPolicyDate()));
				pageView.setRequestId(""+policy.getRequestId());
				pageView.setStatus(policy.getStatus().getDescription());
				pageView.setType(policy.getDomain().getName());
				pageView.setPolicyNumber(policy.getPolicyNumber());
				return pageView;
			}
		}.transform(policies);
	}

	@Override
	public NewPolicyParam getNewPolicyParam(final Long policyId) {
		final Policy policy = policyDAO.get(policyId);
		return (NewPolicyParam) transformer.transform(policy);
	}
	
	@Override
	public Collection<UserInfo> getMyUser(final PolicySearchParam searchParam) {
		final Collection<UserInfo> userInfos = new ArrayList<UserInfo>();
		if(searchParam.getUserType().getUser().startsWith("cust")) {
			final Customer customer = customerDAO.getCustomerLoginId(searchParam.getLoginId());
			if(customer!=null)
			{
				searchParam.setCustomerId(customer.getId());
			}	
		} else {
			final FinancialPlanner planner = financialPlannerDAO.getFinancialPlannerByLoginId(searchParam.getLoginId());
			searchParam.setFplId(planner.getId());
		}
		final Collection<Policy> policies = policyDAO.getPolicyList(searchParam);
		for(final Policy policy : policies) {
			UserInfo userInfo = null;
			if(searchParam.getUserType().getUser().startsWith("cust")) {
				userInfo = loginQueryDAO.getUserInfo(UserType.FP_CORPORATE, policy.getFplId());
			} else if(searchParam.getUserType().getUser().startsWith("fp")) {
				userInfo = loginQueryDAO.getUserInfo(UserType.CUST_INDIVIDUAL, policy.getCustomerId());
			}
			userInfos.add(userInfo);
		}
		return userInfos;
		
	}
	/**
	 * @param policyDAO the policyDAO to set
	 */
	public void setPolicyDAO(final IPolicyDAO policyDAO) {
		this.policyDAO = policyDAO;
	}

	/**
	 * @param policyDAO the policyDAO to set
	 */
	public void setPolicyDAO2(final IPolicyDAO2 policyDAO) {
		this.policyDAO2 = policyDAO;
	}

	
	/**
	 * @param financialPlannerDAO the financialPlannerDAO to set
	 */
	public void setFinancialPlannerDAO(final IFinancialPlannerDAO financialPlannerDAO) {
		this.financialPlannerDAO = financialPlannerDAO;
	}

	/**
	 * @param customerDAO the customerDAO to set
	 */
	public void setCustomerDAO(final ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	/**
	 * @param transformer the transformer to set
	 */
	public void setTransformer(final AbstractTransformer transformer) {
		this.transformer = transformer;
	}

	/**
	 * @param loginQueryDAO the loginQueryDAO to set
	 */
	public void setLoginQueryDAO(final ILoginQueryDAO loginQueryDAO) {
		this.loginQueryDAO = loginQueryDAO;
	}
}
