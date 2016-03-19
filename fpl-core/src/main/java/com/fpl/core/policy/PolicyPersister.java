package com.fpl.core.policy;

import com.fpl.common.AbstractTransformer;
import com.fpl.persistence.customer.ICustomerDAO;
import com.fpl.persistence.policy.IPolicyDAO;
import com.fpl.persistence.policy.IPolicyDAO2;
import com.fpl.persistence.status.IStatusDAO;
import com.fpl.policy.NewPolicyParam;
import com.fpl.policy.NewPolicyParam2;
import com.fpl.policy.Policy;
import com.fpl.policy.Policy2;
import com.fpl.profile.customer.Customer;
import com.fpl.status.Status;
import com.fpl.util.StringUtil;

public class PolicyPersister implements IPolicyPersister {
	
	private IPolicyDAO policyDAO;
	private IPolicyDAO2 policyDAO2;
	private AbstractTransformer<NewPolicyParam, Policy> transformer;
	private AbstractTransformer<NewPolicyParam2, Policy2> transformer2;
	private ICustomerDAO customerDAO;
	private IStatusDAO statusDAO;
	
	@Override
	public void persistPolicy(final NewPolicyParam policyParam) {
		System.out.println(policyParam);
		final Policy policy = transformer.transform(policyParam);
		if(StringUtil.isEmpty(policyParam.getRequestId())) {
			final Customer customer = customerDAO.getCustomerLoginId(Long.valueOf(policyParam.getLoginId()));
			policy.setCustomerId(customer.getId());
		}
		if(StringUtil.isNotEmpty(policyParam.getId())) {
			final Policy oldPolicy = policyDAO.get(Long.valueOf(policyParam.getId()));
			policy.setId(oldPolicy.getId());
			policy.setStatusId(oldPolicy.getStatusId());
			policy.setCreatedDate(oldPolicy.getCreatedDate());
		}
		policyDAO.save(policy, true);
	}
	@Override
	public void persistPolicy(final NewPolicyParam2 policyParam) {
		System.out.println(policyParam);
		final Policy2 policy = transformer2.transform(policyParam);
		final Customer customer = customerDAO.getCustomerLoginId(Long.valueOf(policyParam.getLoginId()));
		policy.setCustomerId(customer.getId());
		if(StringUtil.isEmpty(policyParam.getPolicyREId())) {
			/*final Customer customer = customerDAO.getCustomerLoginId(Long.valueOf(policyParam.getLoginId()));
			policy.setCustomerId(customer.getId());*/
		}
		if(StringUtil.isNotEmpty(policyParam.getId())) {
			final Policy2 oldPolicy = policyDAO2.get(Long.valueOf(policyParam.getId()));
			policy.setId(oldPolicy.getId());
			policy.setStatusId(oldPolicy.getStatusId());
			policy.setCreatedDate(oldPolicy.getCreatedDate());
		}
		policyDAO2.save(policy, true);
	}
	
	@Override
	public void updatePolicy(final String policyId, final String status) {
		final Policy policy = policyDAO.get(Long.valueOf(policyId));
		final String disDescription = status.equalsIgnoreCase("Accept") ? "ST_11" : "ST_12";
		final Status policyStatus = statusDAO.getStatusByDisc(disDescription);
		policy.setStatus(policyStatus);
		policy.setStatusId(policyStatus.getId());
		policyDAO.update(policy, true);
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
	 * @param transformer the transformer to set
	 */
	public void setTransformer(final AbstractTransformer<NewPolicyParam, Policy> transformer) {
		this.transformer = transformer;
	}
	/**
	 * @param transformer the transformer to set
	 */
	public void setTransformer2(final AbstractTransformer<NewPolicyParam2, Policy2> transformer) {
		this.transformer2 = transformer;
	}

	
	/**
	 * @param customerDAO the customerDAO to set
	 */
	public void setCustomerDAO(final ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	/**
	 * @param statusDAO the statusDAO to set
	 */
	public void setStatusDAO(final IStatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}
}
