package com.fpl.core.policy;

import java.util.Collection;

import com.fpl.core.communication.UserInfo;
import com.fpl.policy.NewPolicyParam;
import com.fpl.policy.PolicySearchParam;

public interface IPolicyFinder {

	Collection<PolicyListPageView> getPolicyList(PolicySearchParam searchParam);
	
	NewPolicyParam getNewPolicyParam(Long policyId);
	
	Collection<UserInfo> getMyUser(PolicySearchParam searchParam);
}
