package com.fpl.core.policy.support;

import java.util.Date;

import com.fpl.FPLException;
import com.fpl.common.AbstractTransformer;
import com.fpl.company.Company;
import com.fpl.errormsg.FPLCommonErrorMessage;
import com.fpl.persistence.company.ICompanyDAO;
import com.fpl.persistence.request.IRequestDAO;
import com.fpl.persistence.status.IStatusDAO;

import com.fpl.policy.NewPolicyParam2;
import com.fpl.policy.Policy;
import com.fpl.policy.Policy2;
import com.fpl.policy.PolicyErrorMessage;
import com.fpl.request.Request;
import com.fpl.status.Status;
import com.fpl.util.DateUtil;
import com.fpl.util.NumberUtil;
import com.fpl.util.StringUtil;

public class Policytransformer2 extends AbstractTransformer<NewPolicyParam2, Policy2> {
	
	private IRequestDAO requestDAO;
	private IStatusDAO statusDAO;
	private ICompanyDAO companyDAO;
	
	@Override
	public Policy2 transform(final NewPolicyParam2 policyParam) {
		final Policy2 policy = new Policy2();
		System.out.println("policytype"+policyParam.getPolicyTYPE());
		policy.setDomainId(Long.parseLong("4"));
		setContactInfo(policyParam, policy);
		setPremiumInfo(policyParam, policy);
		setPolicyInfo(policyParam, policy);
		setRequestInfo(policyParam, policy);
		return policy;
	}
	
	private void setContactInfo(final NewPolicyParam2 policyParam, final Policy2 policy) {
		policy.setContactName(policyParam.getPolicyFname());
		policy.setContactNumber(policyParam.getPolicyNUMBER());
		policy.setRelation(policyParam.getPolicy_nomineerelation());
		policy.setIdProof(policyParam.getPolicy_nomineeIDprof());
	}
	
	private void setPremiumInfo(final NewPolicyParam2 policyParam, final Policy2 policy) {
		System.out.println("policyscheduledays"+policyParam.getPolicySchedule_Days());
		policy.setFrequency(Integer.valueOf(policyParam.getPolicySchedule_Days()));
		policy.setAmount(NumberUtil.getConvertedNumber(policyParam.getPolicyAMOUNT()));
		policy.setRenewalDate(policyParam.getPolicyRENEWAL_DATE());
		//policy.setRenewalDate(DateUtil.getFormattedDate(policyParam.getPolicyRENEWAL_DATE(), "yyyy-MM-dd"));
		if(StringUtil.isNotEmpty(policyParam.getPolicyAdvancealert())) {
			policy.setAdvanceAlert(Integer.valueOf(policyParam.getPolicyAdvancealert()));
		} else {
			policy.setAdvanceAlert(1);
		}
		policy.setAlertType(policyParam.getPolicyAlertsch());
	}
	
	private void setPolicyInfo(final NewPolicyParam2 policyParam, final Policy2 policy) {
		policy.setDuriation(Integer.valueOf(policyParam.getPolicyDURATION()));
		policy.setPolicyDate(policyParam.getPolicyDATE());
		//policy.setPolicyDate(DateUtil.getFormattedDate(policyParam.getPolicyDATE(), "yyyy-MM-dd"));
		policy.setPolicyNumber(policyParam.getPolicyNUMBER());
		if(StringUtil.isEmpty(policyParam.getPolicyTYPE())) {
			throw new FPLException(FPLCommonErrorMessage.PLEASE_SELECT, "Type");
		}
		policy.setDomainId(Long.valueOf(policyParam.getPolicyTYPE()));
		final Status status = statusDAO.getStatusByDisc(policyParam.getStatus());
		policy.setStatusId(status.getId());
		
		policy.setNote(policyParam.getPolicyComment());
		policy.setCreatedDate(new Date());
	}
	
	private void setRequestInfo(final NewPolicyParam2 policyParam, final Policy2 policy) {
		/*if(!StringUtil.isEmpty(policyParam.getPolicyREId())) {
			final Request request = requestDAO.get(Long.valueOf(policyParam.getPolicyREId()));
			if(request == null) {
				throw new FPLException(PolicyErrorMessage.INVALID_REQUEST_NUMBER);
			}
			policy.setRequestId(request.getId());
			policy.setFplId(request.getFinPlannerId());
			policy.setCustomerId(request.getCustomerId());
		}*/
		if(!StringUtil.isEmpty(policyParam.getPolicyINSURANCECOMPANY())) {
			final Company company = companyDAO.getCompanyByName(policyParam.getPolicyINSURANCECOMPANY().toString());
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
