package com.fpl.core.policy.support;

import java.util.Date;

import com.fpl.FPLException;
import com.fpl.common.AbstractTransformer;
import com.fpl.company.Company;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.persistence.company.ICompanyDAO;
import com.fpl.persistence.request.IRequestDAO;
import com.fpl.persistence.status.IStatusDAO;
import com.fpl.policy.NewPolicyParam;
import com.fpl.policy.Policy;
import com.fpl.policy.PolicyErrorMessage;
import com.fpl.request.Request;
import com.fpl.status.Status;
import com.fpl.util.DateUtil;
import com.fpl.util.NumberUtil;
import com.fpl.util.StringUtil;

public class PolicyTransformer extends AbstractTransformer<NewPolicyParam, Policy> {
	
	private IRequestDAO requestDAO;
	private IStatusDAO statusDAO;
	private ICompanyDAO companyDAO;
	
	@Override
	public Policy transform(final NewPolicyParam policyParam) {
		final Policy policy = new Policy();
		policy.setDomainId(Long.valueOf(policyParam.getDomainId()));
		setContactInfo(policyParam, policy);
		setPremiumInfo(policyParam, policy);
		setPolicyInfo(policyParam, policy);
		setRequestInfo(policyParam, policy);
		return policy;
	}
	
	private void setContactInfo(final NewPolicyParam policyParam, final Policy policy) {
		policy.setContactName(policyParam.getFirstName()+"^"+policyParam.getLastName());
		policy.setContactNumber(policyParam.getContactNumber());
		policy.setRelation(policyParam.getRelation());
		policy.setIdProof(policyParam.getIdProof());
	}
	
	private void setPremiumInfo(final NewPolicyParam policyParam, final Policy policy) {
		policy.setFrequency(Integer.valueOf(policyParam.getFrequency()));
		policy.setAmount(NumberUtil.getConvertedNumber(policyParam.getAmount()));
		policy.setRenewalDate(DateUtil.getFormattedDate(policyParam.getRenewalDate(), "yyyy-MM-dd"));
		if(StringUtil.isNotEmpty(policyParam.getAdvanceAlert())) {
			policy.setAdvanceAlert(Integer.valueOf(policyParam.getAdvanceAlert()));
		} else {
			policy.setAdvanceAlert(1);
		}
		policy.setAlertType(policyParam.getAlertType());
	}
	
	private void setPolicyInfo(final NewPolicyParam policyParam, final Policy policy) {
		policy.setDuriation(Integer.valueOf(policyParam.getDuriation()));
		policy.setPolicyDate(DateUtil.getFormattedDate(policyParam.getPolicyDate(), "yyyy-MM-dd"));
		policy.setPolicyNumber(policyParam.getPolicyNumber());
		if(StringUtil.isEmpty(policyParam.getDomainId())) {
			throw new FPLException(FPLCommonErrorMessage.PLEASE_SELECT, "Type");
		}
		policy.setDomainId(Long.valueOf(policyParam.getDomainId()));
		final Status status = statusDAO.getStatusByDisc(policyParam.getStatus());
		policy.setStatusId(status.getId());
		policy.setNote(policyParam.getNote());
		policy.setCreatedDate(new Date());
	}
	
	private void setRequestInfo(final NewPolicyParam policyParam, final Policy policy) {
		if(!StringUtil.isEmpty(policyParam.getRequestId())) {
			final Request request = requestDAO.get(Long.valueOf(policyParam.getRequestId()));
			if(request == null) {
				throw new FPLException(PolicyErrorMessage.INVALID_REQUEST_NUMBER);
			}
			policy.setRequestId(request.getId());
			policy.setFplId(request.getFinPlannerId());
			policy.setCustomerId(request.getCustomerId());
		}
		if(!StringUtil.isEmpty(policyParam.getCompanyName())) {
			final Company company = companyDAO.getCompanyByName(policyParam.getCompanyName());
			if(company == null) {
				throw new FPLException(FPLCommonErrorMessage.INVALID, "Company Name");
			}
			policy.setCompanyId(company.getId());
		}
	}
	
	/**
	 * @param requestDAO the requestDAO to set
	 */
	public void setRequestDAO(final IRequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}

	/**
	 * @param statusDAO the statusDAO to set
	 */
	public void setStatusDAO(final IStatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

	/**
	 * @param companyDAO the companyDAO to set
	 */
	public void setCompanyDAO(final ICompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
}
