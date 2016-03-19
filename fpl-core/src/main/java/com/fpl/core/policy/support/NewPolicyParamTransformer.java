package com.fpl.core.policy.support;

import com.fpl.common.AbstractTransformer;
import com.fpl.policy.NewPolicyParam;
import com.fpl.policy.Policy;
import com.fpl.util.DateUtil;
import com.fpl.util.NumberUtil;

public class NewPolicyParamTransformer extends AbstractTransformer<Policy, NewPolicyParam> {
	
	@Override
	public NewPolicyParam transform(final Policy policy) {
		final NewPolicyParam policyParam = new NewPolicyParam();
		policyParam.setId(policy.getId()+"");
		policyParam.setNote(policy.getNote());
		setContactInfo(policyParam, policy);
		setPremiumInfo(policyParam, policy);
		setPolicyInfo(policyParam, policy);
		setRequestInfo(policyParam, policy);
		return policyParam;
	}
	
	private void setContactInfo(final NewPolicyParam policyParam, final Policy policy) {
		final String[] nameArray = policy.getContactName().split("^");
		System.out.println(nameArray.length);
		if(nameArray.length > 1) {
			policyParam.setFirstName(nameArray[0]);
			policyParam.setLastName(nameArray[1]);
		} else if(nameArray.length == 1) {
			policyParam.setFirstName(nameArray[0]);
		}
		policyParam.setContactNumber(policy.getContactNumber());
		policyParam.setRelation(policy.getRelation());
		policyParam.setIdProof(policy.getIdProof());
	}
	
	private void setPremiumInfo(final NewPolicyParam policyParam, final Policy policy) {
		policyParam.setFrequency(policy.getFrequency()+"");
		policyParam.setAmount(NumberUtil.getFormattedNumber(policy.getAmount()));
		policyParam.setRenewalDate(DateUtil.getFormattedDate(policy.getRenewalDate(), "yyyy-MM-dd"));
		policyParam.setAdvanceAlert(policy.getAdvanceAlert()+"");
		policyParam.setAlertType(policy.getAlertType());
	}
	
	private void setPolicyInfo(final NewPolicyParam policyParam, final Policy policy) {
		policyParam.setDuriation(policy.getDuriation()+"");
		policyParam.setPolicyDate(DateUtil.getFormattedDate(policy.getPolicyDate(), "yyyy-MM-dd"));
		policyParam.setPolicyNumber(policy.getPolicyNumber());
		policyParam.setDomainId(policy.getDomainId()+"");
		policyParam.setNote(policy.getNote());
		policyParam.setCreatedDate(DateUtil.getFormattedDate(policy.getCreatedDate()));
	}
	
	private void setRequestInfo(final NewPolicyParam policyParam, final Policy policy) {
		policyParam.setRequestId(policy.getRequestId()+"");
	}
}
